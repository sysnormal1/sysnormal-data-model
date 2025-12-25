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
        name = "item_meas_pack_identif",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "item_meas_pack_identif_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "item_id",
                                "packaging_id",
                                "(COALESCE(measurement_unit_id,0))",
                                "(COALESCE(identifier_type_id,0))",
                                "(COALESCE(identifier,'NULL'))",
                                "multiplier",
                                "(COALESCE(stock_item_id,0))",
                                "(COALESCE(stock_entity_id,0))"
                        }
                )
        }
)
public class ItemMeasPackIdentif extends BaseBasicEntity<ItemMeasPackIdentif> {

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "packaging_id", nullable = false)
    private Long packagingId;

    @Column(name = "measurement_unit_id", nullable = false)
    private Long measurementUnitId;

    @Column(name = "unit_weight", precision = 38, scale = 10)
    private BigDecimal unitWeight;

    @Column(name = "package_weight", precision = 38, scale = 10)
    private BigDecimal packageWeight;

    @Column(name = "unit_volume", precision = 38, scale = 10)
    private BigDecimal unitVolume;

    @Column(name = "package_volume", precision = 38, scale = 10)
    private BigDecimal packageVolume;

    @Column(name = "identifier_type_id")
    private Long identifierTypeId;

    @Column(name = "identifier",length = 127)
    private String identifier;

    @Column(name = "multiplier", nullable = false, precision = 38, scale = 10)
    @ColumnDefault("1")
    private BigDecimal multiplier = BigDecimal.ONE;

    @Column(name = "stock_item_id")
    private Long stockItemId;

    @Column(name = "stock_entity_id")
    private Long stockEntityId;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("1")
    private Long numericOrder = 1L;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", insertable = false, updatable = false)
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", insertable = false, updatable = false)
    private IdentifierType identifierType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_item_id", insertable = false, updatable = false)
    private ItemStock itemStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_entity_id", insertable = false, updatable = false)
    private StockEntity stockEntity;

    protected static final long TABLE_ID = 8032;

    public static long getTableId() {
        return TABLE_ID;
    }
}