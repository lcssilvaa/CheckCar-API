package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.service.ItemChecklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-checklist")
public class ItemChecklistController {

    private final ItemChecklistService itemChecklistService;

    public ItemChecklistController(ItemChecklistService itemChecklistService) {
        this.itemChecklistService = itemChecklistService;
    }

    @PostMapping
    public ResponseEntity<ItemChecklist> cadastrar(@RequestBody ItemChecklist item) {
        if (item == null) return ResponseEntity.badRequest().build();
        ItemChecklist novo = itemChecklistService.salvar(item);
        return ResponseEntity.ok(novo);
    }

    @GetMapping
    public ResponseEntity<List<ItemChecklist>> listarTodos() {
        List<ItemChecklist> lista = itemChecklistService.listarTodos();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemChecklist> buscarPorId(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        ItemChecklist item = itemChecklistService.buscarPorId(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemChecklist> atualizar(@PathVariable Integer id, @RequestBody ItemChecklist item) {
        if (id == null || item == null) return ResponseEntity.badRequest().build();
        ItemChecklist existente = itemChecklistService.buscarPorId(id);
        item.setId(existente.getId());
        ItemChecklist atualizado = itemChecklistService.salvar(item);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        itemChecklistService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ItemChecklist>> listarPorTipo(@PathVariable TipoVeiculo tipo) {
        List<ItemChecklist> lista = itemChecklistService.listarPorTipoVeiculo(tipo);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
}
