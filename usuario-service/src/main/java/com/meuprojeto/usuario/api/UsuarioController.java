package com.meuprojeto.usuario.api;

import com.meuprojeto.usuario.domain.Usuario;
import com.meuprojeto.usuario.infra.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;
/**
 * Controlador de autenticação e cadastro.
 * Usa BCrypt para criptografar senhas.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UsuarioController(UsuarioRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }
    /**
     * Cadastra cliente.
     * Criptografa senha automaticamente.
     */
    @PostMapping("/cliente")
    public ResponseEntity<Usuario> criarCliente(@Valid @RequestBody CriarUsuarioDTO dto) {
        Usuario u = new Usuario(null, dto.nome(), dto.email(), dto.senha(), Usuario.TipoUsuario.CLIENTE);
        Usuario salvo = repo.save(u);
        return ResponseEntity.created(URI.create("/usuarios/" + salvo.id())).body(salvo);
    }

    /**
     * Login simples: retorna ID se válido.
     * Usa encoder.matches() para comparar hash.
     */
    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO dto) {
        return repo.findByEmail(dto.email())
                .filter(u -> encoder.matches(dto.senha(), u.senha()))
                .map(u -> ResponseEntity.ok(u.id().toString()))
                .orElse(ResponseEntity.status(401).body("Inválido"));
    }

    public record CriarUsuarioDTO(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String senha) {}
    public record LoginDTO(@NotBlank @Email String email, @NotBlank String senha) {}
}