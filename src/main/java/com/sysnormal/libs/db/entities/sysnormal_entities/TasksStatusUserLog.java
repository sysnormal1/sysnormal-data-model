package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(
        name = "tasks_status_users_logs"
)
public class TasksStatusUserLog extends BaseBasicEntity<TasksStatusUserLog> {

    @Column(name = "task_status_user_id", nullable = false)
    private Long taskStatusUserId;

    @Column(name = "operation", nullable = false, length = 50)
    private String operation;

    @Column(name = "old_status_id")
    private Long oldStatusId;

    @Column(name = "new_status_id")
    private Long newStatusId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_status_user_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TaskStatusUser taskStatusUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "old_status_id", updatable = false, insertable = false)
    private TaskStatus oldStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_status_id", updatable = false, insertable = false)
    private TaskStatus newStatus;

    protected static final long TABLE_ID = 15151;
    public static long getTableId() {
        return TABLE_ID;
    }
}
