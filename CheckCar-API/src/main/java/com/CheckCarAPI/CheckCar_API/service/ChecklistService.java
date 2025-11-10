package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.Checklist;
import com.CheckCarAPI.CheckCar_API.repository.ChecklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }

    public Checklist salvar(Checklist checklist) {
        // Garante que checklist não é null
        Objects.requireNonNull(checklist, "Checklist não pode ser null");
        return checklistRepository.save(checklist);
    }

    public List<Checklist> listarTodos() {
        List<Checklist> lista = checklistRepository.findAll();
        return Objects.requireNonNull(lista, "Lista de checklists não pode ser null");
    }

    public Checklist buscarPorId(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        return checklistRepository.findById(id).orElse(null);
    }

    public void deletar(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        checklistRepository.deleteById(id);
    }
}
