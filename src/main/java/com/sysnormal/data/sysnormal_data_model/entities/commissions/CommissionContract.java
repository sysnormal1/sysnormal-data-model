package com.sysnormal.data.sysnormal_data_model.entities.commissions;

import com.sysnormal.data.basic_data_model.entities.measures.temporalPeriod.TemporalPeriod;
import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
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
public class CommissionContract extends BaseSysnormalEntity<CommissionContract> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
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

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apuration_temporal_period_id", updatable = false, insertable = false)
    private TemporalPeriod apurationTemporalPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replication_temporal_period_id", updatable = false, insertable = false)
    private TemporalPeriod replicationTemporalPeriod;


}
