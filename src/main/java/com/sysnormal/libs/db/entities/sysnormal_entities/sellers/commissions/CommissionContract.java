package com.sysnormal.libs.db.entities.sysnormal_entities.sellers.commissions;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.TemporalPeriod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "commission_contracts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "commission_contracts_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class CommissionContract extends BaseBasicEntity<CommissionContract> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "apuration_temporal_period_id", nullable = false)
    @ColumnDefault(TemporalPeriod.MONTH_ID + "")
    private Long apurationTemporalPeriodId = TemporalPeriod.MONTH_ID;

    @Column(name = "replication_temporal_period_id")
    private Long replicationTemporalPeriodId;

    @Column(name = "availability_start_at")
    private LocalDateTime availabilityStartAt;

    @Column(name = "availability_end_at")
    private LocalDateTime availabilityEndAt;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "min_base_value", precision = 38, scale = 12)
    private BigDecimal minBaseValue;

    @Column(name = "max_base_value", precision = 38, scale = 12)
    private BigDecimal maxBaseValue;

    @Column(name = "min_percent1", precision = 38, scale = 12)
    private BigDecimal minPercent1;

    @Column(name = "max_percent1", precision = 38, scale = 12)
    private BigDecimal maxPercent1;

    @Column(name = "min_result_value", precision = 38, scale = 12)
    private BigDecimal minResultValue;

    @Column(name = "max_result_value", precision = 38, scale = 12)
    private BigDecimal maxResultValue;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apuration_temporal_period_id", updatable = false, insertable = false)
    private TemporalPeriod apurationTemporalPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replication_temporal_period_id", updatable = false, insertable = false)
    private TemporalPeriod replicationTemporalPeriod;

    protected static final long TABLE_ID = 16100;
    public static long getTableId() {
        return TABLE_ID;
    }
}
