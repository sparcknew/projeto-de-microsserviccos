package com.meuprojeto.catalogo.infra;

import com.meuprojeto.catalogo.domain.Servico;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repositório em memória (sem banco real).
 * Usa ConcurrentHashMap para thread-safety.
 */
@Component
public class ServicoRepository {

    /** Banco de dados simulado */
    private final Map<UUID, Servico> db = new ConcurrentHashMap<>();

    /**
     * Executa após a injeção de dependências.
     * Cria dados iniciais para testes.
     */
    @PostConstruct
    public void init() {
        UUID emp = UUID.randomUUID();
        save(new Servico(UUID.randomUUID(), "Corte", "Corte masculino", new BigDecimal("50"), emp));
    }

    /**
     * Salva um serviço.
     * Gera ID se não existir.
     */
    public Servico save(Servico s) {
        if (s.id() == null) {
            s = new Servico(UUID.randomUUID(), s.nome(), s.descricao(), s.preco(), s.empreendedorId());
        }
        db.put(s.id(), s);
        return s;
    }

    /** Busca por ID */
    public Optional<Servico> findById(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    /** Lista todos */
    public List<Servico> findAll() {
        return new ArrayList<>(db.values());
    }
}