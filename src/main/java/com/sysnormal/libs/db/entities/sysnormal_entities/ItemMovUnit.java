package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.ActionStatus;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import com.sysnormal.libs.db.entities.basic_entities.commons.MeasurementUnit;
import com.sysnormal.libs.db.entities.basic_entities.commons.Packaging;
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
        name = "item_mov_units",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "item_mov_units_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "item_mov_amt_id",
                                "(coalesce(identifier,'NULL'))"
                        }
                )
        }
)
public class ItemMovUnit extends BaseBasicEntity<ItemMovUnit> {

    @Column(name = "item_mov_amt_id", nullable = false)
    private Long itemMovAmtId;

    @Column(name = "identifier_type_id", nullable = false)
    private Long identifierTypeId;

    @Column(name = "identifier", length = 127)
    private String identifier;

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

    @Column(name = "status_mov_id", nullable = false)
    @ColumnDefault(ActionStatus.NOT_STARTED_ID + "")
    private Long statusMovId = ActionStatus.NOT_STARTED_ID;

    @Column(name = "mov_started_at")
    private LocalDateTime movStartedAt;

    @Column(name = "mov_ended_at")
    private LocalDateTime movEndedAt;

    @Column(name = "expected_amt", nullable = false, precision = 38, scale = 10)
    @ColumnDefault("0")
    private BigDecimal expectedAmt = BigDecimal.ZERO;

    @Column(name = "moved_amt", nullable = false, precision = 38, scale = 10)
    @ColumnDefault("0")
    private BigDecimal movedAmt = BigDecimal.ZERO;

    @Column(name = "variable_unit_measure_id")
    private Long variableUnitMeasureId;

    @Column(name = "variable_expected_amt", precision = 38, scale = 10)
    private BigDecimal variableExpectedAmt;

    @Column(name = "variable_moved_amt", precision = 38, scale = 10)
    private BigDecimal variableMovedAmt;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_mov_amt_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ItemMovAmount itemMovAmt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", insertable = false, updatable = false)
    private IdentifierType identifierType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", insertable = false, updatable = false)
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_mov_id", insertable = false, updatable = false)
    private ActionStatus statusMov;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variable_unit_measure_id", insertable = false, updatable = false)
    private MeasurementUnit variableUnitMeasure;

    protected static final long TABLE_ID = 9036;

    public static long getTableId() {
        return TABLE_ID;
    }
}