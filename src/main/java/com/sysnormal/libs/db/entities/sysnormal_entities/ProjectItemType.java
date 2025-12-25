
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
        name = "projects_items_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "projects_items_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class ProjectItemType extends BaseBasicEntity<ProjectItemType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    protected static final long TABLE_ID = 15001;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long PROJECTS_ID = 1;
    public static final long PROJECT_ID = 2;
    public static final long PLANNINGS_ID = 20;
    public static final long PLANNING_ID = 21;
    public static final long MANAGEMENTS_ID = 30;
    public static final long MANAGEMENT_ID = 31;
    public static final long INICIATIVES_ID = 210;
    public static final long INICIATIVE_ID = 211;
    public static final long EPICS_ID = 2110;
    public static final long EPIC_ID = 2111;
    public static final long FEATURES_ID = 21110;
    public static final long FEATURE_ID = 21111;
    public static final long REQUIREMENTS_ID = 200000;
    public static final long REQUIREMENT_ID = 200001;
    public static final long AGILE_METHODOLOGIES_ID = 310;
    public static final long AGILE_METHODOLOGY_ID = 311;
    public static final long SCRUMS_ID = 3110;
    public static final long SCRUM_ID = 3111;
    public static final long BACKLOGS_ID = 31110;
    public static final long BACKLOG_ID = 31111;
    public static final long SPRINTS_ID = 31112;
    public static final long SPRINT_ID = 31113;
    public static final long TASKS_ID = 40000;
    public static final long TASK_ID = 40001;

    public static final ProjectItemType PROJECTS = new ProjectItemType(){{
        setId(PROJECTS_ID);
        setIsSysRec((byte) 1);
        setName("PROJECTS");
    }};
    public static final ProjectItemType PROJECT = new ProjectItemType(){{
        setId(PROJECT_ID);
        setIsSysRec((byte) 1);
        setParentId(PROJECTS_ID);
        setName("PROJECT");
    }};
    public static final ProjectItemType PLANNINGS = new ProjectItemType(){{
        setId(PLANNINGS_ID);
        setIsSysRec((byte) 1);
        setParentId(PROJECT_ID);
        setName("PLANNINGS");
    }};
    public static final ProjectItemType PLANNING = new ProjectItemType(){{
        setId(PLANNING_ID);
        setIsSysRec((byte) 1);
        setParentId(PLANNINGS_ID);
        setName("PLANNING");
    }};
    public static final ProjectItemType MANAGEMENTS = new ProjectItemType(){{
        setId(MANAGEMENTS_ID);
        setIsSysRec((byte) 1);
        setParentId(PROJECT_ID);
        setName("MANAGEMENTS");
    }};
    public static final ProjectItemType MANAGEMENT = new ProjectItemType(){{
        setId(MANAGEMENT_ID);
        setIsSysRec((byte) 1);
        setParentId(MANAGEMENTS_ID);
        setName("MANAGEMENT");
    }};
    public static final ProjectItemType INICIATIVES = new ProjectItemType(){{
        setId(INICIATIVES_ID);
        setIsSysRec((byte) 1);
        setParentId(PLANNING_ID);
        setName("INICIATIVES");
    }};
    public static final ProjectItemType INICIATIVE = new ProjectItemType(){{
        setId(INICIATIVE_ID);
        setIsSysRec((byte) 1);
        setParentId(INICIATIVES_ID);
        setName("INICIATIVE");
    }};
    public static final ProjectItemType EPICS = new ProjectItemType(){{
        setId(EPICS_ID);
        setIsSysRec((byte) 1);
        setParentId(INICIATIVE_ID);
        setName("EPICS");
    }};
    public static final ProjectItemType EPIC = new ProjectItemType(){{
        setId(EPIC_ID);
        setIsSysRec((byte) 1);
        setParentId(EPICS_ID);
        setName("EPIC");
    }};
    public static final ProjectItemType FEATURES = new ProjectItemType(){{
        setId(FEATURES_ID);
        setIsSysRec((byte) 1);
        setParentId(EPIC_ID);
        setName("FEATURES");
    }};
    public static final ProjectItemType FEATURE = new ProjectItemType(){{
        setId(FEATURE_ID);
        setIsSysRec((byte) 1);
        setParentId(FEATURES_ID);
        setName("FEATURE");
    }};
    public static final ProjectItemType REQUIREMENTS = new ProjectItemType(){{
        setId(REQUIREMENTS_ID);
        setIsSysRec((byte) 1);
        setParentId(FEATURE_ID);
        setName("REQUIREMENTS");
    }};
    public static final ProjectItemType REQUIREMENT = new ProjectItemType(){{
        setId(REQUIREMENT_ID);
        setIsSysRec((byte) 1);
        setParentId(REQUIREMENTS_ID);
        setName("REQUIREMENT");
    }};
    public static final ProjectItemType AGILE_METHODOLOGIES = new ProjectItemType(){{
        setId(AGILE_METHODOLOGIES_ID);
        setIsSysRec((byte) 1);
        setParentId(MANAGEMENT_ID);
        setName("AGILE_METHODOLOGIES");
    }};
    public static final ProjectItemType AGILE_METHODOLOGY = new ProjectItemType(){{
        setId(AGILE_METHODOLOGY_ID);
        setIsSysRec((byte) 1);
        setParentId(AGILE_METHODOLOGIES_ID);
        setName("AGILE_METHODOLOGY");
    }};
    public static final ProjectItemType SCRUMS = new ProjectItemType(){{
        setId(SCRUMS_ID);
        setIsSysRec((byte) 1);
        setParentId(AGILE_METHODOLOGY_ID);
        setName("SCRUMS");
    }};
    public static final ProjectItemType SCRUM = new ProjectItemType(){{
        setId(SCRUM_ID);
        setIsSysRec((byte) 1);
        setParentId(SCRUMS_ID);
        setName("SCRUM");
    }};
    public static final ProjectItemType BACKLOGS = new ProjectItemType(){{
        setId(BACKLOGS_ID);
        setIsSysRec((byte) 1);
        setParentId(SCRUM_ID);
        setName("BACKLOGS");
    }};
    public static final ProjectItemType BACKLOG = new ProjectItemType(){{
        setId(BACKLOG_ID);
        setIsSysRec((byte) 1);
        setParentId(BACKLOGS_ID);
        setName("BACKLOG");
    }};
    public static final ProjectItemType SPRINTS = new ProjectItemType(){{
        setId(SPRINTS_ID);
        setIsSysRec((byte) 1);
        setParentId(SCRUM_ID);
        setName("SPRINTS");
    }};
    public static final ProjectItemType SPRINT = new ProjectItemType(){{
        setId(SPRINT_ID);
        setIsSysRec((byte) 1);
        setParentId(SPRINTS_ID);
        setName("SPRINT");
    }};
    public static final ProjectItemType TASKS = new ProjectItemType(){{
        setId(TASKS_ID);
        setIsSysRec((byte) 1);
        setParentId(SPRINT_ID);
        setName("TASKS");
    }};
    public static final ProjectItemType TASK = new ProjectItemType(){{
        setId(TASK_ID);
        setIsSysRec((byte) 1);
        setParentId(TASKS_ID);
        setName("TASK");
    }};

}