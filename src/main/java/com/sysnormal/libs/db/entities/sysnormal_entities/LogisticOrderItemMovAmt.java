package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.ActionStatus;
import com.sysnormal.libs.db.entities.basic_entities.commons.MeasurementUnit;
import com.sysnormal.libs.db.entities.basic_entities.commons.Packaging;
import com.sysnormal.libs.db.entities.basic_entities.logistic.LogisticReason;
import com.sysnormal.libs.db.entities.basic_entities.logistic.LogisticStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "logistic_orders_items_mov_amt",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "logistic_orders_items_mov_amt_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "mov_logistic_order_id",
                                "item_mov_amt_id",
                                "(COALESCE(logistic_mov_type_id,0))",
                                "action_status_id",
                                "(COALESCE(type_mov_id,0))",
                                "(COALESCE(measurement_unit_id,0))",
                                "(COALESCE(packaging_id,0))"
                        }
                )
        }
)
public class LogisticOrderItemMovAmt extends BaseBasicEntity<LogisticOrderItemMovAmt> {

    @Column(name = "mov_logistic_order_id", nullable = false)
    private Long movLogisticOrderId;

    @Column(name = "item_mov_amt_id", nullable = false)
    private Long itemMovAmtId;

    @Column(name = "logistic_mov_type_id")
    private Long logisticMovTypeId;

    @Column(name = "action_status_id", nullable = false)
    @ColumnDefault(ActionStatus.NOT_STARTED_ID + "")
    private Long actionStatusId = ActionStatus.NOT_STARTED_ID;

    @Column(name = "type_mov_id")
    private Long typeMovId;

    @Column(name = "logistic_status_id", nullable = false)
    @ColumnDefault(LogisticStatus.TO_DELIVERY_ID + "")
    private Long logisticStatusId = LogisticStatus.TO_DELIVERY_ID;

    @Column(name = "measurement_unit_id")
    private Long measurementUnitId;

    @Column(name = "packaging_id")
    private Long packagingId;

    @Column(name = "unit_weight", precision = 38, scale = 10)
    private BigDecimal unitWeight;

    @Column(name = "package_weight", precision = 38, scale = 10)
    private BigDecimal packageWeight;

    @Column(name = "unit_volume", precision = 38, scale = 10)
    private BigDecimal unitVolume;

    @Column(name = "package_volume", precision = 38, scale = 10)
    private BigDecimal packageVolume;

    @Column(name = "mov_started_at")
    private LocalDateTime movStartedAt;

    @Column(name = "mov_ended_at")
    private LocalDateTime movEndedAt;

    @Column(name = "expected_amt", precision = 38, scale = 10)
    private BigDecimal expectedAmt;

    @Column(name = "moved_amt", precision = 38, scale = 10)
    private BigDecimal movedAmt;

    @Column(name = "unmoved_qty", precision = 38, scale = 10)
    private BigDecimal unmovedQty;

    @Column(name = "collected_qty", precision = 38, scale = 10)
    private BigDecimal collectedQty;

    @Column(name = "unmoved_reason_id")
    private Long unmovedReasonId;

    @Column(name = "collected_reason_id")
    private Long collectedReasonId;

    @Column(name = "unmoved_qty_notes")
    private String unmovedQtyNotes;

    @Column(name = "collected_qty_notes")
    private String collectedQtyNotes;

    @Column(name = "unmoved_photos")
    private String unmovedPhotos;

    @Column(name = "collected_photos")
    private String collectedPhotos;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_logistic_order_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LogisticOrderMov movLogisticOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_mov_amt_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ItemMovAmount itemMovAmt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_mov_type_id", insertable = false, updatable = false)
    private LogisticMovType logisticMovType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_status_id", insertable = false, updatable = false)
    private ActionStatus actionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_mov_id", insertable = false, updatable = false)
    private MovementType typeMov;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_status_id", insertable = false, updatable = false)
    private LogisticStatus logisticStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", insertable = false, updatable = false)
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unmoved_reason_id", insertable = false, updatable = false)
    private LogisticReason unmovedReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collected_reason_id", insertable = false, updatable = false)
    private LogisticReason collectedReason;

    protected static final long TABLE_ID = 12005;

    public static long getTableId() {
        return TABLE_ID;
    }
}