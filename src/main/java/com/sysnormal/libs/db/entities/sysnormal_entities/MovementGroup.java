package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "movement_groups",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "movement_groups_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "(coalesce(identifier_type_id,0))", "(coalesce(identifier,'NULL'))"
                        }
                )
        }
)
public class MovementGroup extends BaseBasicEntity<MovementGroup> {

    @Column(name = "identifier_type_id")
    private Long identifierTypeId;

    @Column(name = "identifier", length = 127)
    private String identifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", insertable = false, updatable = false)
    private IdentifierType identifierType;

    protected static final long TABLE_ID = 9011;
    public static long getTableId() {
        return TABLE_ID;
    }


}