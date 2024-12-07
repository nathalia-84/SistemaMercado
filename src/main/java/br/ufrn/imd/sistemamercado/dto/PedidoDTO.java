package br.ufrn.imd.sistemamercado.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record PedidoDTO(
        @NotBlank(message = "O código do pedido é obrigatório.")
        @Size(max = 50, message = "O código do pedido deve ter no máximo 50 caracteres.")
        String codigo,

        @NotEmpty(message = "A lista de produtos não pode estar vazia.")
        List<Long> produtosId,

        @NotNull(message = "O ID do cliente é obrigatório.")
        Long clienteId
) {

}
