package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(
        name = "api_requests"
)
public class ApiRequest extends BaseBasicEntity<ApiRequest> {

    @Column(name = "api_id", nullable = false)
    private Long apiRequestId;

    @Column(name = "name", length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "method", length = 10)
    private String method;

    @Column(name = "end_point", length = 2000)
    private String endPoint;

    @Column(name = "authorization")
    private String authorization;

    @Column(name = "request_params", length = Integer.MAX_VALUE)
    private String requestParams;

    @Column(name = "body_params", length = Integer.MAX_VALUE)
    private String bodyParams;

    @Column(name = "webhook", length = Integer.MAX_VALUE)
    private String webhook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Api apiId;

    protected static final long TABLE_ID = 20001;
    public static long getTableId() {
        return TABLE_ID;
    }
}
