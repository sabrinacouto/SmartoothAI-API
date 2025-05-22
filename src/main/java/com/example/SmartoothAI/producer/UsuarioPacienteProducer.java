package com.example.SmartoothAI.producer;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class UsuarioPacienteProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${smartooth.broker.exchange.usuarioPaciente}")
    private String exchange;

    @Value("${smartooth.broker.routingkey.usuarioPaciente.created}")
    private String routingKey;


    public void enviarUsuarioPacienteCriado(UsuarioPacienteDTO dto) {
        try {
            String json = new ObjectMapper().writeValueAsString(dto);
            System.out.println("JSON enviado: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(exchange, routingKey, dto);
    }




}
