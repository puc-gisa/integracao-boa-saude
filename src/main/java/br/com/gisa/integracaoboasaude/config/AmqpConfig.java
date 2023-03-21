package br.com.gisa.integracaoboasaude.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public FanoutExchange createAssociadoExchange() {
        return new FanoutExchange(Amqp.ASSOCIADO_NOVO_EXCHANGE);
    }

    @Bean
    public FanoutExchange updateAssociadoExchange() {
        return new FanoutExchange(Amqp.ASSOCIADO_ATUALIZADO_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(Amqp.ASSOCIADO_NOVO_QUEUE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(createAssociadoExchange());
    }
}

