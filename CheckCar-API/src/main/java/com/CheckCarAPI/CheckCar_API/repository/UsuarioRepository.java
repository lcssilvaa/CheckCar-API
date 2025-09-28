package com.CheckCarAPI.CheckCar_API.repository;

import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //define como repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCpf(String cpf);

    //vai permitir buscar o usuario no db pelo cpf (já que é único)
}
