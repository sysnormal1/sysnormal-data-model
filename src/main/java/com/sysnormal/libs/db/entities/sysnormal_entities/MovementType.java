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
        name = "movement_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movement_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name","(coalesce(sigla,' '))"
                        }
                )
        }
)
public class MovementType extends BaseBasicEntity<MovementType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sigla", length = 10)
    private String sigla;

    @Column(name = "is_input", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_input in (0,1)")
    private byte isInput = 0;

    @Column(name = "is_output", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_output in (0,1)")
    private byte isOutput = 0;

    @Column(name = "is_conference", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_conference in (0,1)")
    private byte isConference = 0;

    @Column(name = "is_internal", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_internal in (0,1)")
    private byte isInternal = 0;

    protected static final long TABLE_ID = 251L;
    public static long getTableId() {
        return TABLE_ID;
    }

    // Constantes equivalentes
    public static final long INPUT_ID = 1L;
    public static final long ADJUST_INPUT_ID = 10L;
    public static final long BONUS_INPUT_ID = 11L;
    public static final long INVENTORY_INPUT_ID = 12L;
    public static final long RETURN_INPUT_ID = 13L;
    public static final long SIMPLE_SHIPMENT_INPUT_ID = 14L;
    public static final long TRANSFERENCE_INPUT_ID = 15L;
    public static final long OUTPUT_ID = 2L;
    public static final long ADJUST_OUTPUT_ID = 20L;
    public static final long BONUS_OUTPUT_ID = 21L;
    public static final long INVENTORY_OUTPUT_ID = 22L;
    public static final long RETURN_OUTPUT_ID = 23L;
    public static final long SIMPLE_SHIPMENT_OUTPUT_ID = 24L;
    public static final long TRANSFERENCE_OUTPUT_ID = 25L;
    public static final long CONFERENCE_ID =3L;
    public static final long INTERNAL_ID = 4L;

    public static final MovementType INPUT = new MovementType(){{
        setId(INPUT_ID);
        setIsSysRec((byte) 1);
        setName("INPUT");
        setSigla("I");
        setIsInput((byte) 1);
    }};
    public static final MovementType ADJUST_INPUT = new MovementType(){{
        setId(ADJUST_INPUT_ID);
        setIsSysRec((byte) 1);
        setName("ADJUST INPUT");
        setSigla("AI");
        setIsInput((byte) 1);
    }};
    public static final MovementType BONUS_INPUT = new MovementType(){{
        setId(BONUS_INPUT_ID);
        setIsSysRec((byte) 1);
        setName("BONUS INPUT");
        setSigla("BI");
        setIsInput((byte) 1);
    }};
    public static final MovementType INVENTORY_INPUT = new MovementType(){{
        setId(INVENTORY_INPUT_ID);
        setIsSysRec((byte) 1);
        setName("INVENTORY INPUT");
        setSigla("II");
        setIsInput((byte) 1);
    }};
    public static final MovementType RETURN_INPUT = new MovementType(){{
        setId(RETURN_INPUT_ID);
        setIsSysRec((byte) 1);
        setName("RETURN INPUT");
        setSigla("RI");
        setIsInput((byte) 1);
    }};
    public static final MovementType SIMPLE_SHIPMENT_INPUT = new MovementType(){{
        setId(SIMPLE_SHIPMENT_INPUT_ID);
        setIsSysRec((byte) 1);
        setName("SIMPLE SHIPMENT INPUT");
        setSigla("SI");
        setIsInput((byte) 1);
    }};
    public static final MovementType TRANSFERENCE_INPUT = new MovementType(){{
        setId(TRANSFERENCE_INPUT_ID);
        setIsSysRec((byte) 1);
        setName("TRANSFERENCE INPUT");
        setSigla("TI");
        setIsInput((byte) 1);
    }};
    public static final MovementType OUTPUT = new MovementType(){{
        setId(OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("OUTPUT");
        setSigla("O");
        setIsOutput((byte) 1);
    }};
    public static final MovementType ADJUST_OUTPUT = new MovementType(){{
        setId(ADJUST_OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("ADJUST OUTPUT");
        setSigla("AO");
        setIsOutput((byte) 1);
    }};
    public static final MovementType BONUS_OUTPUT = new MovementType(){{
        setId(BONUS_OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("BONUS OUTPUT");
        setSigla("BO");
        setIsOutput((byte) 1);
    }};
    public static final MovementType INVENTORY_OUTPUT = new MovementType(){{
        setId(INVENTORY_OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("INVENTORY OUTPUT");
        setSigla("IO");
        setIsOutput((byte) 1);
    }};
    public static final MovementType RETURN_OUTPUT = new MovementType(){{
        setId(RETURN_OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("RETURN OUTPUT");
        setSigla("RO");
        setIsOutput((byte) 1);
    }};
    public static final MovementType SIMPLE_SHIPMENT_OUTPUT = new MovementType(){{
        setId(SIMPLE_SHIPMENT_OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("SIMPLE SHIPMENT OUTPUT");
        setSigla("SO");
        setIsOutput((byte) 1);
    }};
    public static final MovementType TRANSFERENCE_OUTPUT = new MovementType(){{
        setId(TRANSFERENCE_OUTPUT_ID);
        setIsSysRec((byte) 1);
        setName("TRANSFERENCE OUTPUT");
        setSigla("TO");
        setIsOutput((byte) 1);
    }};
    public static final MovementType CONFERENCE = new MovementType(){{
        setId(CONFERENCE_ID);
        setIsSysRec((byte) 1);
        setName("CONFERENCE");
        setSigla("C");
        setIsConference((byte) 1);
    }};
    public static final MovementType INTERNAL = new MovementType(){{
        setId(INTERNAL_ID);
        setIsSysRec((byte) 1);
        setName("INTERNAL");
        setSigla("IT");
        setIsInternal((byte) 1);
    }};
}