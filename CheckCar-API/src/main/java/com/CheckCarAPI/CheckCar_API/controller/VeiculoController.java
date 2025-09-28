package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import com.CheckCarAPI.CheckCar_API.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/veiculos")
    public class VeiculoController {

        @Autowired
        private VeiculoService veiculoService;

        @PostMapping
        public ResponseEntity<Veiculo> cadastrar(@RequestBody Veiculo veiculo) {
            Veiculo novo = veiculoService.salvar(veiculo);
            return ResponseEntity.ok(novo);
        }

        @GetMapping
        public ResponseEntity<List<Veiculo>> listarTodos() {
            return ResponseEntity.ok(veiculoService.listarTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Veiculo> buscarPorId(@PathVariable Integer id) {
            return ResponseEntity.ok(veiculoService.buscarPorId(id));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Integer id) {
            veiculoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
    }


