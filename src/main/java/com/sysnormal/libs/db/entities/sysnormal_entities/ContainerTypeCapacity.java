package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import com.sysnormal.libs.db.entities.basic_entities.commons.MeasurementUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
        name = "container_type_capacities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "container_type_capacities_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "container_type_id", "capacity_type_id"
                        }
                )
        }
)
public class ContainerTypeCapacity extends BaseBasicEntity<ContainerTypeCapacity> {

    @Column(name = "container_type_id", nullable = false)
    private Long containerTypeId;

    @Column(name = "capacity_type_id", nullable = false)
    private Long capacityTypeId;

    @Column(name = "measurement_unit_id", nullable = false)
    private Long measurementUnitId;

    @Column(name = "capacity", precision = 38, scale = 10, nullable = false)
    @ColumnDefault("0")
    private BigDecimal capacity = BigDecimal.ZERO;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_type_id", updatable = false, insertable = false)
    private ContainerType containerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capacity_type_id", updatable = false, insertable = false)
    private IdentifierType capacityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", updatable = false, insertable = false)
    private MeasurementUnit measurementUnit;

    protected static final long TABLE_ID = 8004;
    public static long getTableId() {
        return TABLE_ID;
    }
}
