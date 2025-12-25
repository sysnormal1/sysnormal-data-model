package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.commons.Packaging;
import com.sysnormal.libs.db.entities.basic_entities.people.suppliers.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "packs_x_packs_origins",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "packs_x_packs_origins_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "(coalesce(supplier_id,0))", "origin_packaging"
                        }
                )
        }
)
public class PackXPackOrigin extends BaseBasicEntity<PackXPackOrigin> {

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "origin_packaging", nullable = false, length = 127)
    private String originPackaging;

    @Column(name = "packaging_id", nullable = false)
    private Long packagingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", insertable = false, updatable = false)
    private Packaging packaging;

    protected static final long TABLE_ID = 30800;
    public static long getTableId() {
        return TABLE_ID;
    }


}