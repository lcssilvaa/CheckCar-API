package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CheckCarAPI.CheckCar_API.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service //define como service

public class UsuarioService {

    @Autowired //injeta o repositório dentro do service
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.findByCpf(usuario.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario autenticar(String cpf, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
        if (usuario.isEmpty() || !usuario.get().getSenha().equals(senha)) {
            throw new RuntimeException("CPF ou senha inválidos");
        }
        return usuario.orElse(null);
    }
}


