package com.sysnormal.libs.db.entities.sysnormal_entities.sellers.commissions;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.EntityType;
import com.sysnormal.libs.db.entities.basic_entities.commons.TemporalPeriod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "commission_entities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "commission_entities_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "commission_contract_id",
                                "entity_type_id",
                                "entity_id",
                                "entity_name",
                                "(coalesce(alias,' '))",
                                "(coalesce(start_at,'${DatabaseUtils.NULL_DATE_SENTINEL}'))",
                                "(coalesce(end_at,'${DatabaseUtils.NULL_DATE_SENTINEL}'))"
                        }
                )
        }
)
public class CommissionEntity extends BaseBasicEntity<CommissionEntity> {


    @Column(name = "commission_contract_id", nullable = false)
    private Long commissionContractId;

    @Column(name = "entity_type_id", nullable = false)
    private Long entityTypeId;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "entity_name", nullable = false, length = 127)
    private String entityName;

    @Column(name = "description")
    private String description;

    @Column(name = "apuration_temporal_period_id")
    @ColumnDefault(TemporalPeriod.MONTH_ID + "")
    private Long apurationTemporalPeriodId = TemporalPeriod.MONTH_ID;

    @Column(name = "replication_temporal_period_id")
    private Long replicationTemporalPeriodId;

    @Column(name = "alias", length = 127)
    private String alias;

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

    @Column(name = "should_auto_replicate", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "should_auto_replicate in (0,1)")
    private byte shouldAutoReplicate = 0;

    @Column(name = "notes")
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

    protected static final long TABLE_ID = 16111;
    public static long getTableId() {
        return TABLE_ID;
    }
}
