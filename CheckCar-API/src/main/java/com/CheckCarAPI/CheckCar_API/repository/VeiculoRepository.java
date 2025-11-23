package com.CheckCarAPI.CheckCar_API.repository;

import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    Optional<Veiculo> findByPlaca(String placa);
}
