package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.DataType;
import com.sysnormal.libs.db.entities.basic_entities.database.SqlObjectType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(
        name = "report_data_fount_items"
)
public class ReportDataFountItem extends BaseBasicEntity<ReportDataFountItem> {

    @Column(name = "report_data_source_id", nullable = false)
    private Long reportDataSourceId;

    @Column(name = "sql_object_type_id", nullable = false)
    private Long sqlObjectTypeId;

    @Column(name = "sql_object_id")
    private Long sqlObjectId;

    @Column(name = "before_sql_text", length = Integer.MAX_VALUE)
    private String beforeSqlText;

    @Column(name = "sql_text", length = Integer.MAX_VALUE)
    private String sqlText;

    @Column(name = "sql_text_after_children", length = Integer.MAX_VALUE)
    private String sqlTextAfterChildren;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("1")
    private BigInteger numericOrder = BigInteger.ONE;

    @Column(name = "sql_alias", length = 2000)
    private String sql_alias;

    @Column(name = "data_type_id")
    private Long dataTypeId;

    @Column(name = "existence_critery", length = Integer.MAX_VALUE)
    private String existenceCritery;

    @Column(name = "access_critery", length = Integer.MAX_VALUE)
    private String accessCritery;

    @Column(name = "is_unique_in_groupment", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_unique_in_groupment in (0,1)")
    private byte isUniqueInGroupment = 0;

    @Column(name = "data_groupment", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "data_groupment in (0,1)")
    private byte dataGroupment = 0;

    @Column(name = "value_groupment", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "value_groupment in (0,1)")
    private byte valueGroupment = 0;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_data_source_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ReportDataFount reportDataSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sql_object_type_id", insertable = false, updatable = false)
    private SqlObjectType sqlObjectType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_type_id", insertable = false, updatable = false)
    private DataType dataType;


    protected static final long TABLE_ID = 10006;
    public static long getTableId() {
        return TABLE_ID;
    }

}
