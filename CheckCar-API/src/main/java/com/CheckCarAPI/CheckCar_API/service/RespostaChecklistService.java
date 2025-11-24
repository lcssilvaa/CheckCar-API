package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.DTO.RespostaChecklistDTO;
import com.CheckCarAPI.CheckCar_API.entity.*;
import com.CheckCarAPI.CheckCar_API.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RespostaChecklistService {

    @Autowired
    private RespostaChecklistRepository respostaChecklistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private PerguntaChecklistRepository perguntaChecklistRepository;


    public RespostaChecklist salvar(RespostaChecklist resposta) {

        System.out.println("=== DEBUG BACKEND RECEBIDO ===");
        System.out.println("ID: " + resposta.getId());
        System.out.println("Checklist: " + (resposta.getIdLote() != null ? resposta.getIdLote().getClass() : "NULL"));
        System.out.println("Usuário: " + (resposta.getUsuario() != null ? resposta.getUsuario().getId() : "NULL"));
        System.out.println("Veículo: " + (resposta.getVeiculo() != null ? resposta.getVeiculo().getId() : "NULL"));
        System.out.println("Pergunta: " + (resposta.getPergunta() != null ? resposta.getPergunta().getId() : "NULL"));
        System.out.println("Tipo: " + resposta.getTipo());
        System.out.println("Observação: " + resposta.getObservacao());
        System.out.println("Status: " + resposta.getStatus());
        System.out.println("===============================");

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

    public List<RespostaChecklist> salvarLoteDTO(List<RespostaChecklistDTO> dtos) {
        List<RespostaChecklist> respostas = new ArrayList<>();

        for (RespostaChecklistDTO dto : dtos) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Veiculo veiculo = veiculoRepository.findById(dto.getIdVeiculo())
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

            PerguntaChecklist pergunta = perguntaChecklistRepository.findById(dto.getIdPergunta())
                    .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

            RespostaChecklist r = new RespostaChecklist();
            r.setIdLote(dto.getIdLote());
            r.setUsuario(usuario);
            r.setVeiculo(veiculo);
            r.setPergunta(pergunta);
            r.setTipo(TipoVeiculo.valueOf(dto.getTipo()));
            r.setObservacao(dto.getObservacao());
            r.setStatus(dto.getStatus());

            respostas.add(r);
        }

        return respostaChecklistRepository.saveAll(respostas);
    }
}
