package com.meuprojeto.catalogo.api;

import com.meuprojeto.catalogo.CatalogoServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Teste de integração com MockMvc.
 * Simula requisições HTTP sem subir servidor real.
 */
@SpringBootTest(classes = CatalogoServiceApplication.class)
@AutoConfigureMockMvc
class ServicoControllerTest {

    @Autowired
    private MockMvc mvc; // Cliente HTTP simulado

    @Test
    void deveCriarServicoComSucesso() throws Exception {
        String json = """
                {
                    "nome": "Teste",
                    "descricao": "x",
                    "preco": 10
                }
                """;

        mvc.perform(post("/servicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Teste"));
    }
}