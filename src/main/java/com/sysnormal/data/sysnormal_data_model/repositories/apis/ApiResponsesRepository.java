package com.sysnormal.data.sysnormal_data_model.repositories.apis;

import com.sysnormal.data.sysnormal_data_model.entities.apis.ApiResponse;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    /** As últimas respostas de um lote inteiro, sem uma consulta por item. */
    @Query("""
            select r from ApiResponse r
            where r.apiRequestCall.parentId = :batchId and r.deletedAt is null
            order by r.apiRequestCallId, r.id desc
            """)
    List<ApiResponse> findOfBatch(@Param("batchId") Long batchId);
}
