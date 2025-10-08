package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.RespostaChecklist;
import com.CheckCarAPI.CheckCar_API.service.RespostaChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respostas-checklist")
public class RespostaChecklistController {

    @Autowired
    private RespostaChecklistService respostaChecklistService;

    @PostMapping
    public ResponseEntity<RespostaChecklist> cadastrar(@RequestBody RespostaChecklist resposta) {
        RespostaChecklist nova = respostaChecklistService.salvar(resposta);
        return ResponseEntity.ok(nova);
    }

    @GetMapping
    public ResponseEntity<List<RespostaChecklist>> listarTodos() {
        return ResponseEntity.ok(respostaChecklistService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaChecklist> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(respostaChecklistService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        respostaChecklistService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/lote")
    public ResponseEntity<List<RespostaChecklist>> cadastrarLote(@RequestBody List<RespostaChecklist> respostas) {
        List<RespostaChecklist> salvas = respostaChecklistService.salvarLote(respostas);
        return ResponseEntity.ok(salvas);
    }

    
}
