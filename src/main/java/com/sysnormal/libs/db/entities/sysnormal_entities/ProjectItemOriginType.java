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
        name = "project_item_origin_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "project_item_origin_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class ProjectItemOriginType extends BaseBasicEntity<ProjectItemOriginType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_system", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_system in (0,1)")
    private byte isSystem = 0;

    @Column(name = "notes")
    private String notes;

    protected static final long TABLE_ID = 15005;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long SYSTEM_ID = 0;
    public static final long USER_ID = 1;

    public static final ProjectItemOriginType SYSTEM = new ProjectItemOriginType(){{
        setId(SYSTEM_ID);
        setIsSysRec((byte) 1);
        setName("SYSTEM");
        setIsSystem((byte) 1);
    }};

    public static final ProjectItemOriginType USER = new ProjectItemOriginType(){{
        setId(USER_ID);
        setIsSysRec((byte) 1);
        setName("USER");
        setIsSystem((byte) 0);
    }};



}