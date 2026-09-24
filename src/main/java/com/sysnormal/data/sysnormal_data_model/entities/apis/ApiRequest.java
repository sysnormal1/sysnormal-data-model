package com.sysnormal.data.sysnormal_data_model.entities.apis;

import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
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
public class ApiRequest extends BaseSysnormalEntity<ApiRequest> {

    /**
     * A API a que esta requisição pertence.
     *
     * <p>Chamava-se {@code apiRequestId}, nome que dizia outra coisa — é o id da
     * API, não o da requisição — e que fazia toda consulta escrita por ele
     * apontar para o campo errado.</p>
     */
    @Column(name = "api_id", nullable = false)
    private Long apiId;

    @Column(name = "name", length = 127)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
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
    private Api api;


}
