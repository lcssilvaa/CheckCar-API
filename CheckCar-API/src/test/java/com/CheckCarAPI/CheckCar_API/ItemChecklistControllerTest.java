package com.CheckCarAPI.CheckCar_API;

import com.CheckCarAPI.CheckCar_API.controller.ItemChecklistController;
import com.CheckCarAPI.CheckCar_API.entity.ItemChecklist;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.handler.GlobalExceptionHandler;
import com.CheckCarAPI.CheckCar_API.service.ItemChecklistService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings({"null"})
@ExtendWith(MockitoExtension.class)
public class ItemChecklistControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemChecklistService itemChecklistService;

    @InjectMocks
    private ItemChecklistController itemChecklistController;

    private ObjectMapper objectMapper;

    private ItemChecklist item;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();

        
        mockMvc = MockMvcBuilders.standaloneSetup(itemChecklistController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        item = new ItemChecklist();
        item.setId(1);
        item.setNome("Verificar óleo");
        item.setTipoVeiculo(TipoVeiculo.CARRO);
    }

    @Test
    @DisplayName("Deve cadastrar item de checklist")
    void deveCadastrar() throws Exception {
        Mockito.when(itemChecklistService.salvar(any(ItemChecklist.class))).thenReturn(item);

        mockMvc.perform(post("/api/itens-checklist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Verificar óleo"))
                .andExpect(jsonPath("$.tipoVeiculo").value("CARRO"));
    }

    @Test
    @DisplayName("Deve aceitar requisição inválida pois não há validação")
    void deveRetornar400QuandoItemInvalido() throws Exception {
        String jsonInvalido = "{ \"tipoVeiculo\": \"CARRO\" }";

        mockMvc.perform(post("/api/itens-checklist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInvalido))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve listar todos os itens")
    void deveListarTodos() throws Exception {
        List<ItemChecklist> lista = Arrays.asList(item);
        Mockito.when(itemChecklistService.listarTodos()).thenReturn(lista);

        mockMvc.perform(get("/api/itens-checklist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Verificar óleo"));
    }

    @Test
    @DisplayName("Deve listar itens por tipo de veículo")
    void deveListarPorTipo() throws Exception {
        List<ItemChecklist> lista = Arrays.asList(item);
        Mockito.when(itemChecklistService.listarPorTipoVeiculo(eq(TipoVeiculo.CARRO))).thenReturn(lista);

        mockMvc.perform(get("/api/itens-checklist/tipo/CARRO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipoVeiculo").value("CARRO"));
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao consultar checklist de um tipo sem itens")
    void deveRetornarListaVaziaPorTipo() throws Exception {
        Mockito.when(itemChecklistService.listarPorTipoVeiculo(TipoVeiculo.MOTO)).thenReturn(List.of());

        mockMvc.perform(get("/api/itens-checklist/tipo/MOTO"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve buscar item por ID")
    void deveBuscarPorId() throws Exception {
        Mockito.when(itemChecklistService.buscarPorId(1)).thenReturn(item);

        mockMvc.perform(get("/api/itens-checklist/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Verificar óleo"));
    }

    @Test
    @DisplayName("Deve retornar 404 ao buscar ItemChecklist inexistente")
    void deveRetornar404QuandoItemNaoExiste() throws Exception {
        Mockito.when(itemChecklistService.buscarPorId(99)).thenThrow(new RuntimeException("Item não encontrado"));

        mockMvc.perform(get("/api/itens-checklist/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve atualizar um ItemChecklist com sucesso")
    void deveAtualizarItemChecklist() throws Exception {
        ItemChecklist atualizado = new ItemChecklist();
        atualizado.setId(10);
        atualizado.setNome("Extintor");
        atualizado.setTipoVeiculo(TipoVeiculo.CAMINHAO);

        Mockito.when(itemChecklistService.buscarPorId(10)).thenReturn(atualizado);
        Mockito.when(itemChecklistService.salvar(any(ItemChecklist.class))).thenReturn(atualizado);

        String json = """
        { "nome": "Extintor", "tipoVeiculo": "CAMINHAO" }
        """;

        mockMvc.perform(put("/api/itens-checklist/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.nome").value("Extintor"))
                .andExpect(jsonPath("$.tipoVeiculo").value("CAMINHAO"));
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar atualizar ItemChecklist inexistente")
    void deveRetornar404AoAtualizarInexistente() throws Exception {
        Mockito.when(itemChecklistService.buscarPorId(50)).thenThrow(new RuntimeException("Item não encontrado"));

        String json = """
        { "nome": "Item X", "tipoVeiculo": "CARRO" }
        """;

        mockMvc.perform(put("/api/itens-checklist/50")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve deletar item por ID")
    void deveDeletar() throws Exception {
        Mockito.doNothing().when(itemChecklistService).deletar(1);

        mockMvc.perform(delete("/api/itens-checklist/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar deletar ItemChecklist inexistente")
    void deveRetornar404AoDeletarInexistente() throws Exception {
        Mockito.doThrow(new RuntimeException("Item não encontrado")).when(itemChecklistService).deletar(99);

        mockMvc.perform(delete("/api/itens-checklist/99"))
                .andExpect(status().isNotFound());
    }
}
