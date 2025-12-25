package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import com.sysnormal.libs.db.entities.basic_entities.database.Tables;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "midias"
)
public class Midia extends BaseBasicEntity<Midia> {

    @Column(name = "table_ref_id")
    private Long tableRefId;

    @Column(name = "record_ref_id", nullable = false)
    private Long recordRefId;

    @Column(name = "numeric_order")
    private Integer numericOrder;

    @Column(name = "name", length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type", length = 127)
    private String type;

    @Column(name = "local_path", length = 4000)
    private String localPath;

    @Column(name = "content_base64", length = Integer.MAX_VALUE)
    private String contentBase64;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_ref_id", insertable = false, updatable = false)
    private Tables tableRef;

    protected static final long TABLE_ID = 50000;
    public static long getTableId() {
        return TABLE_ID;
    }

}