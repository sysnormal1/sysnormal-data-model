package com.sysnormal.data.sysnormal_data_model.repositories.commissions;

import com.sysnormal.data.sysnormal_data_model.entities.commissions.CommissionApuration;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommissionApurationsRepository extends BaseSysnormalRepository<CommissionApuration, Long> {

    /**
     * O fechamento exato de um período.
     *
     * <p>Vazio não é falha: é um período que ainda não fechou. Quem pergunta a
     * comissão do mês corrente cai nesse caso quase sempre, porque o fechamento
     * roda depois que o mês acaba.</p>
     */
    Optional<CommissionApuration> findByCommissionContractEntityIdAndStartAtAndEndAt(
            Long commissionContractEntityId, LocalDateTime startAt, LocalDateTime endAt);

    /**
     * Fechamentos que tocam a faixa pedida, do mais recente para o mais antigo.
     *
     * <p>Serve quando o período perguntado não coincide com o apurado — pedir
     * "de 01/07 a 15/07" não deve devolver nada de julho fechado inteiro, mas
     * quem chama precisa enxergar que aquele fechamento existe para decidir.</p>
     */
    @Query("""
            select ca from CommissionApuration ca
            where ca.commissionContractEntityId = :commissionContractEntityId
              and ca.startAt <= :endAt
              and ca.endAt >= :startAt
            order by ca.startAt desc
            """)
    List<CommissionApuration> findOverlapping(@Param("commissionContractEntityId") Long commissionContractEntityId,
                                              @Param("startAt") LocalDateTime startAt,
                                              @Param("endAt") LocalDateTime endAt);

}
