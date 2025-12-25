package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.agents.Agent;
import com.sysnormal.libs.db.entities.basic_entities.people.businesses.businessUnits.BusinessUnit;
import com.sysnormal.libs.db.entities.basic_entities.people.businesses.companies.Company;
import com.sysnormal.libs.db.entities.basic_entities.people.clients.Client;
import com.sysnormal.libs.db.entities.basic_entities.people.collaborators.Collaborator;
import com.sysnormal.libs.db.entities.basic_entities.people.suppliers.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Table(
        name = "stock_entities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "stock_entities_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "company_id",
                                "(coalesce(business_unit_id,0))",
                                "(coalesce(warehouse_id,0))",
                                "(coalesce(supplier_id,0))",
                                "(coalesce(client_id,0))",
                                "(coalesce(agent_id,0))",
                                "(coalesce(collaborator_id,0))"
                        }
                )
        }
)
public class StockEntity extends BaseBasicEntity<StockEntity> {

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "business_unit_id", nullable = false)
    private Long businessUnitId;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "collaborator_id")
    private Long collaboratorId;

    @Column(name = "numeric_order", nullable = false)
    @ColumnDefault("0")
    private int numericOrder = 0;

    @Column(name = "precedence", nullable = false)
    @ColumnDefault("1")
    private int precedence = 1;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_unit_id", insertable = false, updatable = false)
    private BusinessUnit businessUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", insertable = false, updatable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator_id", insertable = false, updatable = false)
    private Collaborator collaborator;

    protected static final long TABLE_ID = 8025;

    public static long getTableId() {
        return TABLE_ID;
    }
}