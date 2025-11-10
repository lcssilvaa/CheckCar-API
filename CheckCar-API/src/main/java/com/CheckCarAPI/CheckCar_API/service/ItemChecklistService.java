package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.repository.ItemChecklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItemChecklistService {

    private final ItemChecklistRepository itemChecklistRepository;

    public ItemChecklistService(ItemChecklistRepository itemChecklistRepository) {
        this.itemChecklistRepository = itemChecklistRepository;
    }

    public ItemChecklist salvar(ItemChecklist item) {
        Objects.requireNonNull(item, "ItemChecklist não pode ser null");
        return itemChecklistRepository.save(item);
    }

    public List<ItemChecklist> listarTodos() {
        List<ItemChecklist> lista = itemChecklistRepository.findAll();
        return Objects.requireNonNull(lista, "Lista de itens não pode ser null");
    }

    public ItemChecklist buscarPorId(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        return itemChecklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public void deletar(Integer id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        itemChecklistRepository.deleteById(id);
    }

    public List<ItemChecklist> listarPorTipoVeiculo(TipoVeiculo tipo) {
        Objects.requireNonNull(tipo, "TipoVeiculo não pode ser null");
        List<ItemChecklist> lista = itemChecklistRepository.findByTipoVeiculo(tipo);
        return Objects.requireNonNull(lista, "Lista de itens por tipo não pode ser null");
    }
}
