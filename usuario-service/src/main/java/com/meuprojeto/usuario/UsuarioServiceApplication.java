package com.meuprojeto.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe principal do usuário-service.
 * Adiciona o BCryptPasswordEncoder como bean (OBRIGATÓRIO para o controller).
 */
@SpringBootApplication
public class UsuarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioServiceApplication.class, args);
    }

    /**
     * Fornece o encoder para criptografia de senhas.
     * Sem isso → o controller falha na injeção → teste quebra.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
