package com.sysnormal.data.sysnormal_data_model.repositories.apis;

import com.sysnormal.data.sysnormal_data_model.entities.apis.ApiRequestCall;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * As execuções de uma requisição de API.
 *
 * <h2>O lote é uma chamada que tem chamadas filhas</h2>
 *
 * <p>Um lote de consulta é uma linha com {@code parentId} nulo; cada item dele
 * é uma linha filha apontando para ela. Não foi preciso inventar uma tabela de
 * lote: {@code parent_id} existe em toda entidade daqui, e o vocabulário de
 * andamento — NOT STARTED, RUNNING, STOPPED, CONCLUDED, CANCELED — já está em
 * {@code action_status}.</p>
 *
 * <p>É o mesmo desenho que permite retomar: o worker pede sempre os
 * <b>próximos pendentes</b>, então reiniciar o serviço no meio de um lote de
 * dez mil itens não repete o que já foi feito nem perde o que faltava.</p>
 */
@Repository
public interface ApiRequestCallsRepository extends BaseSysnormalRepository<ApiRequestCall, Long> {

    /**
     * Os próximos itens a executar de um lote, do mais antigo para o mais novo.
     *
     * <p>A ordem por id é o que dá previsibilidade ao andamento: quem olha a
     * tela vê a lista avançar na ordem em que a montou.</p>
     */
    @Query("""
            select c from ApiRequestCall c
            where c.parentId = :batchId
              and c.runStatusId = :statusId
              and c.deletedAt is null
            order by c.id
            """)
    List<ApiRequestCall> findNextOfBatch(@Param("batchId") Long batchId,
                                         @Param("statusId") Long statusId,
                                         Limit limit);

    /** Quantos itens do lote estão em cada situação — o andamento da tela. */
    @Query("""
            select c.runStatusId, count(c)
            from ApiRequestCall c
            where c.parentId = :batchId and c.deletedAt is null
            group by c.runStatusId
            """)
    List<Object[]> countByStatusOfBatch(@Param("batchId") Long batchId);

    /** Os itens de um lote, para a tela de resultados. */
    @Query("""
            select c from ApiRequestCall c
            where c.parentId = :batchId and c.deletedAt is null
            order by c.id
            """)
    List<ApiRequestCall> findAllOfBatch(@Param("batchId") Long batchId);

    /**
     * Lotes em aberto, para o worker retomar depois de uma reinicialização.
     *
     * <p>Um lote fica RUNNING enquanto tem item por fazer. Sem esta consulta,
     * reiniciar o serviço deixaria lotes parados para sempre esperando alguém
     * clicar de novo.</p>
     */
    @Query("""
            select c from ApiRequestCall c
            where c.parentId is null
              and c.runStatusId in :statusIds
              and c.deletedAt is null
            order by c.id
            """)
    List<ApiRequestCall> findBatchesByStatus(@Param("statusIds") List<Long> statusIds);

    /** Um item do lote pelo alvo — usado para não pedir duas vezes o mesmo CNPJ. */
    @Query("""
            select c from ApiRequestCall c
            where c.parentId = :batchId
              and c.idAtOrigin = :idAtOrigin
              and c.deletedAt is null
            """)
    List<ApiRequestCall> findOfBatchByTarget(@Param("batchId") Long batchId,
                                             @Param("idAtOrigin") String idAtOrigin);
}
