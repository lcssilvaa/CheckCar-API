package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.entity.PerguntaChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.repository.PerguntaChecklistRepository;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/perguntas")
public class PerguntaChecklistController {

    private final PerguntaChecklistRepository repository;

    public PerguntaChecklistController(PerguntaChecklistRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<PerguntaChecklist> listarPorTipo(@RequestParam TipoVeiculo tipoVeiculo) {
        return repository.findByTipoVeiculoAndAtivoTrue(tipoVeiculo);
    }

    @GetMapping("/todas")
    public List<PerguntaChecklist> listarTodasAtivas() {
        return repository.findByAtivoTrue();
    }

    @PostMapping
    public @NonNull PerguntaChecklist cadastrar(@RequestBody @NonNull PerguntaChecklist pergunta) {
        PerguntaChecklist salvo = Objects.requireNonNull(repository.save(pergunta), "Erro ao salvar pergunta");
        return salvo;
    }

    @PutMapping("/{id}")
    public @NonNull PerguntaChecklist atualizar(@PathVariable("id") @NonNull Long id,
                                                 @RequestBody @NonNull PerguntaChecklist pergunta) {
        PerguntaChecklist existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

        existente.setTexto(pergunta.getTexto());
        existente.setTipoVeiculo(pergunta.getTipoVeiculo());
        existente.setTipoResposta(pergunta.getTipoResposta());
        existente.setAtivo(pergunta.getAtivo());

        PerguntaChecklist atualizado = Objects.requireNonNull(repository.save(existente), "Erro ao atualizar pergunta");
        return atualizado;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") @NonNull Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Pergunta não encontrada");
        }
        repository.deleteById(id);
    }
}
