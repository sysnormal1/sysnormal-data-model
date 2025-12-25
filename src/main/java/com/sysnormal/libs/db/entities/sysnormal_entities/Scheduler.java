package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(
        name = "schedulers",
        uniqueConstraints = {
                @UniqueConstraint(name = "schedulers_u1",columnNames = {
                        "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                        "process_name",
                        "name",
                        "query_column_data",
                        "run_month_day_at",
                        "run_week_day_at",
                        "run_hour_at",
                        "run_minute_at"
                })
        }
)
@Getter
@Setter
public class Scheduler extends BaseBasicEntity<Scheduler> {

    @Column(name = "process_name", nullable = false, length = 127)
    private String processName;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "connection_name", length = 127)
    private String connectionName;

    @Column(name = "sql_script_type", nullable = false, length = 127)
    @ColumnDefault("'SELECT'")
    private String sqlScriptType = "SELECT";

    @Lob
    @Column(name = "sql_script", nullable = false, length = Integer.MAX_VALUE)
    private String sqlScript;

    @Column(name = "sql_replacements", length = Integer.MAX_VALUE)
    private String sqlReplacements;

    @Column(name = "query_column_data",  length = 127)
    private String queryColumnData;

    @Column(name = "output_file_path", length = 1024)
    private String outputFilePath;

    @Column(name = "output_file_name", length = 127)
    private String outputFileName;

    @Column(name = "output_file_extension", length = 10)
    private String outputFileExtension;

    @Column(name = "run_month_day_at", nullable = false, length = 2)
    @ColumnDefault("1")
    @Check(constraints = "(run_month_day_at BETWEEN 1 AND 31 OR run_month_day_at = -1)")
    @Min(-1)
    @Max(31)
    private Integer runMonthDayAt = 1;

    @Column(name = "run_week_day_at", nullable = false, length = 1)
    @ColumnDefault("1")
    @Check(constraints = "(run_week_day_at BETWEEN 1 AND 7 OR run_week_day_at = -1)")
    @Min(-1)
    @Max(7)
    private Integer runWeekDayAt = 1;

    @Column(name = "run_hour_at", nullable = false, length = 2)
    @ColumnDefault("0")
    @Check(constraints = "(run_hour_at BETWEEN -1 AND 23)")
    @Min(-1)
    @Max(23)
    private Integer runHourAt = 0;

    @Column(name = "run_minute_at", nullable = false, length = 2)
    @ColumnDefault("0")
    @Check(constraints = "(run_minute_at BETWEEN -1 AND 59)")
    @Min(-1)
    @Max(59)
    private Integer runMinuteAt = 0;

    @Column(name = "notes")
    private String notes;

    protected static final long TABLE_ID = 18000;
    public static long getTableId() {
        return TABLE_ID;
    }

}
