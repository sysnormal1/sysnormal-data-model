package com.sysnormal.libs.db.entities.sysnormal_entities.reports.campaigns;

import com.sysnormal.libs.db.entities.sysnormal_entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "campaign_kpi_result_values",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "campaign_kpi_result_values_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "campaign_kpi_id",
                                "campaign_entity_ids",
                                "name"
                        }
                )
        }
)
public class CampaignKpiResultValue extends BaseSysnormalEntity<CampaignKpiResultValue> {

    @Column(name = "campaign_kpi_id", nullable = false)
    private Long campaignKpiId;

    @Column(name = "campaign_entity_ids", length = 127)
    private String campaignEntityIds;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "expression", nullable = false, length = Integer.MAX_VALUE)
    private String expression;

    @Column(name = "is_participation_criterion", nullable = false, length = 1)
    @ColumnDefault("0")
    @Check(constraints = "is_participation_criterion in (0,1)")
    private byte isParticipationCriterion = 0;

    @Column(name = "is_visible", nullable = false, length = 1)
    @ColumnDefault("1")
    @Check(constraints = "is_visible in (0,1)")
    private byte isVisible = 1;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("1")
    private int numericOrder = 1;

    @Column(name = "calculated_at")
    private LocalDateTime calculatedAt;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_kpi_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CampaignKpi campaignKpi;

    protected static final long TABLE_ID = 16009;
    public static long getTableId() {
        return TABLE_ID;
    }
}
