package com.CheckCarAPI.CheckCar_API.repository;

import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemChecklistRepository extends JpaRepository <ItemChecklist, Integer> {
}
