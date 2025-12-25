package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.EntityType;
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
        name = "campaigns",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "campaigns_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name",
                                "start_at",
                                "end_at"
                        }
                )
        }
)
public class Campaign extends BaseBasicEntity<Campaign> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "entity_type_id", nullable = false)
    private Long entityTypeId;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntityType entityType;

    protected static final long TABLE_ID = 16000;
    public static long getTableId() {
        return TABLE_ID;
    }
}
