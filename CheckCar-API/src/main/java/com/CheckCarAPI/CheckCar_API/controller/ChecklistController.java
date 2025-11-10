package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.Checklist;
import com.CheckCarAPI.CheckCar_API.service.ChecklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistController {

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @PostMapping
    public ResponseEntity<Checklist> cadastrar(@RequestBody Checklist checklist) {
        if (checklist == null) return ResponseEntity.badRequest().build();
        Checklist novo = checklistService.salvar(checklist);
        return ResponseEntity.ok(novo);
    }

    @GetMapping
    public ResponseEntity<List<Checklist>> listarTodos() {
        List<Checklist> lista = checklistService.listarTodos();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checklist> buscarPorId(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        Checklist checklist = checklistService.buscarPorId(id);
        return ResponseEntity.ok(checklist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Checklist> atualizar(@PathVariable Integer id, @RequestBody Checklist checklist) {
        if (id == null || checklist == null) return ResponseEntity.badRequest().build();
        Checklist existente = checklistService.buscarPorId(id);
        checklist.setId(existente.getId());
        Checklist atualizado = checklistService.salvar(checklist);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        checklistService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
