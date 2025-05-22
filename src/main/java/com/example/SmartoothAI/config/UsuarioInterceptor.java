package com.example.SmartoothAI.config;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UsuarioInterceptor implements HandlerInterceptor {

    @Autowired
    private UsuarioPacienteService usuarioPacienteService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuarioLogadoId") != null) {
            Long id = (Long) session.getAttribute("usuarioLogadoId");
            UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(id);
            request.setAttribute("usuario", usuario);
        }
        return true;
    }
}

