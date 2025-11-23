package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.config.UsuarioServiceMockConfig;
import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import com.CheckCarAPI.CheckCar_API.entity.PerguntaChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.service.PerguntaChecklistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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
public class PerguntaChecklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PerguntaChecklistService perguntaChecklistService;

    @Test
    @DisplayName("Listar as perguntas por tipo de checklist")
    void listarPorTipo() throws Exception {
        PerguntaChecklist p1 = new PerguntaChecklist();
        p1.setId(1L);
        p1.setTexto("Descreva o estado da suspensão");
        p1.setTipoVeiculo(TipoVeiculo.CARRO);

        when(perguntaChecklistService.listarPorTipo(TipoVeiculo.CARRO))
                .thenReturn(Arrays.asList(p1));

        mockMvc.perform(get("/api/perguntas")
                        .param("tipoVeiculo", "CARRO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].texto").value("Descreva o estado da suspensão"))
                .andExpect(jsonPath("$[0].tipoVeiculo").value("CARRO"));
    }

    @Test
    @DisplayName("Listar todos as perguntas")
    void deveListarTodasPerguntas() throws Exception {
        PerguntaChecklist p1 = new PerguntaChecklist();
        p1.setId(1L);
        p1.setTexto("Descreva o estado da suspensão");
        p1.setTipoVeiculo(TipoVeiculo.CARRO);

        PerguntaChecklist p2 = new PerguntaChecklist();
        p2.setId(2L);
        p2.setTexto("Descreva o estado do freio");
        p2.setTipoVeiculo(TipoVeiculo.CARRO);

        PerguntaChecklist p3 = new PerguntaChecklist();
        p3.setId(3L);
        p3.setTexto("Qual peça foi utilizada?");
        p3.setTipoVeiculo(TipoVeiculo.CARRO);

        when(perguntaChecklistService.listarTodasAtivas()).thenReturn(Arrays.asList(p1, p2, p3));

        mockMvc.perform(get("/api/perguntas/todas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].texto").value("Descreva o estado da suspensão"))
                .andExpect(jsonPath("$[1].texto").value("Descreva o estado do freio"))
                .andExpect(jsonPath("$[2].texto").value("Qual peça foi utilizada?"));
    }

    @Test
    @DisplayName("Cadastrar uma Pergunta")
    void deveCadastrarPergunta() throws Exception {
        PerguntaChecklist p1 = new PerguntaChecklist();
        p1.setId(5L);
        p1.setTexto("O motor apresenta barulho?");
        p1.setTipoVeiculo(TipoVeiculo.CARRO);

        when(perguntaChecklistService.cadastrar(any(PerguntaChecklist.class))).thenReturn(p1);

        mockMvc.perform(post("/api/perguntas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.texto").value("O motor apresenta barulho?"))
                .andExpect(jsonPath("$.tipoVeiculo").value("CARRO"));
    }

}

