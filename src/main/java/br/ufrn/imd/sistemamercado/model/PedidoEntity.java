package br.ufrn.imd.sistemamercado.model;

import br.ufrn.imd.sistemamercado.dto.PedidoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String codigo;
    @ManyToMany
    @JoinTable(
            name = "produtos_pedidos",
            joinColumns = @JoinColumn(name = "pedido_fk"),
            inverseJoinColumns = @JoinColumn(name = "produto_fk")
    )
    List<ProdutoEntity> produtos;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    ClienteEntity cliente;

    Boolean ativo;

    public PedidoEntity(PedidoDTO pedidoDTO, ClienteEntity cliente, List<ProdutoEntity> produtos) {
        this.codigo = pedidoDTO.codigo();
        this.produtos = produtos;
        this.cliente = cliente;
        this.ativo = true;
    }

    public void desativar(){
        this.ativo = false;
    }

    public void atualizar(PedidoDTO pedidoDTO, ClienteEntity cliente, List<ProdutoEntity> produtos) {
        this.codigo = pedidoDTO.codigo();
        this.produtos = produtos;
        this.cliente = cliente;
    }

    public void salvarProduto(ProdutoEntity produto) {
        this.produtos.add(produto);
    }

    public boolean removerProduto(ProdutoEntity produto) {
        return this.produtos.remove(produto);
    }
}
