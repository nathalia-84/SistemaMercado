package br.ufrn.imd.sistemamercado.dto;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ProdutoDTO(
        @NotBlank(message = "O nome do produto é obrigatório.")
        @Size(max = 100, message = "O nome do produto não pode ter mais de 100 caracteres.")
        String nomeProduto,

        @NotBlank(message = "A marca é obrigatória.")
        @Size(max = 50, message = "A marca não pode ter mais de 50 caracteres.")
        String marca,

        @NotNull(message = "A data de fabricação é obrigatória.")
        @PastOrPresent(message = "A data de fabricação não pode estar no futuro.")
        LocalDate dataFabricacao,

        @NotNull(message = "A data de validade é obrigatória.")
        @Future(message = "A data de validade deve ser no futuro.")
        LocalDate dataValidade,

        @NotBlank(message = "O gênero é obrigatório.")
        String genero,

        @NotBlank(message = "O lote é obrigatório.")
        String lote
) {
}
