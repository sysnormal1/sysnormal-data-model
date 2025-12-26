package com.sysnormal.libs.db.entities.sysnormal_entities.sellers.commissions;

import com.sysnormal.libs.db.entities.sysnormal_entities.BaseSysnormalEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "commissions_old"
)
public class CommissionOld extends BaseSysnormalEntity<CommissionOld> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "entityname", nullable = false, length = 500)
    @ColumnDefault("'RCA'")
    private String entityname = "RCA";

    @Column(name = "entityid", nullable = false)
    private Long entityid;

    @Column(name = "condictionentityname", length = 500)
    private String condictionentityname;

    @Column(name = "condictionentityid")
    private Long condictionentityid;

    @Column(name = "conditionjoin", length = Integer.MAX_VALUE)
    private String conditionjoin;

    @Column(name = "condictionreportsvisionsids", length = Integer.MAX_VALUE)
    private String condictionreportsvisionsids;

    @Column(name = "percent1name", nullable = false, length = 500)
    @ColumnDefault("'COMMISSION'")
    private String percent1name = "COMMISSION";

    @Column(name = "percent1", precision = 38, scale = 12, nullable = false)
    @ColumnDefault("0")
    private BigDecimal percent1 = BigDecimal.ZERO;

    @Column(name = "percent2name", length = 500)
    private String percent2name;

    @Column(name = "percent2", precision = 38, scale = 12)
    private BigDecimal percent2;

    @Column(name = "minvalue", precision = 38, scale = 12)
    private BigDecimal minvalue;

    @Column(name = "maxvalue", precision = 38, scale = 12)
    private BigDecimal maxvalue;

    @Column(name = "priorityoverothers", nullable = false)
    @ColumnDefault("1")
    @Check(constraints = "priorityoverothers in (0,1)")
    private byte priorityoverothers = 1;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    protected static final long TABLE_ID = 40620;
    public static long getTableId() {
        return TABLE_ID;
    }
}
