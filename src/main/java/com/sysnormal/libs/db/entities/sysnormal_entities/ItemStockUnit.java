package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import com.sysnormal.libs.db.entities.basic_entities.commons.MeasurementUnit;
import com.sysnormal.libs.db.entities.basic_entities.commons.Packaging;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(
        name = "item_stock_units",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "item_stock_units_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "stock_item_id",
                                "(coalesce(identifier,'NULL'))"
                        }
                )
        }
)
public class ItemStockUnit extends BaseBasicEntity<ItemStockUnit> {

    @Column(name = "stock_item_id", nullable = false)
    private Long stockItemId;

    @Column(name = "identifier_type_id")
    private Long identifierTypeId;

    @Column(name = "identifier", length = 127)
    private String identifier;

    @Column(name = "item_unit_status_id", nullable = false)
    @ColumnDefault(ItemStatus.NORMAL_ID + "")
    private Long itemStockStatusId = ItemStatus.NORMAL_ID;

    @Column(name = "measurement_unit_id", nullable = false)
    private Long measurementUnitId;

    @Column(name = "packaging_id", nullable = false)
    private Long packagingId;

    @Column(name = "unit_weight", precision = 38, scale = 10)
    private BigDecimal unitWeight;

    @Column(name = "package_weight", precision = 38, scale = 10)
    private BigDecimal packageWeight;

    @Column(name = "unit_volume", precision = 38, scale = 10)
    private BigDecimal unitVolume;

    @Column(name = "package_volume", precision = 38, scale = 10)
    private BigDecimal packageVolume;

    @Column(name = "amount", nullable = false, precision = 38, scale = 10)
    @ColumnDefault("0")
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "variable_unit_measure_id")
    private Long variableUnitMeasureId;

    @Column(name = "variable_amount", precision = 38, scale = 10)
    private BigDecimal variableAmount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_item_id", insertable = false, updatable = false)
    private ItemStock stockItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", insertable = false, updatable = false)
    private IdentifierType identifierType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_unit_status_id", insertable = false, updatable = false)
    private ItemStatus itemUnitStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", insertable = false, updatable = false)
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variable_unit_measure_id", insertable = false, updatable = false)
    private MeasurementUnit variableUnitMeasure;

    protected static final long TABLE_ID = 8031;

    public static long getTableId() {
        return TABLE_ID;
    }
}