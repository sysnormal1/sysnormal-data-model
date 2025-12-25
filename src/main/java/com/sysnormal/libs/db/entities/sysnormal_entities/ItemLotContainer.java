package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "items_lots_containers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "items_lots_containers_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "item_id",
                                "lot_id",
                                "container_id"
                        }
                )
        }
)
public class ItemLotContainer extends BaseBasicEntity<ItemLotContainer> {

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "lot_id", nullable = false)
    private Long lotId;

    @Column(name = "container_id")
    private Long containerId;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id", insertable = false, updatable = false)
    private Lot lot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_id", insertable = false, updatable = false)
    private Container container;

    protected static final long TABLE_ID = 8020;

    public static long getTableId() {
        return TABLE_ID;
    }
}