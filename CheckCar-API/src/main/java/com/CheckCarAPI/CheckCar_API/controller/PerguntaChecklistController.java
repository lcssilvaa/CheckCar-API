package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.PerguntaChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.service.PerguntaChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perguntas")
public class PerguntaChecklistController {

    @Autowired
    private PerguntaChecklistService service;

    @GetMapping
    public List<PerguntaChecklist> listarPorTipo(@RequestParam TipoVeiculo tipoVeiculo) {
        return service.listarPorTipo(tipoVeiculo);
    }

    @PostMapping
    public PerguntaChecklist cadastrar(@RequestBody PerguntaChecklist pergunta) {
        return service.cadastrar(pergunta);
    }

    @GetMapping("/todas")
    public List<PerguntaChecklist> listarTodasAtivas() {
        return service.listarTodasAtivas();
    }
}
