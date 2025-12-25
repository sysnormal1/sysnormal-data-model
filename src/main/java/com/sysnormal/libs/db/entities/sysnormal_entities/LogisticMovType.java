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
        name = "logistic_mov_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "logistic_mov_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class LogisticMovType extends BaseBasicEntity<LogisticMovType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_input", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_input in (0,1)")
    private byte isInput = 0;

    @Column(name = "is_output", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_output in (0,1)")
    private byte isOutput = 0;


    protected static final long TABLE_ID = 9001L;
    public static long getTableId() {
        return TABLE_ID;
    }

    // Constantes equivalentes
    public static final long DELIVERY_ID = 1;
    public static final long COLLECT_ID = 2;
    public static final long RETREAT_ID = 3;

    public static final LogisticMovType DELIVERY = new LogisticMovType(){{
        setId(DELIVERY_ID);
        setIsSysRec((byte) 1);
        setName("DELIVERY");
        setIsOutput((byte) 1);
    }};
    public static final LogisticMovType COLLECT = new LogisticMovType(){{
        setId(COLLECT_ID);
        setIsSysRec((byte) 1);
        setName("COLLECT");
        setIsInput((byte) 1);
    }};
    public static final LogisticMovType RETREAT = new LogisticMovType(){{
        setId(RETREAT_ID);
        setIsSysRec((byte) 1);
        setName("RETREAT");
        setIsInput((byte) 1);
    }};
}