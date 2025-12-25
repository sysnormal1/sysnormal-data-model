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
        name = "item_status",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "item_status_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name",
                                "(coalesce(sigla,'NULL'))"
                        }
                )
        }
)
public class ItemStatus extends BaseBasicEntity<ItemStatus> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sigla", length = 10)
    private String sigla;

    @Column(name = "is_disponible", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "is_disponible in (0,1)")
    private byte isDisponible = 1;

    @Column(name = "is_damaged", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_damaged in (0,1)")
    private byte isDamaged = 0;



    protected static final long TABLE_ID = 8009;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long NORMAL_ID = 1;
    public static final long DAMAGED_ID = 2;

    public static final ItemStatus NORMAL = new ItemStatus(){{
        setId(NORMAL_ID);
        setIsSysRec((byte) 1);
        setName("NORMAL");
        setIsDisponible((byte) 1);
        setIsDamaged((byte) 0);
    }};
    public static final ItemStatus DAMAGED = new ItemStatus(){{
        setId(DAMAGED_ID);
        setIsSysRec((byte) 1);
        setName("DAMAGED");
        setIsDisponible((byte) 0);
        setIsDamaged((byte) 1);
    }};

}