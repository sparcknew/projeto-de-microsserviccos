package com.meuprojeto.usuario.infra;

import com.meuprojeto.usuario.domain.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UsuarioRepository {
    private final Map<UUID, Usuario> db = new ConcurrentHashMap<>();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        saveCliente("Ana", "ana@cliente.com", "123");
    }

    public Usuario save(Usuario u) {
        String senhaCripto = encoder.encode(u.senha());
        UUID id = u.id() == null ? UUID.randomUUID() : u.id();
        Usuario salvo = new Usuario(id, u.nome(), u.email(), senhaCripto, u.tipo());
        db.put(id, salvo);
        return salvo;
    }

    public Optional<Usuario> findByEmail(String email) {
        return db.values().stream().filter(u -> u.email().equals(email)).findFirst();
    }

    public Optional<Usuario> findById(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    private void saveCliente(String n, String e, String s) {
        save(new Usuario(null, n, e, s, Usuario.TipoUsuario.CLIENTE));
    }
}