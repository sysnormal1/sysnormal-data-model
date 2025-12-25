package com.sysnormal.libs.db.entities.sysnormal_entities.visualComponents;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigInteger;

@Entity
@Getter
@Setter
@Table(
        name = "visual_components",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "visual_components_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class VisualComponent extends BaseBasicEntity<VisualComponent> {

    private static final String RESOURCE_PATH = "db/migration/visual_components.json"; // expected on classpath


    @Column(name = "visual_component_type_id", nullable = false)
    @ColumnDefault(VisualComponentType.SCREEN_ID + "")
    private Long visualComponentTypeId = VisualComponentType.SCREEN_ID;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "icon", length = Integer.MAX_VALUE)
    private String icon;

    @Column(name = "path", length = Integer.MAX_VALUE)
    private String path;

    @Column(name = "numeric_order")
    private BigInteger numericOrder;

    @Column(name = "show_in_menu", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "show_in_menu in (0,1)")
    private byte showInMenu = 1;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visual_component_type_id", insertable = false, updatable = false)
    private VisualComponentType visualComponentType;


    protected static final long TABLE_ID = 401;
    public static long getTableId() {
        return TABLE_ID;
    }

}