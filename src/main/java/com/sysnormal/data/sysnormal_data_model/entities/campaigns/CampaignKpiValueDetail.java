package com.sysnormal.data.sysnormal_data_model.entities.campaigns;

import com.sysnormal.data.basic_data_model.entities.commons.entityType.EntityType;
import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
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
        name = "campaign_kpi_value_details",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "campaign_kpi_value_details_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "campaign_kpi_value_getter_id",
                                "entity_type_id"
                        }
                )
        }
)
public class CampaignKpiValueDetail extends BaseSysnormalEntity<CampaignKpiValueDetail> {

    @Column(name = "campaign_kpi_value_getter_id", nullable = false)
    private Long campaignKpiValueGetterId;

    @Column(name = "entity_type_id", nullable = false)
    private Long entityTypeId;

    @Column(name = "is_never_selled", nullable = false, length = 1)
    @ColumnDefault("0")
    @Check(constraints = "is_never_selled in (0,1)")
    private byte considerReturns = 0;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_kpi_value_getter_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CampaignKpiValueGetter campaignKpiValueGetter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id", updatable = false, insertable = false)
    private EntityType entityType;


}
