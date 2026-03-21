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
        name = "api_responses"
)
public class ApiResponse extends BaseSysnormalEntity<ApiResponse> {

    @Column(name = "api_request_call_id", nullable = false)
    private Long apiRequestCallId;

    @Column(name = "response_status_code")
    private Integer responseStatusCode;

    @Column(name = "response", length = Integer.MAX_VALUE)
    private String response;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_request_call_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApiRequestCall apiRequestCall;


}
