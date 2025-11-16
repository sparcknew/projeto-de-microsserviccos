package com.meuprojeto.usuario.domain;

import java.util.UUID;

public record Usuario(UUID id, String nome, String email, String senha, TipoUsuario tipo) {
    public enum TipoUsuario { CLIENTE, EMPREENDEDOR }
}