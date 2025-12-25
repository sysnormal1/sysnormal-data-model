package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.financial.Currency;
import com.sysnormal.libs.db.entities.basic_entities.financial.FinancialValueForm;
import com.sysnormal.libs.db.entities.basic_entities.financial.FinancialValueMovType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
        name = "logistic_orders_dest_values",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "logistic_orders_dest_values_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "logistic_order_id",
                                "logistic_order_financial_value_form_id",
                                "financial_value_mov_type_dest_id",
                                "numeric_order"
                        }
                )
        }
)
public class LogisticOrderDestValue extends BaseBasicEntity<LogisticOrderDestValue> {

    @Column(name = "logistic_order_id", nullable = false)
    private Long logisticMovTypeId;

    @Column(name = "logistic_order_financial_value_form_id", nullable = false)
    private Long identifierTypeId;

    @Column(name = "currenty_type_id")
    private Long currentyTypeId;

    @Column(name = "financial_value_mov_type_dest_id", nullable = false)
    private Long financialValueMovTypeDestId;

    @Column(name = "destinated_value", precision = 38, scale = 10)
    private BigDecimal destinatedValue;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("1")
    private Integer numericOrder = 1;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_order_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LogisticOrder logisticOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_order_financial_value_form_id", insertable = false, updatable = false)
    private FinancialValueForm logisticOrderFinancialValueForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currenty_type_id", insertable = false, updatable = false)
    private Currency currentyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_value_mov_type_dest_id", insertable = false, updatable = false)
    private FinancialValueMovType financialValueMovTypeDest;

    protected static final long TABLE_ID = 12007;
    public static long getTableId() {
        return TABLE_ID;
    }

}
