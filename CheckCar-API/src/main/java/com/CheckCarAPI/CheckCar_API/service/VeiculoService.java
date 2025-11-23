package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import com.CheckCarAPI.CheckCar_API.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo salvar(Veiculo veiculo) {
        Objects.requireNonNull(veiculo, "Veículo não pode ser null");
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorId(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    public Veiculo atualizar(Integer id, Veiculo veiculoAtualizado) {
        Objects.requireNonNull(id, "ID não pode ser null");
        Objects.requireNonNull(veiculoAtualizado, "Veículo atualizado não pode ser null");

        Veiculo existente = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        existente.setModelo(veiculoAtualizado.getModelo());
        existente.setPlaca(veiculoAtualizado.getPlaca());
        existente.setAno(veiculoAtualizado.getAno());
        // atualizar outros campos se houver

        return veiculoRepository.save(existente);
    }

    public void deletar(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        veiculoRepository.deleteById(id);
    }
}