package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
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
        name = "item_stocks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "item_stocks_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "item_lot_container_id",
                                "stock_relationship_type_id",
                                "stock_entity_id",
                                "(coalesce(warehouse_address_id,0))",
                                "item_stock_status_id",
                                "measurement_unit_id",
                                "packaging_id"
                        }
                )
        }
)
public class ItemStock extends BaseBasicEntity<ItemStock> {

    @Column(name = "item_lot_container_id", nullable = false)
    private Long itemLotContainerId;

    @Column(name = "stock_relationship_type_id", nullable = false)
    @ColumnDefault(StockEntityRelationshipType.OWNER_ID + "")
    private Long stockRelationshipTypeId = StockEntityRelationshipType.OWNER_ID;

    @Column(name = "stock_entity_id", nullable = false)
    private Long stockEntityId;

    @Column(name = "warehouse_address_id")
    private Long warehouseAddressId;

    @Column(name = "item_stock_status_id", nullable = false)
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

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("1")
    private Long numericOrder = 1L;

    @Column(name = "precedence", nullable = false)
    @ColumnDefault("0")
    private Long precedence = 0L;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_lot_container_id", insertable = false, updatable = false)
    private ItemLotContainer itemLotContainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_relationship_type_id", insertable = false, updatable = false)
    private StockEntityRelationshipType stockRelationshipType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_entity_id", insertable = false, updatable = false)
    private StockEntity stockEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_address_id", insertable = false, updatable = false)
    private WarehouseAddress warehouseAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_stock_status_id", insertable = false, updatable = false)
    private ItemStatus itemStockStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", insertable = false, updatable = false)
    private Packaging packaging;

    protected static final long TABLE_ID = 8030;

    public static long getTableId() {
        return TABLE_ID;
    }
}