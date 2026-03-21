package com.sysnormal.data.sysnormal_data_model.entities.apis;

import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "maps_api_responses"
)
public class MapsApiResponse extends BaseSysnormalEntity<MapsApiResponse> {

    @Column(name = "entity", length = 127)
    private String entity;

    @Column(name = "entity_id", length = 127)
    private String entityId;

    @Column(name = "library", length = 127)
    private String library;

    @Column(name = "request_params", length = Integer.MAX_VALUE)
    private String requestParams;

    @Column(name = "response_status_code")
    private Short responseStatusCode;

    @Column(name = "response_status", length = 127)
    private String responseStatus;

    @Column(name = "response", length = Integer.MAX_VALUE)
    private String response;

    @Column(name = "response_expire_at")
    private LocalDateTime responseExpireAt;



}
