package com.CheckCarAPI.CheckCar_API.config;

import com.CheckCarAPI.CheckCar_API.service.UsuarioService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UsuarioServiceMockConfig {
    @Bean
    public UsuarioService usuarioService() {
        return Mockito.mock(UsuarioService.class);
    }
}


