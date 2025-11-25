package com.meuprojeto.usuario.api;

import com.meuprojeto.usuario.UsuarioServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// ← ESSA LINHA DESATIVA O SPRING SECURITY NO TESTE
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = UsuarioServiceApplication.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void deveCriarCliente() throws Exception {
        String json = """
                {
                    "nome": "Lucas",
                    "email": "lucas@teste.com",
                    "senha": "123456"
                }
                """;

        mvc.perform(post("/usuarios/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
           .andExpect(status().isCreated()); // ← Agora passa 201
    }
}
