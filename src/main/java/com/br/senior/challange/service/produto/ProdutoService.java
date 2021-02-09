package com.br.senior.challange.service.produto;
import com.br.senior.challange.model.produto.Produto;
import com.br.senior.challange.repository.pedido.PedidoRepository;
import com.br.senior.challange.repository.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService  {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Produto> findAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Produto> pagedResult = produtoRepository.findAll(paging);
        return pagedResult.toList();
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Boolean delete(Produto produto) {
        try {
            if (!pedidoRepository.existsById(produto.getPedido().getId())) {
                produtoRepository.delete(produto);
                return true;
            }
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return false;
    }

    public Optional<Produto> findById(UUID id) {
        return produtoRepository.findById(id);
    }
}
