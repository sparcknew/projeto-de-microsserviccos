package com.meuprojeto.agendamento.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


/**
 * Cliente HTTP para validar serviço no catálogo.
 * Usa RestTemplate (simples e suficiente).
 */
@Component
public class CatalogoClient {
    private final RestTemplate rest;
    private final String url;

    /** Injeta URL via application.properties */
    public CatalogoClient(RestTemplate rest, @Value("${catalogo.url}") String url) {
        this.rest = rest;
        this.url = url;
    }

    /**
     * Verifica se o serviço existe.
     * Retorna false se der erro (404, etc.).
     */
    public boolean servicoExiste(UUID id) {
        try {
            rest.getForObject(url + "/servicos/" + id, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}