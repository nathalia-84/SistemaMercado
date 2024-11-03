package br.ufrn.imd.sistemamercado.dto;

import java.util.Date;

public record ClienteDTO(
        String nome,
        String cpf,
        String genero,
        Date dataNascimento,
        Boolean ativo
) {
}
