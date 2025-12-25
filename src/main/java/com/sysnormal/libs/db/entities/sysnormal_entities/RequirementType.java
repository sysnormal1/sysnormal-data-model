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
        name = "requirements_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "requirements_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class RequirementType extends BaseBasicEntity<RequirementType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_functional", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "is_functional in (0,1)")
    private byte isFunctional = 1;

    protected static final long TABLE_ID = 15019;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long FUNCTIONAL_ID = 1;
    public static final long NO_FUNCTIONAL_ID = 2;

    public static final RequirementType FUNCTIONAL = new RequirementType(){{
        setId(FUNCTIONAL_ID);
        setIsSysRec((byte) 1);
        setName("FUNCTIONAL");
        setIsFunctional((byte) 1);
    }};
    public static final RequirementType NO_FUNCTIONAL = new RequirementType(){{
        setId(NO_FUNCTIONAL_ID);
        setIsSysRec((byte) 1);
        setName("NO FUNCTIONAL");
        setIsFunctional((byte) 0);
    }};

}