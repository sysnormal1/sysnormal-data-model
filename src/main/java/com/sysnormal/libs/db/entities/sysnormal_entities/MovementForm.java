package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Table(
        name = "movement_forms",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movement_forms_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class MovementForm extends BaseBasicEntity<MovementForm> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_budget", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_budget in (0,1)")
    private byte isBudget = 0;

    @Column(name = "is_order", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_order in (0,1)")
    private byte isOrder = 0;

    @Column(name = "is_invoice", nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "is_invoice in (0,1)")
    private byte isInvoice = 0;


    protected static final long TABLE_ID = 252L;
    public static long getTableId() {
        return TABLE_ID;
    }

    // Constantes equivalentes
    public static final long BUDGET_ID = 1;
    public static final long ORDER_ID = 2;
    public static final long INVOICE_ID = 3;

    public static final MovementForm BUDGET = new MovementForm(){{
        setId(BUDGET_ID);
        setIsSysRec((byte) 1);
        setName("BUDGET");
        setIsBudget((byte) 1);
    }};
    public static final MovementForm ORDER = new MovementForm(){{
        setId(ORDER_ID);
        setIsSysRec((byte) 1);
        setName("ORDER");
        setIsOrder((byte) 1);
    }};
    public static final MovementForm INVOICE = new MovementForm(){{
        setId(INVOICE_ID);
        setIsSysRec((byte) 1);
        setName("INVOICE");
        setIsInvoice((byte) 1);
    }};
}