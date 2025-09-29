package com.CheckCarAPI.CheckCar_API.repository;

import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemChecklistRepository extends JpaRepository <ItemChecklist, Integer> {
    List<ItemChecklist> findByTipoVeiculo(TipoVeiculo tipo);
}
