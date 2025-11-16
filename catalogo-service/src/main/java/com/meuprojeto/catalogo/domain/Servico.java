package com.meuprojeto.catalogo.domain;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Representa um serviço oferecido por um empreendedor.
 * Usa record (imutável, com equals/hashCode automáticos).
 */
public record Servico(
        UUID id,                    // Identificador único
        String nome,                // Ex: "Corte Masculino"
        String descricao,           // Detalhes do serviço
        BigDecimal preco,           // Valor monetário (evita float)
        UUID empreendedorId         // Dono do serviço
) {}