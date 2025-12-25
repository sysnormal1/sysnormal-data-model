package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(
        name = "projects_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "projects_items_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "project_item_type_id","identifier"
                        }
                )
        }
)
public class ProjectItem extends BaseBasicEntity<ProjectItem> {

    @Column(name = "project_item_type_id", nullable = false)
    @ColumnDefault(ProjectItemType.REQUIREMENTS_ID + "")
    private Long projectItemTypeId = ProjectItemType.REQUIREMENTS_ID;

    @Column(name = "project_item_origin_id",nullable = false)
    @ColumnDefault(ProjectItemOriginType.USER_ID+ "")
    private Long projectItemOriginId = ProjectItemOriginType.USER_ID;

    @Column(name = "identifier", nullable = false, length = 127)
    private String identifier;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_item_type_id", insertable = false, updatable = false)
    private ProjectItemType projectItemType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_item_origin_id", insertable = false, updatable = false)
    private ProjectItemOriginType projectItemOrigin;

    protected static final long TABLE_ID = 15010;
    public static long getTableId() {
        return TABLE_ID;
    }

}
