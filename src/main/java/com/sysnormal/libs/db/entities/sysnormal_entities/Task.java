package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "tasks"
)
public class Task extends BaseBasicEntity<Task> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "forecast_start_moment")
    private LocalDateTime forecastStartMoment;

    @Column(name = "forecast_end_moment")
    private LocalDateTime forecastEndMoment;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "notes")
    private String notes;

    protected static final long TABLE_ID = 15101;
    public static long getTableId() {
        return TABLE_ID;
    }

}
