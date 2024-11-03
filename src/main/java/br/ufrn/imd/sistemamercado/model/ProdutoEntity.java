package br.ufrn.imd.sistemamercado.model;

import br.ufrn.imd.sistemamercado.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nomeProduto;
    String marca;
    LocalDate dataFabricacao;
    LocalDate dataValidade;
    String genero;
    String lote;
    Boolean ativo;

    public void ativar() {
        this.ativo = true;
    }

    public void desativar(){
        this.ativo = false;
    }

    public void carregarDTO(ProdutoDTO produto) {
        if (produto.nomeProduto() != null) {
            this.nomeProduto = produto.nomeProduto();
        }
        if (produto.marca() != null) {
            this.marca = produto.marca();
        }
        if (produto.dataFabricacao() != null) {
            this.dataFabricacao = produto.dataFabricacao();
        }
        if (produto.dataValidade() != null) {
            this.dataValidade = produto.dataValidade();
        }
        if (produto.genero() != null) {
            this.genero = produto.genero();
        }
        if (produto.lote() != null) {
            this.lote = produto.lote();
        }
        if (produto.ativo() != null) {
            this.ativo = produto.ativo();
        }
    }
}

