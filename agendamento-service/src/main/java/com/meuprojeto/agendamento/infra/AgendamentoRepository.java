package com.meuprojeto.agendamento.infra;

import com.meuprojeto.agendamento.domain.Agendamento;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AgendamentoRepository {
    private final Map<UUID, Agendamento> db = new ConcurrentHashMap<>();

    public Agendamento save(Agendamento a) {
        UUID id = a.id() == null ? UUID.randomUUID() : a.id();
        Agendamento salvo = new Agendamento(id, a.clienteId(), a.servicoId(), a.dataHora());
        db.put(id, salvo);
        return salvo;
    }

    public List<Agendamento> findByCliente(UUID clienteId) {
        return db.values().stream()
                .filter(a -> a.clienteId().equals(clienteId))
                .toList();
    }
}