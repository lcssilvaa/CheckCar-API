package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.config.UsuarioServiceMockConfig;
import com.CheckCarAPI.CheckCar_API.entity.Checklist;
import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import com.CheckCarAPI.CheckCar_API.service.ChecklistService;
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
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(UsuarioServiceMockConfig.class)
public class ChecklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ChecklistService checklistService;

    @Test
    @DisplayName("Retornar Checklist por ID")
    void deveRetornarChecklistPorID() throws Exception {
        Checklist c1 = new Checklist();
        c1.setId(2);
        c1.setDataCheckList(LocalDateTime.now());

        when(checklistService.buscarPorId(2)).thenReturn(c1);

        mockMvc.perform(get("/api/checklists/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Listar todos os Checklists")
    void deveListarTodosChecklists() throws Exception {
        Checklist c1 = new Checklist();
        c1.setId(2);
        c1.setDataCheckList(LocalDateTime.now());

        Checklist c2 = new Checklist();
        c2.setId(3);
        c2.setDataCheckList(LocalDateTime.now());

        when(checklistService.listarTodos()).thenReturn(Arrays.asList(c1, c2));

        mockMvc.perform(get("/api/checklists"))
                .andExpect(status().isOk());
    }

}

