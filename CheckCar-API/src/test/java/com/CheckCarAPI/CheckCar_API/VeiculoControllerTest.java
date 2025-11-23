package com.CheckCarAPI.CheckCar_API;

import com.CheckCarAPI.CheckCar_API.controller.VeiculoController;
import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import com.CheckCarAPI.CheckCar_API.service.VeiculoService;

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
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private VeiculoController veiculoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Veiculo veiculo;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();

        veiculo = new Veiculo();
        veiculo.setId(1);
        veiculo.setModelo("Civic");
        veiculo.setMarca("Honda");
        veiculo.setAno(2020);
        veiculo.setPlaca("ABC-1234");
    }

    @Test
    @DisplayName("Deve cadastrar um novo veículo com sucesso")
    void deveCadastrarVeiculo() throws Exception {
        Mockito.when(veiculoService.salvar(any(Veiculo.class))).thenReturn(veiculo);

        mockMvc.perform(post("/api/veiculos")
                .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                .content(Objects.requireNonNull(objectMapper.writeValueAsString(veiculo))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Civic"))
                .andExpect(jsonPath("$.marca").value("Honda"));
    }

    @Test
    @DisplayName("Deve listar todos os veículos")
    void deveListarTodos() throws Exception {
        List<Veiculo> lista = Arrays.asList(veiculo);
        Mockito.when(veiculoService.listarTodos()).thenReturn(lista);

        mockMvc.perform(get("/api/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].placa").value("ABC-1234"));
    }

    @Test
    @DisplayName("Deve buscar veículo por ID")
    void deveBuscarPorId() throws Exception {
        Mockito.when(veiculoService.buscarPorId(1)).thenReturn(veiculo);

        mockMvc.perform(get("/api/veiculos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Civic"));
    }

    @Test
    @DisplayName("Deve deletar veículo por ID")
    void deveDeletarPorId() throws Exception {
        Mockito.doNothing().when(veiculoService).deletar(1);

        mockMvc.perform(delete("/api/veiculos/1"))
                .andExpect(status().isNoContent());
    }
}
