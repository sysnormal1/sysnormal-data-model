package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "gtin_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "gtin_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name",
                                "(coalesce(characters,0))"
                        }
                )
        }
)
public class GtinType extends BaseBasicEntity<GtinType> {

    @Column(name = "name", nullable = false,length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "characters")
    private Long characters;


    protected static final long TABLE_ID = 8007;

    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long UNDEFINED = 1;
    public static final long GTIN8 = 8;
    public static final long GTIN12 = 12;
    public static final long GTIN13 = 13;
    public static final long GTIN14 = 14;

}