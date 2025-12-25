package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "fiscal_codes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "fiscal_codes_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "movement_type_id","fiscal_code","name"
                        }
                )
        }
)
public class FiscalCode extends BaseBasicEntity<FiscalCode> {

    @Column(name = "movement_type_id", nullable = false)
    private Long movementTypeId;

    @Column(name = "fiscal_code", nullable = false)
    private Integer fiscalCode;

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_type_id", updatable = false, insertable = false)
    private MovementType movementType;


    protected static final long TABLE_ID = 253;
    public static long getTableId() {
        return TABLE_ID;
    }

}