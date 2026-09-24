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

    /**
     * Os cabeçalhos da resposta, como JSON.
     *
     * <p>Existe porque em muitas APIs o que importa não vem no corpo: custo da
     * chamada, identificador de rastreio, teto de uso restante. No CNPJá é o
     * {@code cnpja-request-cost}, e sem ele não há extrato de consumo — só
     * estimativa.</p>
     *
     * <p>Fica como texto e não como colunas nomeadas de propósito: cada API tem
     * os seus, e esta tabela não conhece API nenhuma.</p>
     */
    @Column(name = "response_headers", length = Integer.MAX_VALUE)
    private String responseHeaders;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_request_call_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApiRequestCall apiRequestCall;


}
