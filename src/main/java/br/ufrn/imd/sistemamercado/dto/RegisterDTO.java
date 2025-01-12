package br.ufrn.imd.sistemamercado.dto;

import br.ufrn.imd.sistemamercado.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
