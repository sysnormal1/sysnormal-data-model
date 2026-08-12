package com.sysnormal.data.sysnormal_data_model.entities.commissions;

import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * O fechamento de um período — era {@code commission_entities}.
 *
 * <p>O nome mudou porque a tabela nunca foi o vínculo do vendedor com o
 * contrato: é o resultado apurado de um período. Quem é o vendedor e sob qual
 * contrato ele está agora vive em {@link CommissionContractEntity}; aqui ficam
 * {@code start_at}/{@code end_at} do período, o valor e quando foi calculado.</p>
 *
 * <p>Ausência de linha para um período é resposta legítima: significa que o
 * período ainda não fechou. Quem pergunta a comissão do mês corrente cai nesse
 * caso quase sempre, e é o serviço quem decide o que fazer — não este cadastro.</p>
 */
@Getter
@Setter
@Entity
@Table(
        name = "commission_apurations",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "commission_apurations_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "commission_contract_entity_id",
                                "start_at",
                                "end_at"
                        }
                )
        }
)
public class CommissionApuration extends BaseSysnormalEntity<CommissionApuration> {

    @Column(name = "commission_contract_entity_id", nullable = false)
    private Long commissionContractEntityId;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "min_base_value", precision = 38, scale = 12)
    private BigDecimal minBaseValue;

    @Column(name = "max_base_value", precision = 38, scale = 12)
    private BigDecimal maxBaseValue;

    @Column(name = "base_value", precision = 38, scale = 12)
    private BigDecimal baseValue;

    @Column(name = "min_percent1", precision = 38, scale = 12)
    private BigDecimal minPercent1;

    @Column(name = "max_percent1", precision = 38, scale = 12)
    private BigDecimal maxPercent1;

    @Column(name = "min_result_value", precision = 38, scale = 12)
    private BigDecimal minResultValue;

    @Column(name = "max_result_value", precision = 38, scale = 12)
    private BigDecimal maxResultValue;

    @Column(name = "expression", length = Integer.MAX_VALUE)
    private String expression;

    @Column(name = "result_value", precision = 38, scale = 12)
    private BigDecimal resultValue;

    @Column(name = "calculated_at")
    private LocalDateTime calculatedAt;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commission_contract_entity_id", updatable = false, insertable = false)
    private CommissionContractEntity commissionContractEntity;


}
