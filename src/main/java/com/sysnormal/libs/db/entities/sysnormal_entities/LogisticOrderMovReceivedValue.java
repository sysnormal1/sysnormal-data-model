package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.financial.Currency;
import com.sysnormal.libs.db.entities.basic_entities.financial.FinancialValueForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(
        name = "logistic_orders_movs_received_values",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "logistic_orders_movs_received_values_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "(COALESCE(logistic_order_movement_receipt_value_ref_id,0))",
                                "logistic_order_id",
                                "(COALESCE(mov_logistic_order_id,0))",
                                "financial_value_form_id",
                                "(COALESCE(logistic_mov_item_mov_id,0))",
                                "expected_currency_id",
                                "numeric_order"
                        }
                )
        }
)
public class LogisticOrderMovReceivedValue extends BaseBasicEntity<LogisticOrderMovReceivedValue> {

    @Column(name = "logistic_order_movement_receipt_value_ref_id")
    private Long logisticOrderMovementReceiptValueRefId;

    @Column(name = "logistic_order_id", nullable = false)
    private Long logisticOrderId;

    @Column(name = "mov_logistic_order_id")
    private Long movLogisticOrderId;

    @Column(name = "financial_value_form_id", nullable = false)
    private Long financialValueFormId;

    @Column(name = "logistic_mov_item_mov_id")
    private Long logisticMovItemMovId;

    @Column(name = "expected_currency_id", nullable = false)
    private Long expectedCurrencyId;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "expected_value", precision = 38, scale = 10)
    private BigDecimal expectedValue;

    @Column(name = "expected_value_notes")
    private String expectedValueNotes;

    @Column(name = "received_currency_id")
    private Long receivedCurrencyId;

    @Column(name = "received_value", precision = 38, scale = 10)
    private BigDecimal receivedValue;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "received_notes")
    private String receivedNotes;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("1")
    private Integer numericOrder = 1;

    @Column(name = "proofs")
    private String proofs;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_order_movement_receipt_value_ref_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LogisticOrderMovReceivedValue logisticOrderMovementReceiptValueRef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_order_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LogisticOrder logisticOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_logistic_order_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LogisticOrderMov movLogisticOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_value_form_id", insertable = false, updatable = false)
    private FinancialValueForm financialValueForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_mov_item_mov_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LogisticOrderItemMovAmt logisticMovItemMov;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expected_currency_id", insertable = false, updatable = false)
    private Currency expectedCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_currency_id", insertable = false, updatable = false)
    private Currency receivedCurrency;

    protected static final long TABLE_ID = 12006;
    public static long getTableId() {
        return TABLE_ID;
    }

}
