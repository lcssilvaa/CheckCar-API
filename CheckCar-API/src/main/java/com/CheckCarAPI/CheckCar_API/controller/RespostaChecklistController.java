package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.RespostaChecklist;
import com.CheckCarAPI.CheckCar_API.service.RespostaChecklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respostas-checklist")
public class RespostaChecklistController {

    private final RespostaChecklistService respostaChecklistService;

    public RespostaChecklistController(RespostaChecklistService respostaChecklistService) {
        this.respostaChecklistService = respostaChecklistService;
    }

    @PostMapping
    public ResponseEntity<RespostaChecklist> cadastrar(@RequestBody RespostaChecklist resposta) {
        if (resposta == null) return ResponseEntity.badRequest().build();
        RespostaChecklist nova = respostaChecklistService.salvar(resposta);
        return ResponseEntity.ok(nova);
    }

    @GetMapping
    public ResponseEntity<List<RespostaChecklist>> listarTodos() {
        List<RespostaChecklist> lista = respostaChecklistService.listarTodos();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaChecklist> buscarPorId(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        RespostaChecklist resposta = respostaChecklistService.buscarPorId(id);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaChecklist> atualizar(@PathVariable Integer id, @RequestBody RespostaChecklist resposta) {
        if (id == null || resposta == null) return ResponseEntity.badRequest().build();
        RespostaChecklist existente = respostaChecklistService.buscarPorId(id);
        resposta.setId(existente.getId());
        RespostaChecklist atualizado = respostaChecklistService.salvar(resposta);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        respostaChecklistService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/lote")
    public ResponseEntity<List<RespostaChecklist>> cadastrarLote(@RequestBody List<RespostaChecklist> respostas) {
        if (respostas == null || respostas.isEmpty()) return ResponseEntity.badRequest().build();
        List<RespostaChecklist> salvas = respostaChecklistService.salvarLote(respostas);
        return ResponseEntity.ok(salvas);
    }
}
