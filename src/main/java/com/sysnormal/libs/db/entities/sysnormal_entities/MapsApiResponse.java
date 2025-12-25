package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
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
public class MapsApiResponse extends BaseBasicEntity<MapsApiResponse> {

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


    protected static final long TABLE_ID = 20010;
    public static long getTableId() {
        return TABLE_ID;
    }
}
