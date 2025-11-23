package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.config.UsuarioServiceMockConfig;
import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import com.CheckCarAPI.CheckCar_API.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(UsuarioServiceMockConfig.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Retonar um usuário pelo CPF")
    void deveRetornarUsuarioPorCpf() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Lucas");
        usuario.setCpf("12345678900");

        when(usuarioService.buscarPorCpf("12345678900")).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/12345678900"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Lucas"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    @DisplayName("Listar todos os usuários")
    void deveListarTodosUsuarios() throws Exception {
        Usuario u1 = new Usuario();
        u1.setId(1);
        u1.setNome("Lucas");
        u1.setCpf("12345678900");

        Usuario u2 = new Usuario();
        u2.setId(2);
        u2.setNome("Maria");
        u2.setCpf("98765432100");

        when(usuarioService.listarTodos()).thenReturn(Arrays.asList(u1, u2));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Lucas"))
                .andExpect(jsonPath("$[1].nome").value("Maria"));
    }

    @Test
    @DisplayName("Cadastrar um usuário")
    void deveCadastrarUsuario() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setId(2);
        usuario.setNome("Maria");
        usuario.setCpf("98765432100");

        when(usuarioService.salvar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria"))
                .andExpect(jsonPath("$.cpf").value("98765432100"));
    }

}

