package com.meuprojeto.agendamento.infra;

// Importa a anotação Component para permitir que esta classe seja gerenciada pelo Spring (injeção de dependência)
import org.springframework.stereotype.Component;
// Importa o RestTemplate, usado para fazer requisições HTTP para outros serviços
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component // Indica que esta classe será um bean do Spring e poderá ser injetada em outras classes
public class CatalogoClient {

    // Objeto usado para fazer requisições HTTP
    private final RestTemplate rest;

    // URL base do serviço de catálogo. Aqui está fixa para teste.
    private final String url;

    // Construtor que recebe um RestTemplate injetado pelo Spring
    public CatalogoClient(RestTemplate rest) {
        this.rest = rest;
        this.url = "http://catalogo-service:8081"; // valor padrão para teste
    }

    /**
     * Método que verifica se um serviço existe no serviço de catálogo.
     *
     * @param id UUID do serviço que queremos consultar.
     * @return true se o serviço existe, false se a requisição falhar.
     */
    public boolean servicoExiste(UUID id) {
        try {
            // Faz uma requisição GET para o endpoint do catálogo.
            // Se o objeto for encontrado, a requisição retorna sem erro.
            rest.getForObject(url + "/servicos/" + id, Object.class);

            // Se não lançar exceção, significa que o serviço existe.
            return true;
        } catch (Exception e) {
            // Qualquer exceção significa que o serviço não foi encontrado ou houve erro de comunicação.
            return false;
        }
    }
}
