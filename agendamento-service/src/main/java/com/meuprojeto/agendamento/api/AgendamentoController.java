package com.meuprojeto.agendamento.api;

import com.meuprojeto.agendamento.domain.Agendamento;
import com.meuprojeto.agendamento.infra.AgendamentoRepository;
import com.meuprojeto.agendamento.infra.CatalogoClient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    private final AgendamentoRepository repo;
    private final CatalogoClient catalogo;

    public AgendamentoController(AgendamentoRepository repo, CatalogoClient catalogo) {
        this.repo = repo;
        this.catalogo = catalogo;
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar(@Valid @RequestBody NovoAgendamentoDTO dto) {
        if (!catalogo.servicoExiste(dto.servicoId())) {
            return ResponseEntity.badRequest().build();
        }
        Agendamento a = repo.save(new Agendamento(null, dto.clienteId(), dto.servicoId(), dto.dataHora()));
        return ResponseEntity.created(URI.create("/agendamentos/" + a.id())).body(a);
    }

    @GetMapping("/cliente/{id}")
    public List<Agendamento> listarPorCliente(@PathVariable UUID id) {
        return repo.findByCliente(id);
    }

    public record NovoAgendamentoDTO(
            @NotNull UUID clienteId,
            @NotNull UUID servicoId,
            @NotNull @Future LocalDateTime dataHora
    ) {}
}