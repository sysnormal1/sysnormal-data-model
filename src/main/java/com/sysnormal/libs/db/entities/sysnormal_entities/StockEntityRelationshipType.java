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
        name = "stock_entity_relationship_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "stock_entity_relationship_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class StockEntityRelationshipType extends BaseBasicEntity<StockEntityRelationshipType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_origin", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_origin in (0,1)")
    private byte isOrigin = 0;

    @Column(name = "is_owner", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_owner in (0,1)")
    private byte isOwner = 0;

    @Column(name = "is_reserved", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_reserved in (0,1)")
    private byte isReserved = 0;

    @Column(name = "is_target", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_target in (0,1)")
    private byte isTarget = 0;



    protected static final long TABLE_ID = 8026;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long OWNER_ID = 1;

    public static final StockEntityRelationshipType OWNER = new StockEntityRelationshipType(){{
        setId(OWNER_ID);
        setIsSysRec((byte) 1);
        setName("OWNER");
        setIsOrigin((byte) 1);
    }};
}