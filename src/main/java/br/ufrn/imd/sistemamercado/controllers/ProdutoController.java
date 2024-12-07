package br.ufrn.imd.sistemamercado.controllers;

import br.ufrn.imd.sistemamercado.dto.ProdutoDTO;
import br.ufrn.imd.sistemamercado.model.ProdutoEntity;
import br.ufrn.imd.sistemamercado.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    public ResponseEntity<List<ProdutoEntity>> getAll() {
        List<ProdutoEntity> produtos = produtoService.getAllAtivos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getById(@PathVariable Long id) {
        ProdutoEntity produto = produtoService.getByIdAtivo(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/")
    public ResponseEntity<ProdutoEntity> postProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoEntity produto = produtoService.createProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> updateProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoEntity produtoAtualizado = produtoService.updateProduto(id, produtoDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.ok("Produto deletado com sucesso.");
    }

    @DeleteMapping("/dl/{id}")
    public ResponseEntity<String> deleteLogic(@PathVariable Long id) {
        produtoService.deleteLogicProduto(id);
        return ResponseEntity.ok("Produto desativado logicamente.");
    }
}

