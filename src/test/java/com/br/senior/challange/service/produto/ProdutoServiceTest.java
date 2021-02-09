package com.br.senior.challange.service.produto;

import com.br.senior.challange.model.pedido.Pedido;
import com.br.senior.challange.model.produto.Produto;
import com.br.senior.challange.repository.pedido.PedidoRepository;
import com.br.senior.challange.repository.produto.ProdutoRepository;
import com.br.senior.challange.service.pedido.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    public Produto createProduto(UUID uuid,String nome, BigDecimal valor, Boolean servico, Boolean desativado) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setId(uuid);
        produto.setServico(servico);
        produto.setValor(valor);
        produto.setDesativado(desativado);
        return produto;
    }

    @Test
    public void findById() {
        Produto produto = createProduto(UUID.fromString("b05301a0-7cfb-488d-8996-03f96b25e1f3"),"sapato", BigDecimal.valueOf(100), false, false);
        when(produtoService.findById(produto.getId())).thenReturn(java.util.Optional.of(produto));
        assertNotNull(produto);
        assertEquals(produtoService.findById(produto.getId()).get(), produto);
    }

    @Test
    public void delete() {
        Produto produto = createProduto(UUID.fromString("b05301a0-7cfb-488d-8996-03f96b25e1f3"),"sapato", BigDecimal.valueOf(100), false, false);
        produto.setPedido(mock(Pedido.class));
        when(produtoService.delete(produto)).thenAnswer(invocationOnMock -> Boolean.TRUE);
    }

    @Test
    public void save() {
        Produto produto = createProduto(UUID.fromString("b05301a0-7cfb-488d-1996-03f96b25e1f3"),"Tenis", BigDecimal.valueOf(100), false, false);
        when(produtoService.save(produto)).thenReturn(produto);
        assertEquals(produtoService.save(produto), produto);
        assertNotNull(produto);
    }


}