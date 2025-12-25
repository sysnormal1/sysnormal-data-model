package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(
        name = "campaign_kpi_value_detail_entities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "campaign_kpi_value_detail_entities_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "campaign_kpi_value_detail_id",
                                "entity_id"
                        }
                )
        }
)
public class CampaignKpiValueDetailEntity extends BaseBasicEntity<CampaignKpiValueDetailEntity> {

    @Column(name = "campaign_kpi_value_detail_id", nullable = false)
    private Long campaignKpiValueDetailId;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "expression", length = Integer.MAX_VALUE)
    private String expression;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_kpi_value_detail_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CampaignKpiValueDetail campaignKpiValueDetail;

    protected static final long TABLE_ID = 16011;
    public static long getTableId() {
        return TABLE_ID;
    }
}
