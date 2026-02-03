package com.sysnormal.libs.db.entities.sysnormal_entities.reports.campaigns;

import com.sysnormal.libs.db.entities.sysnormal_entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "campaign_entities_kpi_result_values",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "campaign_entities_kpi_result_values_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "campaign_entity_id","campaign_kpi_result_value_id"
                        }
                )
        }
)
public class CampaignEntityKpiResultValue extends BaseSysnormalEntity<CampaignEntityKpiResultValue> {

    @Column(name = "campaign_entity_id", nullable = false)
    private Long apiRequestCallId;

    @Column(name = "campaign_kpi_result_value_id", nullable = false)
    private Long campaignKpiResultValueId;

    @Column(name = "value", nullable = false, precision = 38, scale = 12)
    @ColumnDefault("0")
    private BigDecimal value = BigDecimal.ZERO;

    @Column(name = "calculated_at")
    private LocalDateTime calculatedAt;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_entity_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CampaignEntity campaignEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_kpi_result_value_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CampaignKpiResultValue campaignKpiResultValue;

    protected static final long TABLE_ID = 16060;
    public static long getTableId() {
        return TABLE_ID;
    }
}
