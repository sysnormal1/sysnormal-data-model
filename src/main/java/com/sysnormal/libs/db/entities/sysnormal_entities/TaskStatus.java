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
        name = "task_status",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "task_status_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name",
                                "(coalesce(sigla,'NULL'))"
                        }
                )
        }
)
public class TaskStatus extends BaseBasicEntity<TaskStatus> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "sigla", length = 10)
    private String sigla;

    @Column(name = "description")
    private String description;

    @Column(name = "is_running", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_running in (0,1)")
    private byte isRunning = 0;

    @Column(name = "is_stopped", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_stopped in (0,1)")
    private byte isStopped = 0;

    @Column(name = "is_canceled", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_canceled in (0,1)")
    private byte isCanceled = 0;

    @Column(name = "is_concluded", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_concluded in (0,1)")
    private byte isConcluded = 0;

    @Column(name = "is_visible", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "is_visible in (0,1)")
    private byte isVisible = 1;

    protected static final long TABLE_ID = 15100;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long NOT_STARTED_ID = 1;
    public static final long RUNNING_ID = 2;
    public static final long STOPPED_ID = 3;
    public static final long CANCELED_ID = 4;
    public static final long CONCLUDED_ID = 5;

    public static final TaskStatus NOT_STARTED = new TaskStatus(){{
        setId(NOT_STARTED_ID);
        setIsSysRec((byte) 1);
        setName("NOT STARTED");
    }};
    public static final TaskStatus RUNNING = new TaskStatus(){{
        setId(RUNNING_ID);
        setIsSysRec((byte) 1);
        setName("RUNNING");
        setIsRunning((byte) 1);
    }};
    public static final TaskStatus STOPPED = new TaskStatus(){{
        setId(STOPPED_ID);
        setIsSysRec((byte) 1);
        setName("STOPPED");
        setIsStopped((byte) 1);
    }};
    public static final TaskStatus CANCELED = new TaskStatus(){{
        setId(CANCELED_ID);
        setIsSysRec((byte) 1);
        setName("CANCELED");
        setIsCanceled((byte) 1);
        setIsVisible((byte) 1);
    }};
    public static final TaskStatus CONCLUDED = new TaskStatus(){{
        setId(CONCLUDED_ID);
        setIsSysRec((byte) 1);
        setName("CONCLUDED");
        setIsConcluded((byte) 1);
        setIsVisible((byte) 1);
    }};

}