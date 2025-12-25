package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Table(
        name = "warehouse_address_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "warehouse_address_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class WarehouseAddressType extends BaseBasicEntity<WarehouseAddressType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "is_storable", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "is_storable in (0,1)")
    private byte isStorable = 1;

    @Column(name = "is_passable", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_passable in (0,1)")
    private byte isPassable = 0;

    protected static final long TABLE_ID = 3003;
    public static long getTableId() {
        return TABLE_ID;
    }

}