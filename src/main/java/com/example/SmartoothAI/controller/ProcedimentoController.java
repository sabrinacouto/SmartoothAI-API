package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.ProcedimentoDTO;
import com.example.SmartoothAI.services.ProcedimentoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    public ProcedimentoController(ProcedimentoService procedimentoService) {
        this.procedimentoService = procedimentoService;
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<ProcedimentoDTO>>> getAllProcedimentos() {
        List<EntityModel<ProcedimentoDTO>> procedimentosDTO = procedimentoService.getAllProcedimentos().getBody().stream()
                .map(this::toEntityModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(procedimentosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProcedimentoDTO>> getProcedimentoById(@PathVariable Long id) {
        ProcedimentoDTO procedimentoDTO = procedimentoService.getProcedimentoById(id).getBody();
        return ResponseEntity.ok(toEntityModel(procedimentoDTO));
    }

    @PostMapping
    public ResponseEntity<String> createProcedimento(@RequestBody ProcedimentoDTO procedimentoDTO) {
        return procedimentoService.save(procedimentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProcedimento(@PathVariable Long id, @RequestBody ProcedimentoDTO procedimentoDTO) {
        return procedimentoService.update(id, procedimentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProcedimento(@PathVariable Long id) {
        return procedimentoService.delete(id);
    }

    private EntityModel<ProcedimentoDTO> toEntityModel(ProcedimentoDTO procedimentoDTO) {
        EntityModel<ProcedimentoDTO> entityModel = EntityModel.of(procedimentoDTO);
        Link selfLink = linkTo(ProcedimentoController.class)
                .slash(procedimentoDTO.getProcedimentoId())
                .withSelfRel();
        entityModel.add(selfLink);

        Link allLinks = linkTo(ProcedimentoController.class).withRel("all-procedimentos");
        entityModel.add(allLinks);

        return entityModel;
    }
}
