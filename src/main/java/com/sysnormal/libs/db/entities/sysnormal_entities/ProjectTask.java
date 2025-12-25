package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "project_tasks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "project_tasks_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "project_item_id"
                        }
                )
        }
)
public class ProjectTask extends BaseBasicEntity<ProjectTask> {

    @Column(name = "project_item_id", nullable = false)
    private Long projectItemId;

    @Column(name = "task_type_id",nullable = false)
    @ColumnDefault(ProjectTaskType.TASK_ID + "")
    private Long taskTypeId = ProjectTaskType.TASK_ID;

    @Column(name = "forecast_start_moment")
    private LocalDateTime forecastStartMoment;

    @Column(name = "forecast_end_moment")
    private LocalDateTime forecastEndMoment;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_item_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectItem projectIitem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_type_id", insertable = false, updatable = false)
    private ProjectTaskType taskType;

    protected static final long TABLE_ID = 15051;
    public static long getTableId() {
        return TABLE_ID;
    }

}
