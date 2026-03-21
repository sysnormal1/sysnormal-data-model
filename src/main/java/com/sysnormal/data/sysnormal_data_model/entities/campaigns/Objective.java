package com.sysnormal.data.sysnormal_data_model.entities.campaigns;

import com.sysnormal.data.basic_data_model.entities.commons.entityType.EntityType;
import com.sysnormal.data.basic_data_model.entities.database.tables.Tables;
import com.sysnormal.data.basic_data_model.entities.measures.measurementUnit.MeasurementUnit;
import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "objectives",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "objectives_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name",
                                "(coalesce(campaign_name,''))",
                                "(coalesce(table_id,-1))",
                                "(coalesce(record_id,-1))",
                                "(coalesce(entity_type_id,-1))",
                                "(coalesce(entity_id,-1))",
                                "(coalesce(measurement_unit_id,-1))",
                                "start_at",
                                "end_at"
                        }
                )
        }
)
public class Objective extends BaseSysnormalEntity<Objective> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "campaign_name", length = 127)
    private String campaignName;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "entity_type_id")
    private Long entityTypeId;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "measurement_unit_id")
    private Long measurementUnitId;

    @Column(name = "conditions", length = Integer.MAX_VALUE)
    private String conditions;

    @Column(name = "type_get_objective_from")
    private String typeGetObjectiveFrom;

    @Column(name = "origin_get_objective_from")
    private String originGetObjectiveFrom;

    @Column(name = "get_objective_from", length = Integer.MAX_VALUE)
    private String getObjectiveFrom;

    @Column(name = "type_get_value_from")
    private String typeGetValueFrom;

    @Column(name = "origin_get_value_from")
    private String originGetValueFrom;

    @Column(name = "get_value_from", length = Integer.MAX_VALUE)
    private String getValueFrom;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", insertable = false, updatable = false)
    private Tables table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id", insertable = false, updatable = false)
    private EntityType entityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id", insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;



}