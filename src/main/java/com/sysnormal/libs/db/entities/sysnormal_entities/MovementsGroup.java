package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(
        name = "movements_groups",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movements_groups_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "movement_group_id",
                                "mov_id"
                        }
                )
        }
)
public class MovementsGroup extends BaseBasicEntity<MovementsGroup> {

    @Column(name = "movement_group_id", nullable = false)
    private Long movementGroupId;

    @Column(name = "mov_id", nullable = false)
    private Long movId;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("0")
    private Integer numericOrder = 0;

    @Column(name = "precedence", nullable = false)
    @ColumnDefault("0")
    private Integer precedence = 0;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_group_id", insertable = false, updatable = false)
    private MovementGroup movementGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_id", insertable = false, updatable = false)
    private Movement movement;

    protected static final long TABLE_ID = 9012;
    public static long getTableId() {
        return TABLE_ID;
    }

}
