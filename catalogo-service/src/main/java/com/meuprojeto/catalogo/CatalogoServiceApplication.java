package com.meuprojeto.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal do microsserviço de catálogo.
 * Inicia o Spring Boot e habilita:
 * - @SpringBootApplication (auto-configuração, component scan, etc.)
 */
@SpringBootApplication
public class CatalogoServiceApplication {

    /**
     * Ponto de entrada da aplicação.
     * @param args argumentos da linha de comando (não usados aqui)
     */
    public static void main(String[] args) {
        SpringApplication.run(CatalogoServiceApplication.class, args);
    }
}