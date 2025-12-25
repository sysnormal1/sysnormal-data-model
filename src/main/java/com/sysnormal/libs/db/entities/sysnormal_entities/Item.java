package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import com.sysnormal.libs.db.entities.basic_entities.commons.MeasurementUnit;
import com.sysnormal.libs.db.entities.basic_entities.people.suppliers.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
        name = "items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "items_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "identifier_type_id", "identifier"
                        }
                )
        },
        indexes = {
                @Index(name = "idx_items_for_origin_without_table_id", columnList = "data_origin_id,id_at_origin")
        }
)

public class Item extends BaseBasicEntity<Item> {

    @Column(name = "identifier_type_id", nullable = false)
    private Long identifierTypeId;

    @Column(name = "identifier", nullable = false, length = 127)
    private String identifier;

    @Column(name = "ncm_id")
    private Long ncmId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "measurement_unit_id")
    private Long measurementUnitId;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "net_weight_un", precision = 32, scale = 12)
    private BigDecimal netWeightUn;

    @Column(name = "gross_weight_un", precision = 32, scale = 12)
    private BigDecimal grossWeightUn;

    @Column(name = "storage_temperature", precision = 32, scale = 12)
    private BigDecimal storageTemperature;

    @Column(name = "default_expiration_time")
    private Integer defaultExpirationTime;

    @Column(name = "turn_curve", length = 10)
    private String turnCurve;

    @Column(name = "item_business_origin_id")
    private Long itemBusinessOriginId;

    @Column(name = "item_category_origin_id")
    private Long itemCategoryOriginId;

    @Column(name = "notes")
    private String notes;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", insertable = false, updatable = false)
    private IdentifierType identifierType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ncm_id", insertable = false, updatable = false)
    private Ncm ncm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private ItemDepartment department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_business_origin_id", insertable = false, updatable = false)
    private ItemBusinessOrigin itemBusinessOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_category_origin_id", insertable = false, updatable = false)
    private ItemCategoryOrigin itemCategoryOrigin;

    protected static final long TABLE_ID = 8010L;
    public static long getTableId() {
        return TABLE_ID;
    }
}
