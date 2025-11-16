package com.meuprojeto.catalogo.api;

import com.meuprojeto.catalogo.domain.Servico;
import com.meuprojeto.catalogo.infra.ServicoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para serviços.
 * Endpoints: POST /servicos, GET /servicos
 */
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoRepository repo;

    /** Injeção via construtor (melhor prática) */
    public ServicoController(ServicoRepository repo) {
        this.repo = repo;
    }

    /** Lista todos os serviços */
    @GetMapping
    public List<Servico> listar() {
        return repo.findAll();
    }

    /**
     * Cria um novo serviço.
     * Valida entrada com Bean Validation.
     */
    @PostMapping
    public ResponseEntity<Servico> criar(@RequestBody @Valid NovoServicoDTO dto) {
        Servico s = repo.save(new Servico(null, dto.nome(), dto.descricao(), dto.preco(), UUID.randomUUID()));
        return ResponseEntity
                .created(URI.create("/servicos/" + s.id()))
                .body(s);
    }

    /** DTO para criação (evita expor senha ou campos internos) */
    public record NovoServicoDTO(
            @NotBlank String nome,
            @NotBlank String descricao,
            @Positive BigDecimal preco
    ) {}
}