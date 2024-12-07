package br.ufrn.imd.sistemamercado.services;

import br.ufrn.imd.sistemamercado.dto.PedidoDTO;
import br.ufrn.imd.sistemamercado.dto.ProdutoDTO;
import br.ufrn.imd.sistemamercado.exceptions.ResourceNotFoundException;
import br.ufrn.imd.sistemamercado.model.ClienteEntity;
import br.ufrn.imd.sistemamercado.model.PedidoEntity;
import br.ufrn.imd.sistemamercado.model.ProdutoEntity;
import br.ufrn.imd.sistemamercado.repositories.ClienteRepository;
import br.ufrn.imd.sistemamercado.repositories.PedidoRepository;
import br.ufrn.imd.sistemamercado.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public List<PedidoEntity> getAllPedidos() {
        return pedidoRepository.findByAtivoTrue();
    }


    public PedidoEntity getPedidoById(Long id) {
        return pedidoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + id + " não encontrado."));
    }


    public PedidoEntity createPedido(PedidoDTO pedidoDTO) {
        ClienteEntity cliente = clienteRepository.findByIdAndAtivoTrue(pedidoDTO.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        List<ProdutoEntity> produtos = new ArrayList<>();

        for (Long idProduto : pedidoDTO.produtosId()) {
            ProdutoEntity produto = produtoRepository.findByIdAndAtivoTrue(idProduto)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
            produtos.add(produto);
        }

        PedidoEntity pedido = new PedidoEntity(pedidoDTO, cliente, produtos);
        return pedidoRepository.save(pedido);
    }


    public PedidoEntity updatePedido(Long id, PedidoDTO pedidoDTO) {
        PedidoEntity pedidoExistente = pedidoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + id + " não encontrado."));

        ClienteEntity cliente = clienteRepository.findByIdAndAtivoTrue(pedidoDTO.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + pedidoDTO.clienteId() + " não encontrado."));

        List<ProdutoEntity> produtos = new ArrayList<>();

        for (Long idProduto : pedidoDTO.produtosId()) {
            ProdutoEntity produto = produtoRepository.findByIdAndAtivoTrue(idProduto)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
            produtos.add(produto);
        }

        pedidoExistente.atualizar(pedidoDTO, cliente, produtos);
        return pedidoRepository.save(pedidoExistente);
    }


    public void deletePedido(Long id) {
        PedidoEntity pedidoExistente = pedidoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + id + " não encontrado."));
        pedidoRepository.delete(pedidoExistente);
    }


    public void deleteLogicPedido(Long id) {
        PedidoEntity pedidoExistente = pedidoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + id + " não encontrado."));
        pedidoExistente.desativar();
        pedidoRepository.save(pedidoExistente);
    }


    public PedidoEntity adicionarProduto(Long pedidoId, ProdutoDTO produtoDTO) {
        PedidoEntity pedidoExistente = pedidoRepository.findByIdAndAtivoTrue(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + pedidoId + " não encontrado."));

        ProdutoEntity produto = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDTO, produto);
        produto.ativar();
        produto = produtoRepository.save(produto);

        pedidoExistente.salvarProduto(produto);
        return pedidoRepository.save(pedidoExistente);
    }


    public PedidoEntity removerProduto(Long pedidoId, Long produtoId) {
        PedidoEntity pedidoExistente = pedidoRepository.findByIdAndAtivoTrue(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + pedidoId + " não encontrado."));

        ProdutoEntity produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + produtoId + " não encontrado."));

        if (!pedidoExistente.removerProduto(produto)) {
            throw new ResourceNotFoundException("Produto com ID " + produtoId + " não está associado ao pedido com ID " + pedidoId + ".");
        }

        return pedidoRepository.save(pedidoExistente);
    }
}
