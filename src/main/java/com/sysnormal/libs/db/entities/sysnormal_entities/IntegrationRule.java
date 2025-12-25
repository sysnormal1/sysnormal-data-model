package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.database.Tables;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Table(
        name = "integration_rules",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "integration_rules_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name",
                                "table_id",
                                "(COALESCE(field_name,'NULL'))"
                        }
                )
        }
)
public class IntegrationRule extends BaseBasicEntity<IntegrationRule> {

    @Column(name = "name", nullable = false,length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "table_id", nullable = false)
    private Long tableId;

    @Column(name = "field_name", length = 127)
    private String fieldName;

    @Column(name = "condition_to_check", length = Integer.MAX_VALUE)
    private String conditionToCheck;

    @Column(name = "rule", nullable = false, length = Integer.MAX_VALUE)
    private String rule;

    @Column(name = "check_at_front", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "check_at_front in (0,1)")
    private byte checkAtFront = 1;

    @Column(name = "check_at_back", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "check_at_back in (0,1)")
    private byte checkAtBack = 1;

    @Column(name = "check_on_insert", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "check_on_insert in (0,1)")
    private byte checkOnInsert = 1;

    @Column(name = "check_on_update", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "check_on_update in (0,1)")
    private byte checkOnUpdate = 1;

    @Column(name = "check_on_delete", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "check_on_delete in (0,1)")
    private byte checkOnDelete = 0;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", insertable = false, updatable = false)
    private Tables table;

    protected static final long TABLE_ID = 20020;

    public static long getTableId() {
        return TABLE_ID;
    }
}