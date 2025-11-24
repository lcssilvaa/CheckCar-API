package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.DTO.ChecklistDTO;
import com.CheckCarAPI.CheckCar_API.entity.Checklist;
import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import com.CheckCarAPI.CheckCar_API.repository.ChecklistRepository;
import com.CheckCarAPI.CheckCar_API.repository.UsuarioRepository;
import com.CheckCarAPI.CheckCar_API.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChecklistService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

    public Checklist salvar(ChecklistDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(dto.getIdVeiculo())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Checklist checklist = new Checklist();
        checklist.setUsuario(usuario);
        checklist.setVeiculo(veiculo);

        return checklistRepository.save(checklist);
    }

    public List<Checklist> listarTodos() {
        return checklistRepository.findAll();
    }

    public Checklist buscarPorId(Integer id) {
        return checklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Checklist não encontrado"));
    }

    public void deletar(Integer id) {
        checklistRepository.deleteById(id);
    }

    public Checklist salvarDataHora(Checklist checklist) {
        checklist.setDataCheckList(LocalDateTime.now());
        return checklistRepository.save(checklist);
    }

}

