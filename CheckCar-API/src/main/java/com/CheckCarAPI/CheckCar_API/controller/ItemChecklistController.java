package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.service.ItemChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-checklist")
public class ItemChecklistController {

    @Autowired
    private ItemChecklistService itemChecklistService;

    @PostMapping
    public ResponseEntity<ItemChecklist> cadastrar(@RequestBody ItemChecklist item) {
        ItemChecklist novo = itemChecklistService.salvar(item);
        return ResponseEntity.ok(novo);
    }

    @GetMapping
    public ResponseEntity<List<ItemChecklist>> listarTodos() {
        return ResponseEntity.ok(itemChecklistService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemChecklist> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(itemChecklistService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        itemChecklistService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ItemChecklist>> listarPorTipo(@PathVariable TipoVeiculo tipo) {
        return ResponseEntity.ok(itemChecklistService.listarPorTipoVeiculo(tipo));
    }
}

