package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
        name = "container_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "container_types_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class ContainerType extends BaseBasicEntity<ContainerType> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sigla", length = 10)
    private String sigla;

    @Column(name = "tara", precision = 38, scale = 10)
    private BigDecimal tara;


    protected static final long TABLE_ID = 8002;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long NO_CONTEINER_ID = 1;
    public static final long PALLET_ID = 2;

    public static final ContainerType NO_CONTEINER = new ContainerType(){{
        setId(NO_CONTEINER_ID);
        setIsSysRec((byte) 1);
        setName("NO_CONTEINER");
    }};
    public static final ContainerType PALLET = new ContainerType(){{
        setId(PALLET_ID);
        setIsSysRec((byte) 1);
        setName("PALLET");
    }};
}
