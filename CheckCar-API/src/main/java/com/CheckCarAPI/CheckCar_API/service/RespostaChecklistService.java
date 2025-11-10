package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.RespostaChecklist;
import com.CheckCarAPI.CheckCar_API.repository.RespostaChecklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RespostaChecklistService {

    private final RespostaChecklistRepository respostaChecklistRepository;

    public RespostaChecklistService(RespostaChecklistRepository respostaChecklistRepository) {
        this.respostaChecklistRepository = respostaChecklistRepository;
    }

    public RespostaChecklist salvar(RespostaChecklist resposta) {
        Objects.requireNonNull(resposta, "RespostaChecklist não pode ser null");
        return respostaChecklistRepository.save(resposta);
    }

    public List<RespostaChecklist> listarTodos() {
        List<RespostaChecklist> lista = respostaChecklistRepository.findAll();
        return Objects.requireNonNull(lista, "Lista de respostas não pode ser null");
    }

    public RespostaChecklist buscarPorId(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        return respostaChecklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada"));
    }

    public void deletar(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        respostaChecklistRepository.deleteById(id);
    }

    public List<RespostaChecklist> salvarLote(List<RespostaChecklist> respostas) {
        Objects.requireNonNull(respostas, "Lista de respostas não pode ser null");
        return respostaChecklistRepository.saveAll(respostas);
    }
}
