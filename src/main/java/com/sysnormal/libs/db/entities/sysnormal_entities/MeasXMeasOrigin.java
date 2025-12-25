package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.MeasurementUnit;
import com.sysnormal.libs.db.entities.basic_entities.people.suppliers.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "meas_x_meas_origins",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "meas_x_meas_origins_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "(coalesce(supplier_id,0))", "origin_measurement_unit"
                        }
                )
        }
)
public class MeasXMeasOrigin extends BaseBasicEntity<MeasXMeasOrigin> {

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "origin_measurement_unit", nullable = false, length = 127)
    private String originMeasurementUnit;

    @Column(name = "measurement_unit_id", nullable = false)
    private Long measurementUnitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;

    protected static final long TABLE_ID = 30801;
    public static long getTableId() {
        return TABLE_ID;
    }

}