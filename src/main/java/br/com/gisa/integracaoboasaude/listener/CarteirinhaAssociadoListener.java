package br.com.gisa.integracaoboasaude.listener;

import br.com.gisa.integracaoboasaude.config.Amqp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CarteirinhaAssociadoListener {


    @RabbitListener(queues = Amqp.ASSOCIADO_NOVO_QUEUE)
    public void onMessage(String payload) {
        log.info("Receiving message={}", payload);
    }

}
