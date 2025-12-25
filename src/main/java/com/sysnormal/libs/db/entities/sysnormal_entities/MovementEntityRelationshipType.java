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
        name = "movement_entity_relationship_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movement_entity_relationship_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class MovementEntityRelationshipType extends BaseBasicEntity<MovementEntityRelationshipType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_origin", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_origin in (0,1)")
    private byte isOrigin = 0;

    @Column(name = "is_target", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_target in (0,1)")
    private byte isTarget = 0;

    @Column(name = "is_input", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_input in (0,1)")
    private byte isInput = 0;

    @Column(name = "is_output", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_output in (0,1)")
    private byte isOutput = 0;




    protected static final long TABLE_ID = 9020;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long ORIGIN_INPUT_ID = 1;
    public static final long TARGET_INPUT_ID = 2;
    public static final long ORIGIN_OUTPUT_ID = 3;
    public static final long TARGET_OUTPUT_ID = 4;

    public static final MovementEntityRelationshipType ORIGIN_INPUT = new MovementEntityRelationshipType(){{
        setId(ORIGIN_INPUT_ID);
        setIsSysRec((byte) 1);
        setName("ORIGIN_INPUT");
        setIsOrigin((byte) 1);
        setIsTarget((byte) 0);
        setIsInput((byte) 1);
        setIsOutput((byte) 0);
    }};
    public static final MovementEntityRelationshipType TARGET_INPUT = new MovementEntityRelationshipType(){{
        setId(TARGET_INPUT_ID);
        setIsSysRec((byte) 1);
        setName("TARGET_INPUT");
        setIsOrigin((byte) 0);
        setIsTarget((byte) 1);
        setIsInput((byte) 1);
        setIsOutput((byte) 0);
    }};
    public static final MovementEntityRelationshipType ORIGIN_OUTPUT = new MovementEntityRelationshipType(){{
        setId(ORIGIN_OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("ORIGIN_OUTPUT");
        setIsOrigin((byte) 1);
        setIsTarget((byte) 0);
        setIsInput((byte) 0);
        setIsOutput((byte) 1);
    }};
    public static final MovementEntityRelationshipType TARGET_OUTPUT = new MovementEntityRelationshipType(){{
        setId(TARGET_OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("TARGET_OUTPUT");
        setIsOrigin((byte) 0);
        setIsTarget((byte) 1);
        setIsInput((byte) 0);
        setIsOutput((byte) 1);
    }};

}