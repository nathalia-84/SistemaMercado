package br.ufrn.imd.sistemamercado.dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public record ClienteDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
        String nome,

        @NotBlank(message = "O CPF é obrigatório.")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos.")
        String cpf,

        @NotBlank(message = "O gênero é obrigatório.")
        String genero,

        @NotNull(message = "A data de nascimento é obrigatória.")
        @Past(message = "A data de nascimento deve ser no passado.")
        Date dataNascimento

) {
}
