package br.ufrn.imd.sistemamercado.services;

import br.ufrn.imd.sistemamercado.dto.ProdutoDTO;
import br.ufrn.imd.sistemamercado.exceptions.ResourceNotFoundException;
import br.ufrn.imd.sistemamercado.model.PedidoEntity;
import br.ufrn.imd.sistemamercado.model.ProdutoEntity;
import br.ufrn.imd.sistemamercado.repositories.PedidoRepository;
import br.ufrn.imd.sistemamercado.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;


    public List<ProdutoEntity> getAllAtivos() {
        return produtoRepository.findByAtivoTrue();
    }

    public ProdutoEntity getByIdAtivo(Long id) {
        return produtoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado."));
    }

    public ProdutoEntity createProduto(ProdutoDTO produtoDTO) {
        ProdutoEntity produto = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDTO, produto);
        produto.ativar();
        return produtoRepository.save(produto);
    }

    public ProdutoEntity updateProduto(Long id, ProdutoDTO produtoDTO) {
        ProdutoEntity produtoExistente = produtoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado."));

        produtoExistente.atualizar(produtoDTO);
        return produtoRepository.save(produtoExistente);
    }

    public void deleteProduto(Long id) {
        ProdutoEntity produtoExistente = produtoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado."));

        for (PedidoEntity pedido : produtoExistente.getPedidos()) {
            pedido.getProdutos().remove(produtoExistente);
            pedidoRepository.save(pedido);
        }

        produtoRepository.delete(produtoExistente);
    }

    public void deleteLogicProduto(Long id) {
        ProdutoEntity produtoExistente = produtoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado."));
        produtoExistente.desativar();
        produtoRepository.save(produtoExistente);
    }
}


