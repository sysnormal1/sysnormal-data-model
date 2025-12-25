package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import com.sysnormal.libs.db.entities.basic_entities.commons.ValueName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Table(
        name = "item_mov_amount_restrictions"
)
public class ItemMovAmountRestriction extends BaseBasicEntity<ItemMovAmountRestriction> {

    @Column(name = "item_mov_amt_id", nullable = false)
    private Long itemMovAmtId;

    @Column(name = "identifier_type_id")
    private Long identifierTypeId;

    @Column(name = "value_name_id")
    private Long valueNameId;

    @Column(name = "operation", length = 127)
    private String operation;

    @Column(name = "value", length = 127)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_mov_amt_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ItemMovAmount itemMovAmt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", insertable = false, updatable = false)
    private IdentifierType identifierType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "value_name_id", insertable = false, updatable = false)
    private ValueName valueName;

    protected static final long TABLE_ID = 9037;

    public static long getTableId() {
        return TABLE_ID;
    }
}