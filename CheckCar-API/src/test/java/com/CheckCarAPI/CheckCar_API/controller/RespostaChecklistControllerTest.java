package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.config.UsuarioServiceMockConfig;
import com.CheckCarAPI.CheckCar_API.entity.*;
import com.CheckCarAPI.CheckCar_API.service.PerguntaChecklistService;
import com.CheckCarAPI.CheckCar_API.service.RespostaChecklistService;
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
public class RespostaChecklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RespostaChecklistService respostaChecklistService;

    @Test
    @DisplayName("Listar as perguntas por tipo de checklist")
    void buscarPorID() throws Exception {
        RespostaChecklist r1 = new RespostaChecklist();
        r1.setId(2);
        r1.setObservacao("Tudo certo com o nível de óleo");

        when(respostaChecklistService.buscarPorId(2)).thenReturn(r1);

        mockMvc.perform(get("/api/respostas-checklist/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.observacao").value("Tudo certo com o nível de óleo"));
    }

    @Test
    @DisplayName("Listar todos as respostas")
    void deveListarTodasRespostas() throws Exception {
        RespostaChecklist r1 = new RespostaChecklist();
        r1.setId(1);
        r1.setObservacao("teste");

        RespostaChecklist r2 = new RespostaChecklist();
        r2.setId(2);
        r2.setObservacao("Tudo certo com o nível de óleo");

        RespostaChecklist r3 = new RespostaChecklist();
        r3.setId(3);
        r3.setObservacao("Pneu dianteiro murcho");

        when(respostaChecklistService.listarTodos()).thenReturn(Arrays.asList(r1, r2, r3));

        mockMvc.perform(get("/api/respostas-checklist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].observacao").value("teste"))
                .andExpect(jsonPath("$[1].observacao").value("Tudo certo com o nível de óleo"))
                .andExpect(jsonPath("$[2].observacao").value("Pneu dianteiro murcho"));
    }

    @Test
    @DisplayName("Cadastrar uma Resposta")
    void deveCadastrarResposta() throws Exception {
        RespostaChecklist r4 = new RespostaChecklist();
        r4.setId(4);
        r4.setObservacao("Resposta teste");

        when(respostaChecklistService.salvar(any(RespostaChecklist.class))).thenReturn(r4);

        mockMvc.perform(post("/api/respostas-checklist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(r4)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.observacao").value("Resposta teste"));
    }

}

