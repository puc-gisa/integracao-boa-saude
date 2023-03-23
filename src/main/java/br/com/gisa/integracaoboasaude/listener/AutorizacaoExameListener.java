package br.com.gisa.integracaoboasaude.listener;

import br.com.gisa.integracaoboasaude.config.Amqp;
import br.com.gisa.integracaoboasaude.domain.SituacaoAutoricacaoExame;
import br.com.gisa.integracaoboasaude.listener.message.AutorizacaoExameRequestMessage;
import br.com.gisa.integracaoboasaude.listener.message.AutorizacaoExameResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
@AllArgsConstructor
public class AutorizacaoExameListener {

    private final ObjectMapper mapper;
    private final RabbitMessagingTemplate rabbitTemplate;

    @RabbitListener(queues = Amqp.SOLICITACAO_AUTORIZACAO_EXAME_QUEUE)
    public void onMessage(String payload) throws JsonProcessingException {
        log.info("Receiving message={}", payload);
        AutorizacaoExameRequestMessage request = mapper.readValue(payload, AutorizacaoExameRequestMessage.class);
        AutorizacaoExameResponseMessage responseMessage = this.process(request);
        this.sendMessage(Amqp.RESPOSTA_AUTORIZACAO_EXAME_EXCHANGE, responseMessage);
    }

    private AutorizacaoExameResponseMessage process(AutorizacaoExameRequestMessage request) {
        AutorizacaoExameResponseMessage responseMessage = new AutorizacaoExameResponseMessage();
        responseMessage.setId(request.getId());

        LocalDate dataValidade = null;
        String justifcatica = null;
        final SituacaoAutoricacaoExame situacao;

        if (request.getCrmMedicoSolicitante().toUpperCase().endsWith("RJ")) {
            situacao = SituacaoAutoricacaoExame.NEGADO;
            justifcatica = "Médico não autorizado";
        } else if (request.getCodigoExame().toUpperCase().startsWith("A")) {
            situacao = SituacaoAutoricacaoExame.AUTORIZADO;
            justifcatica = "Exame previsto em contrato";
            dataValidade = request.getDataExame().plusDays(45);
        } else {
            situacao = SituacaoAutoricacaoExame.EM_ANALISE;
        }

        responseMessage.setCodigoSituacao(situacao.getCodigo());
        responseMessage.setDataValidade(dataValidade);
        responseMessage.setJustificativa(justifcatica);
        return responseMessage;
    }

    private void sendMessage(String exchange, AutorizacaoExameResponseMessage message) throws JsonProcessingException {
        String payload = mapper.writeValueAsString(message);
        log.info("Sending message to exchange={}, payload={}", exchange, payload);
        rabbitTemplate.convertAndSend(exchange, "", payload);
    }

}
