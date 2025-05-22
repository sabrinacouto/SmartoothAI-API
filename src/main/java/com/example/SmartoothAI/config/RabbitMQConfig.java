package com.example.SmartoothAI.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${smartooth.broker.queue.profissional}")
    private String profissionalQueue;

    @Value("${smartooth.broker.exchange.profissional}")
    private String profissionalExchange;

    @Value("${smartooth.broker.routingkey.profissional.created}")
    private String profissionalRoutingKey;

    @Value("${smartooth.broker.queue.usuarioPaciente}")
    private String usuarioPacienteQueue;

    @Value("${smartooth.broker.exchange.usuarioPaciente}")
    private String usuarioPacienteExchange;

    @Value("${smartooth.broker.routingkey.usuarioPaciente.created}")
    private String usuarioPacienteRoutingKey;

    @Bean
    public Queue profissionalQueue() {
        return new Queue(profissionalQueue, true);
    }

    @Bean
    public DirectExchange profissionalExchange() {
        return new DirectExchange(profissionalExchange);
    }

    @Bean
    public Binding profissionalBinding() {
        return BindingBuilder
                .bind(profissionalQueue())
                .to(profissionalExchange())
                .with(profissionalRoutingKey);
    }

    @Bean
    public Queue usuarioPacienteQueue() {
        return new Queue(usuarioPacienteQueue, true);
    }

    @Bean
    public DirectExchange usuarioPacienteExchange() {
        return new DirectExchange(usuarioPacienteExchange);
    }

    @Bean
    public Binding usuarioPacienteBinding() {
        return BindingBuilder
                .bind(usuarioPacienteQueue())
                .to(usuarioPacienteExchange())
                .with(usuarioPacienteRoutingKey);
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(new ObjectMapper());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
