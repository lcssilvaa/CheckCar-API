package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.Checklist;
import com.CheckCarAPI.CheckCar_API.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/api/checklists")
    public class ChecklistController {

        @Autowired
        private ChecklistService checklistService;

        @PostMapping
        public ResponseEntity<Checklist> cadastrar(@RequestBody Checklist checklist) {
            Checklist novo = checklistService.salvar(checklist);
            return ResponseEntity.ok(novo);
        }

        @GetMapping
        public ResponseEntity<List<Checklist>> listarTodos() {
            return ResponseEntity.ok(checklistService.listarTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Checklist> buscarPorId(@PathVariable Integer id) {
            return ResponseEntity.ok(checklistService.buscarPorId(id));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Integer id) {
            checklistService.deletar(id);
            return ResponseEntity.noContent().build();
        }
    }

