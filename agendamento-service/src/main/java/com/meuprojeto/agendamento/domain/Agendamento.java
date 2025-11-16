package com.meuprojeto.agendamento.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record Agendamento(UUID id, UUID clienteId, UUID servicoId, LocalDateTime dataHora) {}