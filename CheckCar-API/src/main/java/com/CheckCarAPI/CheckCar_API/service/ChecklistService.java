package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.Checklist;
import com.CheckCarAPI.CheckCar_API.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;

    public Checklist salvar(Checklist checklist) {
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

