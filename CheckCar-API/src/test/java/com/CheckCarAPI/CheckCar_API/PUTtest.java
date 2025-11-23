package com.CheckCarAPI.CheckCar_API;

import com.CheckCarAPI.CheckCar_API.controller.*;
import com.CheckCarAPI.CheckCar_API.entity.*;
import com.CheckCarAPI.CheckCar_API.service.*;

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

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PUTtest {

    private MockMvc mockMvc;

    // ===== USUARIO =====
    @Mock private UsuarioService usuarioService;
    @InjectMocks private UsuarioController usuarioController;

    // ===== CHECKLIST =====
    @Mock private ChecklistService checklistService;
    @InjectMocks private ChecklistController checklistController;

    // ===== ITEM CHECKLIST =====
    @Mock private ItemChecklistService itemChecklistService;
    @InjectMocks private ItemChecklistController itemChecklistController;

    // ===== RESPOSTA CHECKLIST =====
    @Mock private RespostaChecklistService respostaChecklistService;
    @InjectMocks private RespostaChecklistController respostaChecklistController;

    // ===== VEICULO =====
    @Mock private VeiculoService veiculoService;
    @InjectMocks private VeiculoController veiculoController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                usuarioController,
                checklistController,
                itemChecklistController,
                respostaChecklistController,
                veiculoController
        ).build();
    }

    // ============================================================
    // ======================= USUARIO ============================
    // ============================================================

    @Test
    @DisplayName("Deve atualizar um Usuario usando CPF")
    void deveAtualizarUsuario() throws Exception {

        Usuario atualizado = new Usuario();
        atualizado.setId(10);
        atualizado.setCpf("12345678900");
        atualizado.setNome("Novo Nome");

        Mockito.when(usuarioService.buscarPorCpf("12345678900"))
                .thenReturn(atualizado);

        Mockito.when(usuarioService.salvar(Mockito.any(Usuario.class)))
                .thenReturn(atualizado);

        String json = """
                {
                    "nome": "Novo Nome"
                }
                """;

        mockMvc.perform(
                put("/api/usuarios/12345678900")
                        .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.nome").value("Novo Nome"));
    }

    // ============================================================
    // ======================= CHECKLIST ==========================
    // ============================================================

    @Test
    @DisplayName("Deve atualizar um Checklist usando ID")
    void deveAtualizarChecklist() throws Exception {

        Checklist existente = new Checklist();
        existente.setId(1);

        Checklist atualizado = new Checklist();
        atualizado.setId(1);

        Mockito.when(checklistService.buscarPorId(1))
                .thenReturn(existente);

        Mockito.when(checklistService.salvar(Mockito.any(Checklist.class)))
                .thenReturn(atualizado);

        String json = """
                {
                    "usuario": { "id": 5 },
                    "veiculo": { "id": 7 }
                }
                """;

        mockMvc.perform(
                put("/api/checklists/1")
                        .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    // ============================================================
    // ================== ITEM CHECKLIST ==========================
    // ============================================================

    @Test
    @DisplayName("Deve atualizar um ItemChecklist usando ID")
    void deveAtualizarItemChecklist() throws Exception {

        ItemChecklist existente = new ItemChecklist();
        existente.setId(1);

        ItemChecklist atualizado = new ItemChecklist();
        atualizado.setId(1);

        Mockito.when(itemChecklistService.buscarPorId(1))
                .thenReturn(existente);

        Mockito.when(itemChecklistService.salvar(Mockito.any(ItemChecklist.class)))
                .thenReturn(atualizado);

        String json = """
                {
                    "descricao": "Item atualizado"
                }
                """;

        mockMvc.perform(
                put("/api/itens-checklist/1")
                        .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    // ============================================================
    // =============== RESPOSTA CHECKLIST =========================
    // ============================================================

    @Test
    @DisplayName("Deve atualizar uma RespostaChecklist usando ID")
    void deveAtualizarRespostaChecklist() throws Exception {

        RespostaChecklist existente = new RespostaChecklist();
        existente.setId(1);

        RespostaChecklist atualizado = new RespostaChecklist();
        atualizado.setId(1);

        Mockito.when(respostaChecklistService.buscarPorId(1))
                .thenReturn(existente);

        Mockito.when(respostaChecklistService.salvar(Mockito.any(RespostaChecklist.class)))
                .thenReturn(atualizado);

        String json = """
                {
                    "resposta": "Atualizada"
                }
                """;

        mockMvc.perform(
                put("/api/respostas-checklist/1")
                        .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    // ============================================================
    // ======================== VEICULO ===========================
    // ============================================================

    @Test
    @DisplayName("Deve atualizar um Veiculo usando ID")
    void deveAtualizarVeiculo() throws Exception {

        Veiculo existente = new Veiculo();
        existente.setId(1);

        Veiculo atualizado = new Veiculo();
        atualizado.setId(1);

        Mockito.when(veiculoService.buscarPorId(1))
                .thenReturn(existente);

        Mockito.when(veiculoService.salvar(Mockito.any(Veiculo.class)))
                .thenReturn(atualizado);

        String json = """
                {
                    "modelo": "Novo Modelo"
                }
                """;

        mockMvc.perform(
                put("/api/veiculos/1")
                        .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
