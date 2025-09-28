package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CheckCarAPI.CheckCar_API.service.UsuarioService;

import java.util.List;

@RestController //indica que é uma classe controller
@RequestMapping("/api/usuarios") //caminho (endpoint)

public class UsuarioController {

    @Autowired //injeta o service do usuario
    private UsuarioService usuarioService;

    @PostMapping //inserir/cadastrar os usuários
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) { //os dados virão em .json
        Usuario novo = usuarioService.salvar(usuario);
        return ResponseEntity.ok(novo); //devolve o status 200 (ok)
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> buscarPorCpf(@PathVariable String cpf) { //pega o valor inserido e coloca na variável cpf
        Usuario usuario = usuarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build(); //devolve status 204 (sem conteúdo), mostrando que foi apagado
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
}
