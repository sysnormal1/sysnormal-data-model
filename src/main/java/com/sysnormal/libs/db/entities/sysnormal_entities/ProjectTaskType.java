package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "project_task_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "project_task_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class ProjectTaskType extends BaseBasicEntity<ProjectTaskType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    protected static final long TABLE_ID = 15050;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long TASK_ID = 1;
    public static final long DOCUMENTATION_ID = 2;
    public static final long PLANNING_ID = 3;
    public static final long EXECUTION_ID = 4;
    public static final long DEVELOPMENT_ID = 5;
    public static final long IMPROVEMENT_ID = 6;
    public static final long CORRECTION_ID = 7;

    public static final ProjectTaskType TASK = new ProjectTaskType(){{
        setId(TASK_ID);
        setIsSysRec((byte) 1);
        setName("TASK");
    }};
    public static final ProjectTaskType DOCUMENTATION = new ProjectTaskType(){{
        setId(DOCUMENTATION_ID);
        setIsSysRec((byte) 1);
        setName("DOCUMENTATION");
    }};
    public static final ProjectTaskType PLANNING = new ProjectTaskType(){{
        setId(PLANNING_ID);
        setIsSysRec((byte) 1);
        setName("PLANNING");
    }};
    public static final ProjectTaskType EXECUTION = new ProjectTaskType(){{
        setId(EXECUTION_ID);
        setIsSysRec((byte) 1);
        setName("EXECUTION");
    }};
    public static final ProjectTaskType DEVELOPMENT = new ProjectTaskType(){{
        setId(DEVELOPMENT_ID);
        setIsSysRec((byte) 1);
        setName("DEVELOPMENT");
    }};
    public static final ProjectTaskType IMPROVEMENT = new ProjectTaskType(){{
        setId(IMPROVEMENT_ID);
        setIsSysRec((byte) 1);
        setName("IMPROVEMENT");
    }};
    public static final ProjectTaskType CORRECTION = new ProjectTaskType(){{
        setId(CORRECTION_ID);
        setIsSysRec((byte) 1);
        setName("CORRECTION");
    }};


}