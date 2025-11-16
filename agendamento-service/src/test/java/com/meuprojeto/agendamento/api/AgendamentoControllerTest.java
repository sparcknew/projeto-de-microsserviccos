package com.meuprojeto.agendamento.api;

import com.meuprojeto.agendamento.infra.CatalogoClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Teste de integração com Mock do CatalogoClient.
 * Simula que o serviço existe → CI passa.
 */
@SpringBootTest(classes = AgendamentoServiceApplication.class)
@AutoConfigureMockMvc
class AgendamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CatalogoClient catalogoClient; // ← MOCK!

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {
        // Simula que o serviço existe
        when(catalogoClient.servicoExiste(any(UUID.class))).thenReturn(true);

        String json = """
                {
                    "clienteId": "123e4567-e89b-12d3-a456-426614174000",
                    "servicoId": "123e4567-e89b-12d3-a456-426614174001",
                    "dataHora": "2025-12-01T10:00:00"
                }
                """;

        mvc.perform(post("/agendamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }
}
