package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.database.Tables;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(
        name = "logistic_logs"
)
public class LogisticLog extends BaseBasicEntity<LogisticLog> {

    @Column(name = "table_ref_id", nullable = false)
    private Long tableRefId;

    @Column(name = "record_ref_id", nullable = false)
    private Long recordRefId;

    @Column(name = "operation", length = 127, nullable = false)
    @ColumnDefault("'UPDATE'")
    private String operation = "UPDATE";

    @Column(name = "json_object", length = Integer.MAX_VALUE)
    private String jsonObject;

    @Column(name = "column_name", length = 127)
    private String columnName;

    @Column(name = "old_value", length = Integer.MAX_VALUE)
    private String oldValue;

    @Column(name = "new_value", length = Integer.MAX_VALUE)
    private String newValue;

    @Column(name = "latitude", precision = 38, scale = 10)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 38, scale = 10)
    private BigDecimal longitude;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_ref_id", insertable = false, updatable = false)
    private Tables tableRef;

    protected static final long TABLE_ID = 12100;

    public static long getTableId() {
        return TABLE_ID;
    }
}