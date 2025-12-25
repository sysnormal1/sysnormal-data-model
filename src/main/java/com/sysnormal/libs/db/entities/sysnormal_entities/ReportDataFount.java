package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "report_data_founts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "report_data_founts_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class ReportDataFount extends BaseBasicEntity<ReportDataFount> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "type_get_expected_data_from", length = 127)
    private String typeGetExpectedDataFrom;

    @Column(name = "origin_get_expected_data_from", length = 127)
    private String originGetExpectedDataFrom;

    @Column(name = "get_expected_data_from", length = Integer.MAX_VALUE)
    private String getExpectedDataFrom;

    @Column(name = "type_get_value_from", length = 127)
    private String typeGetValueFrom;

    @Column(name = "origin_get_value_from", length = 127)
    private String originGetValueFrom;

    @Column(name = "get_value_from", length = Integer.MAX_VALUE)
    private String getValueFrom;

    protected static final long TABLE_ID = 10006;
    public static long getTableId() {
        return TABLE_ID;
    }

}
