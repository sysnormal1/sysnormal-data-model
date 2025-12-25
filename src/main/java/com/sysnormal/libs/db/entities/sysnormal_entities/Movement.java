package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.ActionStatus;
import com.sysnormal.libs.db.entities.basic_entities.commons.DataOrigin;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import com.sysnormal.libs.db.entities.basic_entities.financial.FinancialValueForm;
import com.sysnormal.libs.db.entities.basic_entities.people.businesses.businessUnits.BusinessUnit;
import com.sysnormal.libs.db.entities.basic_entities.people.businesses.companies.Company;
import com.sysnormal.libs.db.entities.basic_entities.people.clients.Client;
import com.sysnormal.libs.db.entities.basic_entities.people.collaborators.Collaborator;
import com.sysnormal.libs.db.entities.basic_entities.people.suppliers.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "movements",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movements_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "type_mov_id",
                                "mov_form_id",
                                "(COALESCE(identifier_type_id,0))",
                                "(COALESCE(identifier,'NULL'))"
                        }
                )
        },
        indexes = {
                @Index(name = "idx_movements_for_origin", columnList = "data_origin_id,table_origin_id,id_at_origin"),
                @Index(name = "idx_movements_customized_report_base", columnList = "invoice_issue_date,status_reg_id,deleted_at,canceled_at,mov_form_id"),
        }
)
public class Movement extends BaseBasicEntity<Movement> {

    @Column(name = "type_mov_id", nullable = false)
    private Long typeMovId;// = MovementType.OUTPUT;

    @Column(name = "mov_form_id", nullable = false)
    @ColumnDefault(MovementForm.INVOICE_ID + "")
    private Long movFormId = MovementForm.INVOICE_ID;

    @Column(name = "identifier_type_id")
    private Long identifierTypeId;

    @Column(name = "identifier", length = 127)
    private String identifier;

    @Column(name = "invoice_number")
    private Long invoiceNumber;

    @Column(name = "invoice_specy", length = 4)
    private String invoiceSpecy;

    @Column(name = "fiscal_code_id")
    private Long fiscalCodeId;

    @Column(name = "invoice_key", length = 100)
    private String invoiceKey;

    @Column(name = "invoice_issue_date")
    private LocalDateTime invoiceIssueDate;

    @Column(name = "lading_bill_number")
    private Long ladingBillNumber;

    @Column(name = "lading_bill_origin_transaction_id")
    private Long ladingBillOriginTransactionId;

    @Column(name = "lading_bill_origin_transaction_item_id")
    private Long ladingBillOriginTransactionItemId;

    @Column(name = "lading_bill_origin_transaction_compl_ids", length = Integer.MAX_VALUE)
    private String ladingBillOriginTransactionComplIds;

    @Column(name = "logistic_order_id")
    private Long logisticOrderId;

    @Column(name = "status_mov_id", nullable = false)
    @ColumnDefault(ActionStatus.NOT_STARTED_ID + "")
    private Long statusMovId= ActionStatus.NOT_STARTED_ID;

    @Column(name = "conference_type_id")
    private Long conferenceTypeId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "business_unit_id")
    private Long businessUnitId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "administrator_id")
    private Long administratorId;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "order_num")
    private Long orderNum;

    @Column(name = "order_origin_id")
    private Long orderOriginId;

    @Column(name = "order_issue_date")
    private LocalDateTime orderIssueDate;

    @Column(name = "financial_value_form_id")
    private Long financialValueFormId;

    @Column(name = "financial_discount_percent", precision = 38, scale = 10)
    private BigDecimal financialDiscountPercent;

    @Column(name = "financial_discount_value", precision = 38, scale = 10)
    private BigDecimal financialDiscountValue;

    @Column(name = "mov_ref_id")
    private Long movRefId;

    @Column(name = "mov_started_at")
    private LocalDateTime movStartedAt;

    @Column(name = "mov_ended_at")
    private LocalDateTime movEndedAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_mov_id", insertable = false, updatable = false)
    private MovementType typeMov;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_form_id", insertable = false, updatable = false)
    private MovementForm movForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fiscal_code_id", insertable = false, updatable = false)
    private FiscalCode fiscalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", insertable = false, updatable = false)
    private IdentifierType identifierType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_order_id", insertable = false, updatable = false)
    private LogisticOrder logisticOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_mov_id", nullable = false, insertable = false, updatable = false)
    private ActionStatus statusMov;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_type_id", insertable = false, updatable = false)
    private ConferenceType conferenceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", insertable = false, updatable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_unit_id", insertable = false, updatable = false)
    private BusinessUnit businessUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", insertable = false, updatable = false)
    private Collaborator buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrator_id", insertable = false, updatable = false)
    private Collaborator administrator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", insertable = false, updatable = false)
    private Collaborator seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_origin_id", insertable = false, updatable = false)
    private DataOrigin orderOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_value_form_id", insertable = false, updatable = false)
    private FinancialValueForm financialValueForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_ref_id", insertable = false, updatable = false)
    private Movement movRef;


    protected static final long TABLE_ID = 9010L;
    public static long getTableId() {
        return TABLE_ID;
    }
}
