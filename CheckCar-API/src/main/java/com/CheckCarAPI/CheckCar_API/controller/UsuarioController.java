package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import com.CheckCarAPI.CheckCar_API.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        if (usuario == null) return ResponseEntity.badRequest().build();
        Usuario novo = usuarioService.salvar(usuario);
        return ResponseEntity.ok(novo);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> buscarPorCpf(@PathVariable String cpf) {
        if (cpf == null) return ResponseEntity.badRequest().build();
        Usuario usuario = usuarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> lista = usuarioService.listarTodos();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        if (id == null || usuario == null) return ResponseEntity.badRequest().build();
        Usuario existente = usuarioService.buscarPorId(id);
        usuario.setId(existente.getId());
        Usuario atualizado = usuarioService.salvar(usuario);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
