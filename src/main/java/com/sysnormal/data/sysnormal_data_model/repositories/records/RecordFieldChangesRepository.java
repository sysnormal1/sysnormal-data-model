package com.sysnormal.data.sysnormal_data_model.repositories.records;

import com.sysnormal.data.sysnormal_data_model.entities.records.RecordFieldChange;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/** O log do que foi aplicado — por alvo, ou o mais recente de tudo. */
@Repository
public interface RecordFieldChangesRepository extends BaseSysnormalRepository<RecordFieldChange, Long> {

    @Query("""
            select c from RecordFieldChange c
            where c.tableOriginId = :tableOriginId
              and c.idAtOrigin = :idAtOrigin
              and c.deletedAt is null
            order by c.id desc
            """)
    List<RecordFieldChange> findOfTarget(@Param("tableOriginId") Long tableOriginId,
                                         @Param("idAtOrigin") String idAtOrigin);

    /**
     * O log recente, para a aba de log.
     *
     * <p>Sem filtro de alvo de propósito: a pergunta da tela é "o que andou
     * mudando no cadastro", e não "o que mudou neste cliente" — essa é a de
     * cima.</p>
     */
    @Query("""
            select c from RecordFieldChange c
            where c.tableOriginId = :tableOriginId and c.deletedAt is null
            order by c.id desc
            """)
    List<RecordFieldChange> findRecent(@Param("tableOriginId") Long tableOriginId, Limit limit);
}
