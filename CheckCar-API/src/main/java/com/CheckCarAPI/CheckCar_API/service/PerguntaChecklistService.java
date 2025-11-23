package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.PerguntaChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.repository.PerguntaChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntaChecklistService {

    @Autowired
    private PerguntaChecklistRepository repository;

    public List<PerguntaChecklist> listarPorTipo(TipoVeiculo tipoVeiculo) {
        return repository.findByTipoVeiculoAndAtivoTrue(tipoVeiculo);
    }

    public PerguntaChecklist cadastrar(PerguntaChecklist pergunta) {
        return repository.save(pergunta);
    }

    public List<PerguntaChecklist> listarTodasAtivas() {
        return repository.findByAtivoTrue();
    }
}
