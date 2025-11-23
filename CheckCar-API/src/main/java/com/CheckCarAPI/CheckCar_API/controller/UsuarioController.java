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

    @PutMapping("/{cpf}")
    public ResponseEntity<Usuario> atualizar(@PathVariable String cpf, @RequestBody Usuario usuario) {
        if (cpf == null || usuario == null) return ResponseEntity.badRequest().build();

        Usuario existente = usuarioService.buscarPorCpf(cpf);

        usuario.setId(existente.getId());
        usuario.setCpf(existente.getCpf());

        Usuario atualizado = usuarioService.salvar(usuario);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        if (cpf == null) return ResponseEntity.badRequest().build();

        Usuario existente = usuarioService.buscarPorCpf(cpf);

        usuarioService.deletar(existente.getId());
        return ResponseEntity.noContent().build();
    }
}
