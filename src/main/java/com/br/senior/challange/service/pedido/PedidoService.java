package com.br.senior.challange.service.pedido;

import com.br.senior.challange.model.pedido.Pedido;
import com.br.senior.challange.model.produto.Produto;
import com.br.senior.challange.repository.pedido.PedidoRepository;
import com.br.senior.challange.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    public List<Pedido> findAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Pedido> pagedResult = pedidoRepository.findAll(paging);
        return pagedResult.toList();
    }

    public Pedido save(Pedido pedido) {
        existeProdutoDesativado(pedido);
        calcularValortTotal(pedido);
        aplicaDesconto(pedido);

        Pedido save = pedidoRepository.save(pedido);
        setPedidoNoProduto(pedido);
        return save;
    }

    private void setPedidoNoProduto(Pedido pedido) {
        for (Produto produto : pedido.getProdutos()) {
           produto.setPedido(pedido);
            produtoService.save(produto);
        }
    }

    public Boolean delete(UUID id) {
        try {
            pedidoRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
    }

    public Optional<Pedido> findById(UUID id) {
        return pedidoRepository.findById(id);
    }

    private void aplicaDesconto(Pedido pedido) {
        List<BigDecimal> valores = new ArrayList<>();
        int quantidadeDeProduto = contaQuantidadeDeProduto(pedido);
        if (!pedido.getSituacao()) {
            for (Produto produto : pedido.getProdutos()) {
                if (!produto.getServico() && quantidadeDeProduto >= 2) {
                    pedido.setDesconto(BigDecimal.valueOf(15));
                    BigDecimal desconto = (pedido.getDesconto().multiply(BigDecimal.valueOf(100))).divide(produto.getValor());
                    BigDecimal valorComDesconto = (produto.getValor().subtract(desconto));
                    valores.add(valorComDesconto);
                }
                if (pedido.getValorTotal() == null) { pedido.setValorTotal(BigDecimal.ZERO); }
                pedido.setValorTotal(pedido.getValorTotal().add(valores.stream().reduce(BigDecimal.ZERO, BigDecimal::add)));
            }
        }
    }

    private void calcularValortTotal(Pedido pedido) {
        List<BigDecimal> valores = new ArrayList<>();
        for (Produto produto : pedido.getProdutos()) {
            if (produto.getServico()) {
                valores.add(produto.getValor());
            }
            pedido.setValorTotal(valores.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        }
    }

    private void existeProdutoDesativado(Pedido pedido) {
        int index = 0;
        for (Produto produto : pedido.getProdutos()) {
            if (produto.getDesativado()) {
                pedido.getProdutos().remove(index);
                index++;
            }
        }
    }

    private int contaQuantidadeDeProduto(Pedido pedido) {
        int quantidadeDeProduto = 0;
        for (Produto produto : pedido.getProdutos()) {
            if (!produto.getServico()) {
                quantidadeDeProduto++;
            }
        }
        return quantidadeDeProduto;

    }
}

