package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.config.UsuarioServiceMockConfig;
import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.entity.Usuario;
import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import com.CheckCarAPI.CheckCar_API.service.ItemChecklistService;
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

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(UsuarioServiceMockConfig.class)
public class ItemChecklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ItemChecklistService itemChecklistService;

    @Test
    @DisplayName("Retornar item por ID")
    void deveRetornarItemPorID() throws Exception {
        ItemChecklist i1 = new ItemChecklist();
        i1.setId(1);
        i1.setNome("Óleo");
        i1.setTipoVeiculo(TipoVeiculo.CARRO);

        when(itemChecklistService.buscarPorId(1)).thenReturn(i1);

        mockMvc.perform(get("/api/itens-checklist/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Óleo"))
                .andExpect(jsonPath("$.tipoVeiculo").value("CARRO"));
    }

    @Test
    @DisplayName("Listar todos os itens")
    void deveListarTodosItens() throws Exception {
        ItemChecklist i1 = new ItemChecklist();
        i1.setId(1);
        i1.setNome("Óleo");
        i1.setTipoVeiculo(TipoVeiculo.CARRO);

        ItemChecklist i2 = new ItemChecklist();
        i2.setId(3);
        i2.setNome("Prego");
        i2.setTipoVeiculo(TipoVeiculo.MOTO);

        ItemChecklist i3 = new ItemChecklist();
        i3.setId(4);
        i3.setNome("Marcha");
        i3.setTipoVeiculo(TipoVeiculo.CARRO);

        when(itemChecklistService.listarTodos()).thenReturn(Arrays.asList(i1, i2, i3));

        mockMvc.perform(get("/api/itens-checklist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Óleo"))
                .andExpect(jsonPath("$[1].nome").value("Prego"))
                .andExpect(jsonPath("$[2].nome").value("Marcha"));
    }

    @Test
    @DisplayName("Cadastrar um Item")
    void deveCadastrarItem() throws Exception {

        ItemChecklist i1 = new ItemChecklist();
        i1.setId(5);
        i1.setNome("Pneu");
        i1.setTipoVeiculo(TipoVeiculo.CAMINHAO);

        when(itemChecklistService.salvar(any(ItemChecklist.class))).thenReturn(i1);

        mockMvc.perform(post("/api/itens-checklist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(i1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pneu"))
                .andExpect(jsonPath("$.tipoVeiculo").value("CAMINHAO"));
    }

}

