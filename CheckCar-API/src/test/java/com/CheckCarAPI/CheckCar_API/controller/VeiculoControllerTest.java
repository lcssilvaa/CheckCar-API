package com.CheckCarAPI.CheckCar_API.controller;

import com.CheckCarAPI.CheckCar_API.config.UsuarioServiceMockConfig;
import com.CheckCarAPI.CheckCar_API.entity.TipoVeiculo;
import com.CheckCarAPI.CheckCar_API.entity.Veiculo;
import com.CheckCarAPI.CheckCar_API.service.VeiculoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@Import(UsuarioServiceMockConfig.class)
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VeiculoService veiculoService;

    @Test
    @DisplayName("Buscar o veículo pelo ID")
    void deveBuscarPorId() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(3);
        veiculo.setPlaca("ABC1234");
        veiculo.setTipo(TipoVeiculo.CARRO);
        veiculo.setModelo("Onyx LT");
        veiculo.setAno(2022);
        veiculo.setMarca("Chevrolet");

        when(veiculoService.buscarPorId(3)).thenReturn(veiculo);

        mockMvc.perform(get("/api/veiculos/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ano").value(2022))
                .andExpect(jsonPath("$.placa").value("ABC1234"));
    }

    @Test
    @DisplayName("Listar todos os veículos")
    void deveListarTodosVeiculos() throws Exception {
        Veiculo v1 = new Veiculo();
        v1.setId(3);
        v1.setPlaca("ABC1234");
        v1.setTipo(TipoVeiculo.CARRO);
        v1.setModelo("Onyx LT");
        v1.setAno(2022);
        v1.setMarca("Chevrolet");


        Veiculo v2 = new Veiculo();
        v2.setId(6);
        v2.setPlaca("DEF5678");
        v2.setTipo(TipoVeiculo.CARRO);
        v2.setModelo("Civic");
        v2.setAno(2025);
        v2.setMarca("Honda");

        when(veiculoService.listarTodos()).thenReturn(Arrays.asList(v1, v2));

        mockMvc.perform(get("/api/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].placa").value("ABC1234"))
                .andExpect(jsonPath("$[1].placa").value("DEF5678"));
    }

    @Test
    @DisplayName("Cadastrar um veículo")
    void deveCadastrarVeiculo() throws Exception {
    
        Veiculo veiculo = new Veiculo();
        veiculo.setId(7);
        veiculo.setModelo("Palio");
        veiculo.setMarca("Fiat");
        veiculo.setAno(2015);
        veiculo.setPlaca("GHI1234");
        
        when(veiculoService.salvar(any(Veiculo.class))).thenReturn(veiculo);

   mockMvc.perform(post("/api/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(veiculo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Palio"))
                .andExpect(jsonPath("$.marca").value("Fiat"))
                .andExpect(jsonPath("$.placa").value("GHI1234"));
    }

}
