package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.IdentifierType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
        name = "containers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "containers_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "container_type_id",
                                "identifier_type_id",
                                "identifier"
                        }
                )
        }
)
public class Container extends BaseBasicEntity<Container> {

    @Column(name = "container_type_id", nullable = false)
    private Long containerTypeId;

    @Column(name = "identifier_type_id", nullable = false)
    private Long identifierTypeId;

    @Column(name = "identifier", nullable = false, length = 127)
    private String identifier;

    @Column(name = "tara", precision = 38, scale = 10)
    private BigDecimal tara;

    @Column(name = "allow_multiple_addresses", nullable = false, length = 1)
    @ColumnDefault("0")
    @Check(constraints = "allow_multiple_addresses in (0,1)")
    private byte isDefault = 0;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_type_id", updatable = false, insertable = false)
    private ContainerType containerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier_type_id", updatable = false, insertable = false)
    private IdentifierType identifierType;

    protected static final long TABLE_ID = 8015;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long WITHOUT_CONTEINER_ID = 1;

    public static final Container WITHOUT_CONTEINER = new Container(){{
        setId(WITHOUT_CONTEINER_ID);
        setIsSysRec((byte) 1);
        setContainerTypeId(ContainerType.NO_CONTEINER_ID);
        setIdentifierTypeId(IdentifierType.IDENTIFIER_ID);
        setIdentifier(WITHOUT_CONTEINER_ID+"");
    }};
}
