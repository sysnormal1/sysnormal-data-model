package com.sysnormal.libs.db.entities.sysnormal_entities.visualComponents;

import com.sysnormal.libs.db.entities.basic_entities.agents.Agent;
import com.sysnormal.libs.db.entities.basic_entities.agents.access.AccessProfile;
import com.sysnormal.libs.db.entities.sysnormal_entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "visual_components_permissions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "visual_components_permissions_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "access_profile_id",
                                "(coalesce(agent_id,0))",
                                "visual_component_id",
                                "(coalesce(start_at,'1000-01-01'))",
                                "(coalesce(end_at,'1000-01-01'))"
                        }
                )
        }
)
public class VisualComponentPermission extends BaseSysnormalEntity<VisualComponentPermission> {


    @Column(name = "access_profile_id", nullable = false)
    private Long accessProfileId;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "visual_component_id", nullable = false)
    private Long visualComponentId;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "is_accessible", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "is_accessible in (0,1)")
    private byte isAccessible = 1;

    @Column(name = "is_manipulatable", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "is_manipulatable in (0,1)")
    private byte isManipulatable = 1;

    @Column(name = "is_editable", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "is_editable in (0,1)")
    private byte isEditable = 1;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_profile_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AccessProfile accessProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visual_component_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VisualComponent visualComponent;

    protected static final long TABLE_ID = 500;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static final long SYSTEM_ID = 0;

    public static final VisualComponentPermission SYSTEM = new VisualComponentPermission(){{
        setId(SYSTEM_ID);
        setIsSysRec((byte) 1);
        setVisualComponentId(2201002L);
        setAccessProfileId(AccessProfile.SYSTEM_ID);
        setIsAccessible((byte) 1);
        setIsEditable((byte) 1);
        setIsManipulatable((byte) 1);
    }};


}