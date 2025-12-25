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
        name = "conference_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "conference_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class ConferenceType extends BaseBasicEntity<ConferenceType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cega", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "cega in (0,1)")
    private byte cega = 0;

    @Column(name = "semicega", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "semicega in (0,1)")
    private byte semicega = 0;

    @Column(name = "normal", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "normal in (0,1)")
    private byte normal = 0;


    protected static final long TABLE_ID = 9004L;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long NORMAL_ID = 1;
    public static final long CEGA_ID = 2;
    public static final long SEMICEGA_ID = 3;
    public static final long XML_WITH_LOT_ID = 4;
    public static final long XML_WITHOUT_LOT_ID = 5;

    // Constantes equivalentes
    public static final ConferenceType NORMAL = new ConferenceType(){{
        setId(NORMAL_ID);
        setIsSysRec((byte) 1);
        setName("NORMAL");
        setNormal((byte)1);
    }};
    public static final ConferenceType CEGA = new ConferenceType(){{
        setId(CEGA_ID);
        setIsSysRec((byte) 1);
        setName("CEGA");
        setCega((byte)1);
    }};
    public static final ConferenceType SEMICEGA = new ConferenceType(){{
        setId(SEMICEGA_ID);
        setIsSysRec((byte) 1);
        setName("SEMICEGA");
        setSemicega((byte)1);
    }};
    public static final ConferenceType XML_WITH_LOT = new ConferenceType(){{
        setId(XML_WITH_LOT_ID);
        setIsSysRec((byte) 1);
        setName("XML_WITH_LOT");
        setNormal((byte)1);
    }};
    public static final ConferenceType XML_WITHOUT_LOT = new ConferenceType(){{
        setId(XML_WITHOUT_LOT_ID);
        setIsSysRec((byte) 1);
        setName("XML_WITHOUT_LOT");
        setSemicega((byte)1);
    }};

}