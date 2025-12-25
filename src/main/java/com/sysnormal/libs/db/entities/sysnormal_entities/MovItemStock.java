package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.ActionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "movs_items_stocks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movs_items_stocks_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "mov_id",
                                "type_mov_id",
                                "stock_item_id"
                        }
                )
        }
)
public class MovItemStock extends BaseBasicEntity<MovItemStock> {

    @Column(name = "mov_id", nullable = false)
    private Long movId;

    @Column(name = "type_mov_id", nullable = false)
    private Long typeMovId;

    @Column(name = "stock_item_id", nullable = false)
    private Long stockItemId;

    @Column(name = "status_mov_id", nullable = false)
    @ColumnDefault(ActionStatus.NOT_STARTED_ID + "")
    private Long statusMovId = ActionStatus.NOT_STARTED_ID;

    @Column(name = "mov_started_at")
    private LocalDateTime movStartedAt;

    @Column(name = "mov_ended_at")
    private LocalDateTime movEndedAt;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("0")
    private Long numericOrder = 0L;

    @Column(name = "precedence", nullable = false)
    @ColumnDefault("0")
    private Long precedence = 0L;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movement mov;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_mov_id", insertable = false, updatable = false)
    private MovementType typeMov;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_item_id", insertable = false, updatable = false)
    private ItemStock stockItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_mov_id", insertable = false, updatable = false)
    private ActionStatus statusMov;

    protected static final long TABLE_ID = 9030;

    public static long getTableId() {
        return TABLE_ID;
    }
}