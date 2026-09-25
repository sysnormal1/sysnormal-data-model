package com.sysnormal.data.sysnormal_data_model.entities.records;

import com.sysnormal.data.basic_data_model.entities.commons.actionStatus.ActionStatus;
import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
import com.sysnormal.data.sysnormal_data_model.entities.apis.ApiResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/**
 * Uma alteração de campo aplicada num cadastro — o que mudou, de quê para quê,
 * por quem e com que resultado.
 *
 * <h2>Não conhece entidade nenhuma</h2>
 *
 * <p>O alvo é o par agnóstico que toda entidade daqui carrega:
 * {@code data_origin_id} + {@code table_origin_id} + {@code id_at_origin} —
 * "o cliente 12462 da tabela PCCLIENT no Winthor". A mesma tabela serve a um
 * campo de cliente, de produto ou de fornecedor, de qualquer sistema
 * catalogado, e é por isso que ela não tem uma coluna sequer com nome de
 * negócio.</p>
 *
 * <h2>⚠️ `oldValue` é o que torna a gravação reversível</h2>
 *
 * <p>Gravar num ERP de terceiro sem guardar o valor anterior é uma operação de
 * mão única. Este registro é escrito <b>antes</b> do UPDATE e fechado depois
 * dele: se a gravação falhar, fica o motivo em {@code errorMessage} e o status
 * de parada; se der certo, fica o retrato exato do que desfazer.</p>
 *
 * <p>{@code apiResponseId} é a justificativa: a resposta da API externa que
 * sugeriu aquele valor. Nulo quando a mudança não veio de integração — uma
 * correção manual pela tela é igualmente registrável aqui.</p>
 */
@Getter
@Setter
@Entity
@Table(
        name = "record_field_changes",
        indexes = {
                @Index(name = "record_field_changes_target_idx", columnList = "table_origin_id, id_at_origin"),
                @Index(name = "record_field_changes_response_idx", columnList = "api_response_id")
        }
)
public class RecordFieldChange extends BaseSysnormalEntity<RecordFieldChange> {

    /** A resposta que justificou a mudança. Nulo em alteração manual. */
    @Column(name = "api_response_id")
    private Long apiResponseId;

    /**
     * A coluna alterada, com o nome que ela tem no sistema de destino
     * ({@code CLIENTE}, {@code SIMPLESNACIONAL}).
     *
     * <p>Nome do destino, e não o nome interno do de-para: quem lê este log
     * precisa conseguir procurar a coluna no ERP sem tradutor.</p>
     */
    @Column(name = "field_name", nullable = false, length = 127)
    private String fieldName;

    @Column(name = "old_value", length = Integer.MAX_VALUE)
    private String oldValue;

    @Column(name = "new_value", length = Integer.MAX_VALUE)
    private String newValue;

    /**
     * Quem pediu a alteração, no domínio do <b>SSO</b>.
     *
     * <p>⚠️ Coluna <b>sem chave estrangeira</b>, e isso é a razão de ela
     * existir: {@code creator_agent_id} aponta para a tabela {@code agents}
     * DESTE banco, que é outra população — o mesmo jumbo.ti é o agente 1 no SSO
     * e o 0 aqui. Gravar o id do SSO naquela coluna derruba o insert por
     * violação de chave estrangeira, e foi exatamente o que aconteceu na
     * primeira versão deste log.</p>
     *
     * <p>{@code creator_agent_id} continua sendo o agente de sistema, porque
     * quem materializa a linha é o serviço; a autoria humana da decisão é
     * esta.</p>
     */
    @Column(name = "sso_agent_id")
    private Long ssoAgentId;

    /** `CONCLUDED` quando gravou, `STOPPED` quando falhou, `RUNNING` enquanto tenta. */
    @Column(name = "run_status_id", nullable = false)
    @ColumnDefault(ActionStatus.NOT_STARTED_ID + "")
    private Long runStatusId = ActionStatus.NOT_STARTED_ID;

    @Column(name = "error_message", length = Integer.MAX_VALUE)
    private String errorMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_response_id", updatable = false, insertable = false)
    private ApiResponse apiResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "run_status_id", updatable = false, insertable = false)
    private ActionStatus runStatus;

}
