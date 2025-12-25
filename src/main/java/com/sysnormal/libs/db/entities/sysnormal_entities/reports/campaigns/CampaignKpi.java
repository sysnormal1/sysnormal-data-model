package com.sysnormal.libs.db.entities.sysnormal_entities.reports.campaigns;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(
        name = "campaign_kpis",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "campaign_kpis_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "campaign_id",
                                "name"
                        }
                )
        }
)
public class CampaignKpi extends BaseBasicEntity<CampaignKpi> {

    @Column(name = "campaign_id", nullable = false)
    private Long campaignId;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "is_visible", nullable = false, length = 1)
    @ColumnDefault("1")
    @Check(constraints = "is_visible in (0,1)")
    private byte isParticipationCriterion = 1;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("1")
    private int numericOrder = 1;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Campaign campaign;

    protected static final long TABLE_ID = 16005;
    public static long getTableId() {
        return TABLE_ID;
    }
}
