package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import com.CheckCarAPI.CheckCar_API.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorCpf(String cpf) {
        Objects.requireNonNull(cpf, "CPF não pode ser null");
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario buscarPorId(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario salvar(Usuario usuario) {
        Objects.requireNonNull(usuario, "Usuário não pode ser null");
        if (usuarioRepository.findByCpf(usuario.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Integer id, Usuario usuarioAtualizado) {
        Objects.requireNonNull(id, "ID não pode ser null");
        Objects.requireNonNull(usuarioAtualizado, "Usuário atualizado não pode ser null");

        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existente.setNome(usuarioAtualizado.getNome());
        existente.setCpf(usuarioAtualizado.getCpf());
        // outros campos do Usuario podem ser atualizados aqui

        return usuarioRepository.save(existente);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        usuarioRepository.deleteById(id);
    }
}