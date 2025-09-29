package com.CheckCarAPI.CheckCar_API.service;

import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.repository.ItemChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemChecklistService {

    @Autowired
    private ItemChecklistRepository itemChecklistRepository;

    public ItemChecklist salvar(ItemChecklist item) {
        return itemChecklistRepository.save(item);
    }

    public List<ItemChecklist> listarTodos() {
        return itemChecklistRepository.findAll();
    }

    public ItemChecklist buscarPorId(Integer id) {
        return itemChecklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public void deletar(Integer id) {
        itemChecklistRepository.deleteById(id);
    }

    public List<ItemChecklist> listarPorTipoVeiculo(TipoVeiculo tipo) {
        return itemChecklistRepository.findByTipoVeiculo(tipo);
    }
}

