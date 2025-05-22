package com.example.SmartoothAI.producer;

import com.example.SmartoothAI.dto.ProfissionalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProfissionalProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${smartooth.broker.exchange.profissional}")
    private String exchange;

    @Value("${smartooth.broker.routingkey.profissional.created}")
    private String routingKey;

    public void enviarProfissionalCriado(ProfissionalDTO dto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, dto);
    }
}


