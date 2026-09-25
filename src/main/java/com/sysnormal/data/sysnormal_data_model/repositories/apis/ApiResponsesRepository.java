package com.sysnormal.data.sysnormal_data_model.repositories.apis;

import com.sysnormal.data.sysnormal_data_model.entities.apis.ApiResponse;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * As respostas guardadas.
 *
 * <p>Uma chamada pode ter mais de uma resposta — a API de mapas pagina, e uma
 * tentativa que falhou e outra que deu certo são duas respostas da mesma
 * chamada. Por isso a consulta devolve lista e ordena da mais nova para a mais
 * velha: quem quer "a resposta" quer a última.</p>
 */
@Repository
public interface ApiResponsesRepository extends BaseSysnormalRepository<ApiResponse, Long> {

    @Query("""
            select r from ApiResponse r
            where r.apiRequestCallId = :callId and r.deletedAt is null
            order by r.id desc
            """)
    List<ApiResponse> findOfCall(@Param("callId") Long callId);

    /**
     * A última resposta guardada para um alvo, de qualquer chamada anterior.
     *
     * <p>É o cache local, e o alvo é o par agnóstico de sempre — origem,
     * tabela e id lá —, então serve a qualquer API e a qualquer entidade:
     * "o que já sabemos sobre o cliente 12462 do Winthor, e quando".</p>
     *
     * <p>⚠️ {@code statusCodes} é de quem chama, e não um padrão daqui:
     * reaproveitar um 500 transformaria uma falha momentânea da fonte em
     * resposta oficial pelos dias seguintes. Quem sabe o que é aproveitável
     * naquela API é quem a conhece.</p>
     *
     * <p>⚠️ {@code ignorarCabecalho} existe para excluir as <b>cópias</b>, e
     * sem ele o cache nunca envelhece: quem reaproveita costuma guardar uma
     * cópia da resposta, a cópia nasce com data de hoje, e a próxima busca
     * acha a cópia em vez do original. Medido: três releituras seguidas do
     * mesmo cliente produziram 525 ← 524 ← 523, cada uma "mais nova" que a
     * anterior — o dado de 09:43 pareceria fresco para sempre, e a fonte nunca
     * mais seria consultada.</p>
     */
    @Query("""
            select r from ApiResponse r
            where r.dataOriginId = :dataOriginId
              and r.tableOriginId = :tableOriginId
              and r.idAtOrigin = :idAtOrigin
              and r.responseStatusCode in :statusCodes
              and r.response is not null
              and r.createdAt >= :desde
              and (r.responseHeaders is null or r.responseHeaders not like :ignorarCabecalho)
              and r.deletedAt is null
            order by r.id desc
            """)
    List<ApiResponse> findLastOfTargetSince(@Param("dataOriginId") Long dataOriginId,
                                            @Param("tableOriginId") Long tableOriginId,
                                            @Param("idAtOrigin") String idAtOrigin,
                                            @Param("statusCodes") List<Integer> statusCodes,
                                            @Param("desde") LocalDateTime desde,
                                            @Param("ignorarCabecalho") String ignorarCabecalho,
                                            Limit limit);

    /** As últimas respostas de um lote inteiro, sem uma consulta por item. */
    @Query("""
            select r from ApiResponse r
            where r.apiRequestCall.parentId = :batchId and r.deletedAt is null
            order by r.apiRequestCallId, r.id desc
            """)
    List<ApiResponse> findOfBatch(@Param("batchId") Long batchId);
}
