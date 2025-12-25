package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "item_departments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "item_departments_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)

public class ItemDepartment extends BaseBasicEntity<ItemDepartment> {
    @Column(name = "name", nullable = false)
    private String name;

    protected static final long TABLE_ID = 8006L;
    public static long getTableId() {
        return TABLE_ID;
    }
}
