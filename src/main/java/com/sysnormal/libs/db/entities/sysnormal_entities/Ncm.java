package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "ncms",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "ncms_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "chapter",
                                "(coalesce(position,0))",
                                "(coalesce(subposition,0))",
                                "(coalesce(item,0))",
                                "(coalesce(subitem,0))",
                                "(coalesce(exception,0))"
                        }
                )
        }
)

public class Ncm extends BaseBasicEntity<Ncm> {
    @Column(name = "chapter", nullable = false)
    @Check(constraints = "chapter between 0 and 99")
    private byte chapter;

    @Column(name = "position")
    @Check(constraints = "coalesce(position,0) between 0 and 99")
    private Byte position;

    @Column(name = "subposition")
    @Check(constraints = "coalesce(subposition,0) between 0 and 99")
    private Byte subposition;

    @Column(name = "item")
    @Check(constraints = "coalesce(item,0) between 0 and 9")
    private Byte item;

    @Column(name = "subitem")
    @Check(constraints = "coalesce(subitem,0) between 0 and 9")
    private Byte subitem;

    @Column(name = "ncm")
    private Long ncm;

    @Column(name = "exception")
    @Check(constraints = "coalesce(exception,0) between 0 and 99")
    private Byte exception;

    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "description", nullable = false, length = 1024)
    private String description;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "start_act_type", length = 512)
    private String startActType;

    @Column(name = "start_act_number", length = 512)
    private String startActNumber;

    @Column(name = "start_act_year", length = 4)
    private Integer startActYear;

    @Column(name = "notes")
    private String notes;

    protected static final long TABLE_ID = 8008L;
    public static long getTableId() {
        return TABLE_ID;
    }
}
