package br.ufrn.imd.sistemamercado.dto;
import java.time.LocalDate;

public record ProdutoDTO(
        String nomeProduto,
        String marca,
        LocalDate dataFabricacao,
        LocalDate dataValidade,
        String genero,
        String lote,
        Boolean ativo
) {
}
