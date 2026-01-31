package com.sysnormal.libs.db.entities.sysnormal_entities.sellers.commissions;

import com.sysnormal.libs.db.entities.basic_entities.measures.measurementUnit.MeasurementUnit;
import com.sysnormal.libs.db.entities.basic_entities.reports.reportVision.ReportVision;
import com.sysnormal.libs.db.entities.sysnormal_entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
        name = "commission_contract_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "commission_contract_items_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "commission_contract_id",
                                "name"
                        }
                )
        }
)
public class CommissionContractItem extends BaseSysnormalEntity<CommissionContractItem> {

    @Column(name = "commission_contract_id", nullable = false)
    private Long commissionContractId;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "replication_temporal_period_id")
    private Long replicationTemporalPeriodId;

    @Column(name = "consider_in_others_items", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "consider_in_others_items in (0,1)")
    private byte considerInOthersItems = 0;

    @Column(name = "base_value_origin", nullable = false, length = 127)
    @ColumnDefault("'manual'")
    @Check(constraints = "base_value_origin in ('manual','query','customized_report','specific_negotiation')")
    private String baseValueOrigin = "manual";

    @Column(name = "origin_transaction_id")
    private Long originTransactionId;

    @Column(name = "origin_transaction_item_id")
    private Long originTransactionItemId;

    @Column(name = "report_vision_id")
    private Long reportVisionId;

    @Column(name = "measurement_unit_id")
    private Long measurementUnitId;

    @Column(name = "consider_normal_sales", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "consider_normal_sales in (0,1)")
    private byte considerNormalSales = 1;

    @Column(name = "consider_returns", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "consider_returns in (0,1)")
    private byte considerReturns = 1;

    @Column(name = "consider_bonuses", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "consider_bonuses in (0,1)")
    private byte considerBonuses = 0;

    @Column(name = "consider_financial_discount", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "consider_financial_discount in (0,1)")
    private byte considerFinancialDiscount = 1;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "ignore_entity_id_condition", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "ignore_entity_id_condition in (0,1)")
    private byte ignoreEntityIdCondition = 0;

    @Column(name = "base_value_query", length = Integer.MAX_VALUE)
    private String baseValueQuery;

    @Column(name = "min_base_value", precision = 38, scale = 12)
    private BigDecimal minBaseValue;

    @Column(name = "max_base_value", precision = 38, scale = 12)
    private BigDecimal maxBaseValue;

    @Column(name = "base_value_expression", length = Integer.MAX_VALUE)
    private String baseValueExpression;

    @Column(name = "min_percent1", precision = 38, scale = 12)
    private BigDecimal minPercent1;

    @Column(name = "max_percent1", precision = 38, scale = 12)
    private BigDecimal maxPercent1;

    @Column(name = "percent1", precision = 38, scale = 12, nullable = false)
    @ColumnDefault("0")
    //@Check(constraints = "percent1 between coalesce(min_percent1,percent1) and coalesce(max_percent1,percent1)")
    private BigDecimal percent1 = BigDecimal.ZERO;

    @Column(name = "percent2", precision = 38, scale = 12)
    private BigDecimal percent2;

    @Column(name = "min_result_value", precision = 38, scale = 12)
    private BigDecimal minResultValue;

    @Column(name = "max_result_value", precision = 38, scale = 12)
    private BigDecimal maxResultValue;

    @Column(name = "expression", length = Integer.MAX_VALUE)
    private String expression;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commission_contract_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CommissionContract commissionContract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_vision_id", updatable = false, insertable = false)
    private ReportVision reportVision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", updatable = false, insertable = false)
    private MeasurementUnit measurementUnit;

    protected static final long TABLE_ID = 16102;
    public static long getTableId() {
        return TABLE_ID;
    }
}
