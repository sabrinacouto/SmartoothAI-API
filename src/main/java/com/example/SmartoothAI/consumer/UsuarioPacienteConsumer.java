package com.example.SmartoothAI.consumer;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsuarioPacienteConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${smartooth.broker.queue.usuarioPaciente}")
    private String usuarioPacienteQueue;

    @RabbitListener(queues = "${smartooth.broker.queue.usuarioPaciente}")
    public void receberMensagem(UsuarioPacienteDTO dto) {
        System.out.println("Usuário Paciente recebido via RabbitMQ: " + dto.getNome() + " | Email: " + dto.getEmail());
    }
}

