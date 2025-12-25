package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import com.sysnormal.libs.db.entities.basic_entities.commons.MeasurementUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(
        name = "warehouse_address_capacities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "warehouse_address_capacities_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "warehouse_address_id",
                                "capacity_type_id"
                        }
                )
        }
)
public class WarehouseAddressCapacity extends BaseBasicEntity<WarehouseAddressCapacity> {

    @Column(name = "warehouse_address_id", nullable = false)
    private Long warehouseAddressId;

    @Column(name = "capacity_type_id", nullable = false)
    private Long capacityTypeId;

    @Column(name = "measurement_unit_id", nullable = false)
    private Long measurementUnitId;

    @Column(name = "capacity", nullable = false, precision = 38,scale = 10)
    @ColumnDefault("0")
    private BigDecimal capacity = BigDecimal.ZERO;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_address_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WarehouseAddress warehouseAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capacity_type_id", insertable = false, updatable = false)
    private IdentifierType capacitytype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;


    protected static final long TABLE_ID = 3007;
    public static long getTableId() {
        return TABLE_ID;
    }


}