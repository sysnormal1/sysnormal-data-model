package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.ActionStatus;
import com.sysnormal.libs.db.entities.basic_entities.commons.Assets;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import com.sysnormal.libs.db.entities.basic_entities.logistic.LogisticReason;
import com.sysnormal.libs.db.entities.basic_entities.logistic.LogisticStatus;
import com.sysnormal.libs.db.entities.basic_entities.people.collaborators.Collaborator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "logistic_orders",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "logistic_orders_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "logistic_mov_type_id",
                                "(coalesce(identifier_type_id,0))",
                                "(coalesce(identifier,'NULL'))"
                        }
                )
        }
)
public class LogisticOrder extends BaseBasicEntity<LogisticOrder> {

    @Column(name = "logistic_mov_type_id", nullable = false)
    private Long logisticMovTypeId;

    @Column(name = "identifier_type_id")
    private Long identifierTypeId;

    @Column(name = "identifier", length = 127)
    private String identifier;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "action_status_id")
    @ColumnDefault(ActionStatus.NOT_STARTED_ID+"")
    private Long actionStatusId = ActionStatus.NOT_STARTED_ID;

    @Column(name = "logistic_status_id", nullable = false)
    @ColumnDefault(LogisticStatus.TO_DELIVERY_ID+"")
    private Long logisticStatusId = LogisticStatus.TO_DELIVERY_ID;

    @Column(name = "unmoved_reason_id")
    private Long unmovedReasonId;

    @Column(name = "collected_reason_id")
    private Long collectedReasonId;

    @Column(name = "unmoved_qty_notes")
    private String unmovedQtyNotes;

    @Column(name = "collected_qty_notes")
    private String collectedQtyNotes;

    @Column(name = "mov_started_at")
    private LocalDateTime movStartedAt;

    @Column(name = "mov_ended_at")
    private LocalDateTime movEndedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_mov_type_id", insertable = false, updatable = false)
    private LogisticMovType logisticMovType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", insertable = false, updatable = false)
    private IdentifierType identifierType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private Assets vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", insertable = false, updatable = false)
    private Collaborator driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_status_id", insertable = false, updatable = false)
    private ActionStatus actionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_status_id", insertable = false, updatable = false)
    private LogisticStatus logisticStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unmoved_reason_id", insertable = false, updatable = false)
    private LogisticReason unmovedReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collected_reason_id", insertable = false, updatable = false)
    private LogisticReason collectedReason;

    protected static final long TABLE_ID = 9005L;
    public static long getTableId() {
        return TABLE_ID;
    }

}
