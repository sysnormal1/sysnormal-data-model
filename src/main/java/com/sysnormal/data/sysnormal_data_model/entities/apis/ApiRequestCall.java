package com.sysnormal.data.sysnormal_data_model.entities.apis;

import com.sysnormal.data.basic_data_model.entities.commons.actionStatus.ActionStatus;
import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(
        name = "api_request_calls"
)
public class ApiRequestCall extends BaseSysnormalEntity<ApiRequestCall> {

    @Column(name = "api_request_id", nullable = false)
    private Long apiRequestId;

    @Column(name = "run_status_id", nullable = false)
    @ColumnDefault(ActionStatus.NOT_STARTED_ID+"")
    private Long runStatusId = ActionStatus.NOT_STARTED_ID;

    @Column(name = "on_receive_response")
    private String onReceiveResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_request_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApiRequest apiRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "run_status_id", updatable = false, insertable = false)
    private ActionStatus runStatus;


}
