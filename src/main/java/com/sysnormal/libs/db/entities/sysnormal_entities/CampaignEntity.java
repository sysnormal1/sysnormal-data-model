package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "campaign_entities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "campaign_entities_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "campaign_id",
                                "entity_id",
                                "alias"
                        }
                )
        }
)
public class CampaignEntity extends BaseBasicEntity<CampaignEntity> {

    @Column(name = "campaign_id", nullable = false)
    private Long campaignId;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "alias", nullable = false)
    private String alias;

    @Column(name = "start_at")
    private LocalDateTime startAt;
    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Campaign campaign;

    protected static final long TABLE_ID = 16001;
    public static long getTableId() {
        return TABLE_ID;
    }
}
