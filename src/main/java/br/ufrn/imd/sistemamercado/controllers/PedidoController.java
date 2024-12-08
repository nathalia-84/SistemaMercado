package br.ufrn.imd.sistemamercado.controllers;

import br.ufrn.imd.sistemamercado.dto.PedidoDTO;
import br.ufrn.imd.sistemamercado.dto.ProdutoDTO;
import br.ufrn.imd.sistemamercado.model.PedidoEntity;
import br.ufrn.imd.sistemamercado.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/")
    public ResponseEntity<List<PedidoEntity>> getAll() {
        List<PedidoEntity> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> getById(@PathVariable Long id) {
        PedidoEntity pedido = pedidoService.getPedidoById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/")
    public ResponseEntity<PedidoEntity> createPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        PedidoEntity novoPedido = pedidoService.createPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntity> updatePedido(@PathVariable Long id, @Valid @RequestBody PedidoDTO pedidoDTO) {
        PedidoEntity pedidoAtualizado = pedidoService.updatePedido(id, pedidoDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.ok("O pedido foi deletado com sucesso");
    }

    @DeleteMapping("/dl/{id}")
    public ResponseEntity<String> deleteLogicPedido(@PathVariable Long id) {
        pedidoService.deleteLogicPedido(id);
        return ResponseEntity.ok("O pedido foi deletado logicamente");
    }

    @PostMapping("/{pedidoId}/produtos/")
    public ResponseEntity<PedidoEntity> adicionarProduto(
            @PathVariable Long pedidoId,
            @Valid @RequestBody ProdutoDTO produtoDTO
    ) {
        PedidoEntity pedidoAtualizado = pedidoService.adicionarProduto(pedidoId, produtoDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{pedidoId}/produtos/{produtoId}")
    public ResponseEntity<PedidoEntity> removerProduto(@PathVariable Long pedidoId, @PathVariable Long produtoId) {
        PedidoEntity pedidoAtualizado = pedidoService.removerProduto(pedidoId, produtoId);
        return ResponseEntity.ok(pedidoAtualizado);
    }
}
