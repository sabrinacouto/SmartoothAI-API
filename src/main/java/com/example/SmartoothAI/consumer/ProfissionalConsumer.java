package com.example.SmartoothAI.consumer;


import com.example.SmartoothAI.dto.ProfissionalDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProfissionalConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${smartooth.broker.queue.profissional}")
    private String profissionalQueue;

    @RabbitListener(queues = "${smartooth.broker.queue.profissional}")
    public void receberMensagem(ProfissionalDTO dto) {
        System.out.println("Profissional recebido via RabbitMQ: " + dto.getNome() + " | Email: " + dto.getEmail());
    }
}

