package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(
        name = "movements_entities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movements_entities_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "mov_id",
                                "movement_relationship_type_id",
                                "stock_entity_id"
                        }
                )
        }
)
public class MovementEntity extends BaseBasicEntity<MovementEntity> {

    @Column(name = "mov_id", nullable = false)
    private Long movId;

    @Column(name = "movement_relationship_type_id", nullable = false)
    private Long movementRelationshipTypeId;

    @Column(name = "stock_entity_id", nullable = false)
    private Long stockEntityId;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("0")
    private Integer numericOrder = 0;

    @Column(name = "precedence", nullable = false)
    @ColumnDefault("0")
    private Integer precedence = 0;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movement movement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_relationship_type_id", insertable = false, updatable = false)
    private MovementEntityRelationshipType movementRelationshipType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_entity_id", insertable = false, updatable = false)
    private StockEntity stockEntity;

    protected static final long TABLE_ID = 9021;
    public static long getTableId() {
        return TABLE_ID;
    }

}
