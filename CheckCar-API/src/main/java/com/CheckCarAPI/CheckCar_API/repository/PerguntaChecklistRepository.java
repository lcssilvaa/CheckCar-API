package com.CheckCarAPI.CheckCar_API.repository;

import com.CheckCarAPI.CheckCar_API.entity.PerguntaChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface PerguntaChecklistRepository extends JpaRepository<PerguntaChecklist, Long> {
        List<PerguntaChecklist> findByTipoVeiculoAndAtivoTrue(TipoVeiculo tipoVeiculo);

    List<PerguntaChecklist> findByAtivoTrue();
}
    



