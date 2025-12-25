package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduler_logs")
@Getter
@Setter
public class SchedulerLog extends BaseBasicEntity<SchedulerLog> {

    @Column(name = "scheduler_id", nullable = false)
    private Long schedulerId;

    @CreationTimestamp
    @Column(name = "runned_at", nullable = false, updatable = false)
    @ColumnDefault("current_timestamp(6)")
    private LocalDateTime runnedAt = LocalDateTime.now();

    @Column(name = "result", length = Integer.MAX_VALUE)
    private String result;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "scheduler_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Scheduler scheduler;

    protected static final long TABLE_ID = 18001;
    public static long getTableId() {
        return TABLE_ID;
    }
}
