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

@SpringBootTest(classes = UsuarioServiceApplication.class)
@AutoConfigureMockMvc
class UsuarioControllerTest {
    @Autowired MockMvc mvc;

    @Test
    void deveCriarCliente() throws Exception {
        String json = "{\"nome\":\"Teste\",\"email\":\"teste@x.com\",\"senha\":\"123\"}";
        mvc.perform(post("/usuarios/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }
}