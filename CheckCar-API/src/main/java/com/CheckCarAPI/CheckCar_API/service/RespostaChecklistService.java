package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.RespostaChecklist;
import com.CheckCarAPI.CheckCar_API.repository.RespostaChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaChecklistService {

    @Autowired
    private RespostaChecklistRepository respostaChecklistRepository;

    public RespostaChecklist salvar(RespostaChecklist resposta) {
        return respostaChecklistRepository.save(resposta);
    }

    public List<RespostaChecklist> listarTodos() {
        return respostaChecklistRepository.findAll();
    }

    public RespostaChecklist buscarPorId(Integer id) {
        return respostaChecklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada"));
    }

    public void deletar(Integer id) {
        respostaChecklistRepository.deleteById(id);
    }
}
