package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.RecomendacaoTratamentoDTO;
import com.example.SmartoothAI.model.RecomendacaoTratamento;
import com.example.SmartoothAI.services.RecomendacaoTratamentoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacaoTratamentoController {

    private final RecomendacaoTratamentoService recomendacaoTratamentoService;

    public RecomendacaoTratamentoController(RecomendacaoTratamentoService recomendacaoTratamentoService) {
        this.recomendacaoTratamentoService = recomendacaoTratamentoService;
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<RecomendacaoTratamentoDTO>>> getAllRecomendacoes() {
        List<EntityModel<RecomendacaoTratamentoDTO>> recomendacoesDTO = recomendacaoTratamentoService.getAllRecomendacoes().getBody().stream()
                .map(this::toEntityModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recomendacoesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RecomendacaoTratamentoDTO>> getRecomendacaoById(@PathVariable Long id) {
        RecomendacaoTratamentoDTO recomendacaoTratamentoDTO = recomendacaoTratamentoService.getRecomendacaoById(id).getBody();
        return ResponseEntity.ok(toEntityModel(recomendacaoTratamentoDTO));
    }

    @PostMapping
    public ResponseEntity<String> createRecomendacao(@Valid @RequestBody RecomendacaoTratamentoDTO recomendacaoTratamentoDTO) {
        return recomendacaoTratamentoService.save(recomendacaoTratamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecomendacao(@PathVariable Long id, @Valid @RequestBody RecomendacaoTratamentoDTO recomendacaoTratamentoDTO) {
        return recomendacaoTratamentoService.update(id, recomendacaoTratamentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecomendacao(@PathVariable Long id) {
        return recomendacaoTratamentoService.delete(id);
    }

    private EntityModel<RecomendacaoTratamentoDTO> toEntityModel(RecomendacaoTratamentoDTO recomendacaoTratamentoDTO) {
        EntityModel<RecomendacaoTratamentoDTO> entityModel = EntityModel.of(recomendacaoTratamentoDTO);
        Link selfLink = linkTo(RecomendacaoTratamentoController.class)
                .slash(recomendacaoTratamentoDTO.getRecomendacaoId())
                .withSelfRel();
        entityModel.add(selfLink);

        Link allLinks = linkTo(RecomendacaoTratamentoController.class).withRel("all-recomendacoes");
        entityModel.add(allLinks);

        return entityModel;
    }
}

