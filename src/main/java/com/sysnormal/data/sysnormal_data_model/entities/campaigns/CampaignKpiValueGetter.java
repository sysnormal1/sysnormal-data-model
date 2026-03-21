package com.sysnormal.data.sysnormal_data_model.entities.campaigns;

import com.sysnormal.data.basic_data_model.entities.measures.measurementUnit.MeasurementUnit;
import com.sysnormal.data.basic_data_model.entities.reports.reportVision.ReportVision;
import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "campaign_kpi_value_getters",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "campaign_kpi_value_getters_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "campaign_kpi_id",
                                "campaign_entity_ids",
                                "name"
                        }
                )
        }
)
public class CampaignKpiValueGetter extends BaseSysnormalEntity<CampaignKpiValueGetter> {

    @Column(name = "campaign_kpi_id", nullable = false)
    private Long campaignKpiId;

    @Column(name = "campaign_entity_ids", length = 127)
    private String campaignEntityIds;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "report_vision_id")
    private Long reportVisionId;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "measurement_unit_id", nullable = false)
    @ColumnDefault(MeasurementUnit.WT_ID + "")
    private Long measurementUnitId = MeasurementUnit.WT_ID;

    @Column(name = "consider_normal_sales", nullable = false, length = 1)
    @ColumnDefault("1")
    @Check(constraints = "consider_normal_sales in (0,1)")
    private byte considerNormalSales = 1;

    @Column(name = "consider_returns", nullable = false, length = 1)
    @ColumnDefault("1")
    @Check(constraints = "consider_returns in (0,1)")
    private byte considerReturns = 1;

    @Column(name = "consider_bonuses", nullable = false, length = 1)
    @ColumnDefault("0")
    @Check(constraints = "consider_bonuses in (0,1)")
    private byte considerBonuses = 0;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "objective_query", length = Integer.MAX_VALUE)
    private String objectiveQuery;

    @Column(name = "value_query", length = Integer.MAX_VALUE)
    private String valueQuery;

    @Column(name = "objective")
    private BigDecimal objective;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "calculated_at")
    private LocalDateTime calculatedAt;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_kpi_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CampaignKpi campaignKpi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_vision_id", updatable = false, insertable = false)
    private ReportVision reportVision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", updatable = false, insertable = false)
    private MeasurementUnit measurementUnit;


}
