package com.sysnormal.data.sysnormal_data_model.repositories.commissions;

import com.sysnormal.data.sysnormal_data_model.entities.commissions.CommissionContractEntity;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommissionContractEntitiesRepository extends BaseSysnormalRepository<CommissionContractEntity, Long> {

    /**
     * Vínculos vigentes de uma entidade num instante.
     *
     * <p>Devolve lista, e não um único, de propósito: a vigência é cadastro, e
     * cadastro sobreposto existe. Quem chama decide se dois vínculos ativos ao
     * mesmo tempo são erro — aqui, esconder o segundo seria escolher um por
     * conta própria.</p>
     *
     * <p>{@code contract_ended_at} nulo significa vigente, nunca "encerrado sem
     * data": daí o teste ser por nulidade antes da comparação.</p>
     */
    @Query("""
            select cce from CommissionContractEntity cce
            where cce.entityTypeId = :entityTypeId
              and cce.entityId = :entityId
              and cce.contractStartedAt <= :moment
              and (cce.contractEndedAt is null or cce.contractEndedAt >= :moment)
            order by cce.contractStartedAt desc
            """)
    List<CommissionContractEntity> findValidAt(@Param("entityTypeId") Long entityTypeId,
                                               @Param("entityId") Long entityId,
                                               @Param("moment") LocalDateTime moment);

}
