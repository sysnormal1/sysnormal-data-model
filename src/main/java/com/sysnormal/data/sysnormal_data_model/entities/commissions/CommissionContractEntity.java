package com.sysnormal.data.sysnormal_data_model.entities.commissions;

import com.sysnormal.data.basic_data_model.entities.commons.entityType.EntityType;
import com.sysnormal.data.basic_data_model.entities.measures.temporalPeriod.TemporalPeriod;
import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

/**
 * Quem está sob qual contrato de comissão, e desde quando.
 *
 * <p>Antes esse vínculo estava embutido na apuração, que é uma linha por período
 * fechado. Isso deixava duas perguntas sem resposta: qual o contrato de um
 * vendedor num mês que ainda não fechou, e desde quando ele está nele. As duas
 * são de cadastro, não de apuração — daí a tabela.</p>
 *
 * <p>A vigência é própria, e é o que permite <b>sucessão de contrato</b>: um
 * vendedor que sai de um contrato em dezembro e entra em outro em janeiro tem
 * dois vínculos sequenciais, não um vínculo alterado. O histórico das apurações
 * antigas continua apontando para o vínculo sob o qual foram calculadas.</p>
 */
@Getter
@Setter
@Entity
@Table(
        name = "commission_contract_entities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "commission_contract_entities_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "commission_contract_id",
                                "entity_type_id",
                                "entity_id",
                                "contract_started_at",
                                "(coalesce(contract_ended_at,'1000-01-01'))"
                        }
                )
        }
)
public class CommissionContractEntity extends BaseSysnormalEntity<CommissionContractEntity> {

    @Column(name = "commission_contract_id", nullable = false)
    private Long commissionContractId;

    @Column(name = "entity_type_id", nullable = false)
    private Long entityTypeId;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "entity_name", nullable = false, length = 127)
    private String entityName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "alias", length = 127)
    private String alias;

    /** Início do contrato desta entidade. Não é o período de apuração. */
    @Column(name = "contract_started_at", nullable = false)
    private LocalDateTime contractStartedAt;

    /** Nulo significa vigente, nunca "encerrado sem data". */
    @Column(name = "contract_ended_at")
    private LocalDateTime contractEndedAt;

    @Column(name = "apuration_temporal_period_id")
    @ColumnDefault(TemporalPeriod.MONTH_ID + "")
    private Long apurationTemporalPeriodId = TemporalPeriod.MONTH_ID;

    @Column(name = "replication_temporal_period_id")
    private Long replicationTemporalPeriodId;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "should_auto_replicate", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "should_auto_replicate in (0,1)")
    private byte shouldAutoReplicate = 0;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commission_contract_id", updatable = false, insertable = false)
    private CommissionContract commissionContract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id", updatable = false, insertable = false)
    private EntityType entityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apuration_temporal_period_id", updatable = false, insertable = false)
    private TemporalPeriod apurationTemporalPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replication_temporal_period_id", updatable = false, insertable = false)
    private TemporalPeriod replicationTemporalPeriod;


}
