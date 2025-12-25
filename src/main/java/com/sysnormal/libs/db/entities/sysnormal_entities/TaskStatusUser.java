package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.agents.Agent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "tasks_status_agents",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "tasks_status_agents_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "task_id",
                                "agent_id",
                                "status_id"
                        }
                )
        }
)
public class TaskStatusUser extends BaseBasicEntity<TaskStatusUser> {

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "agent_id",nullable = false)
    private Long agentId;

    @Column(name = "status_id",nullable = false)
    @ColumnDefault(TaskStatus.NOT_STARTED_ID + "")
    private Long statusId = TaskStatus.NOT_STARTED_ID;

    @Column(name = "triggering_task_id")
    private Long triggeringTaskId;

    @Column(name = "forecast_start_moment")
    private LocalDateTime forecastStartMoment;

    @Column(name = "forecast_end_moment")
    private LocalDateTime forecastEndMoment;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "last_run")
    private LocalDateTime lastRun;

    @Column(name = "accumulated_time")
    private BigInteger accumulatedTime;

    @Column(name = "is_owner", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "is_owner in (0,1)")
    private byte isOwner = 1;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "triggering_task_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Task triggeringTask;

    protected static final long TABLE_ID = 15150;
    public static long getTableId() {
        return TABLE_ID;
    }

}
