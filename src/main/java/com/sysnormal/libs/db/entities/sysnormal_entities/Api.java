package com.sysnormal.libs.db.entities.sysnormal_entities;


import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "apis",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "apis_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class Api extends BaseBasicEntity<Api> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "default_method", length = 10)
    private String defaultMethod;

    @Column(name = "default_end_point", length = 2000)
    private String defaultEndPoint;

    @Column(name = "default_authorization")
    private String defaultAuthorization;

    @Column(name = "default_request_params", length = Integer.MAX_VALUE)
    private String defaultRequestParams;

    @Column(name = "default_request_body_params", length = Integer.MAX_VALUE)
    private String defaultRequestBodyParams;

    @Column(name = "default_webhook", length = Integer.MAX_VALUE)
    private String defaultWebhook;

    protected static final long TABLE_ID = 20000;
    public static long getTableId() {
        return TABLE_ID;
    }
}
