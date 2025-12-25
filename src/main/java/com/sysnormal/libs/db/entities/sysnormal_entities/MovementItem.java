package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.ActionStatus;
import com.sysnormal.libs.db.entities.basic_entities.commons.MeasurementUnit;
import com.sysnormal.libs.db.entities.basic_entities.people.suppliers.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "movement_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movement_items_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "movement_id",
                                "item_id",
                                "(coalesce(mov_type_id,0))",
                                "num_ord"
                        }
                )
        },
        indexes = {
                @Index(name = "idx_movement_items_for_origin", columnList = "data_origin_id,table_origin_id,id_at_origin"),
                @Index(name = "idx_movement_items_customized_report_base", columnList = "movement_id,status_reg_id,canceled_at,deleted_at"),
        }
)
public class MovementItem extends BaseBasicEntity<MovementItem> {

    @Column(name = "movement_id", nullable = false)
    private Long movementId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "mov_type_id")
    private Long movTypeId;

    @Column(name = "mov_status_id")
    private Long movStatusId;

    @Column(name = "ncm_id")
    private Long ncmId;

    @Column(name = "fiscal_code_id")
    private Long fiscalCodeId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "measurement_unit_id", nullable = false)
    @ColumnDefault(MeasurementUnit.WT_ID + "")
    private Long measurementUnitId = MeasurementUnit.WT_ID;

    @Column(name = "qty", nullable = false, precision = 32, scale = 10)
    @ColumnDefault("0")
    private BigDecimal qty = BigDecimal.ZERO;

    @Column(name = "un_price_table", precision = 32, scale = 10)
    private BigDecimal unPriceTable;

    @Column(name = "un_price", nullable = false, precision = 32, scale = 10)
    @ColumnDefault("0")
    private BigDecimal unPrice = BigDecimal.ZERO;

    @Column(name = "mov_started_at")
    private LocalDateTime movStartedAt;

    @Column(name = "mov_ended_at")
    private LocalDateTime movEndedAt;

    @Column(name = "net_weight_un", precision = 32, scale = 10)
    private BigDecimal netWeightUn;

    @Column(name = "gross_weight_un", precision = 32, scale = 10)
    private BigDecimal grossWeightUn;

    @Column(name = "qty_returned", precision = 32, scale = 10)
    private BigDecimal qtyReturned;

    @Column(name = "num_ord", nullable = false)
    @ColumnDefault("0")
    private Integer numOrd = 0;

    @Column(name = "financial_discount_percent", precision = 38, scale = 10)
    private BigDecimal financialDiscountPercent;

    @Column(name = "financial_discount_value", precision = 38, scale = 10)
    private BigDecimal financialDiscountValue;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movement movement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_type_id", insertable = false, updatable = false)
    private MovementType movType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_status_id", insertable = false, updatable = false)
    private ActionStatus movStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ncm_id", insertable = false, updatable = false)
    private Ncm ncm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fiscal_code_id", insertable = false, updatable = false)
    private FiscalCode fiscalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private ItemDepartment department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;

    protected static final long TABLE_ID = 9029L;
    public static long getTableId() {
        return TABLE_ID;
    }

}
