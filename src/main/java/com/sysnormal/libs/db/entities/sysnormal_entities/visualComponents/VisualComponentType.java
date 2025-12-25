package com.sysnormal.libs.db.entities.sysnormal_entities.visualComponents;

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
        name = "visual_components_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "visual_components_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class VisualComponentType extends BaseBasicEntity<VisualComponentType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "is_module", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_module in (0,1)")
    private byte isModule = 0;

    @Column(name = "is_screen", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_screen in (0,1)")
    private byte isScreen = 0;

    @Column(name = "is_component", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_component in (0,1)")
    private byte isComponent = 0;

    @Column(name = "notes")
    private String notes;

    protected static final long TABLE_ID = 400;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long MODULE_ID = 1;
    public static final long SCREEN_ID = 2;
    public static final long COMPONENT_ID = 3;

    public static final VisualComponentType MODULE = new VisualComponentType(){{
        setId(MODULE_ID);
        setIsSysRec((byte) 1);
        setName("MODULE");
        setIsModule((byte) 1);
    }};
    public static final VisualComponentType SCREEN = new VisualComponentType(){{
        setId(SCREEN_ID);
        setIsSysRec((byte) 1);
        setName("SCREEN");
        setIsScreen((byte) 1);
    }};
    public static final VisualComponentType COMPONENT = new VisualComponentType(){{
        setId(COMPONENT_ID);
        setIsSysRec((byte) 1);
        setName("COMPONENT");
        setIsComponent((byte) 1);
    }};


}