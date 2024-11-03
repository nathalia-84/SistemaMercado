package br.ufrn.imd.sistemamercado.controllers;


import br.ufrn.imd.sistemamercado.dto.ProdutoDTO;
import br.ufrn.imd.sistemamercado.model.ProdutoEntity;
import br.ufrn.imd.sistemamercado.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<ProdutoEntity> produtos = produtoRepository.findByAtivoTrue();
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getbyId(@PathVariable Long id){
        Optional<ProdutoEntity> produto = produtoRepository.findByIdAndAtivoTrue(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }

    @PostMapping("/")
    public ResponseEntity<ProdutoEntity> postProduct(@RequestBody ProdutoDTO produtoDTO){
        ProdutoEntity produto = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDTO, produto);
        produto.ativar();
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoEntity> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        ProdutoEntity produto = produtoOptional.get();
        produto.carregarDTO(produtoDTO);
        produtoRepository.save(produto);

        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Long id) {
        Optional<ProdutoEntity> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        produtoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
    }

    @DeleteMapping("/dl/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable Long id) {
        Optional<ProdutoEntity> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        ProdutoEntity produto = produtoOptional.get();
        produto.desativar();
        produtoRepository.save(produto);

        return ResponseEntity.status(HttpStatus.OK).body("Produto desativado logicamente");
    }





}
