package com.br.senior.challange.service.pedido;

import com.br.senior.challange.model.pedido.Pedido;
import com.br.senior.challange.model.produto.Produto;
import com.br.senior.challange.repository.pedido.PedidoRepository;
import com.br.senior.challange.repository.produto.ProdutoRepository;
import com.br.senior.challange.service.produto.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
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
    public void saveComDesconto() {
        Pedido pedido = new Pedido();
        Produto produto = new Produto();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(createProduto(UUID.fromString("b05301a0-7cfb-488d-8996-03f96b25e1f3"),"sapato", BigDecimal.valueOf(100), false, false));
        produtos.add(createProduto(UUID.fromString("b05301a0-7cfb-488d-8996-03f96b25e1f3"),"sapato", BigDecimal.valueOf(150), false, false));
        produtos.add(createProduto(UUID.fromString("5569cb05-1b6f-41e3-9f8b-9f0974286f24"),"Faxina", BigDecimal.valueOf(250), true, false));
        pedido.setProdutos(produtos);
        pedido.setSituacao(false);
        when(pedidoService.save(pedido)).thenAnswer(invocationOnMock -> pedido);
        assertNotNull(pedido);
        assertEquals(pedido.getValorTotal(), BigDecimal.valueOf(785));
        assertEquals(pedido.getDesconto(), BigDecimal.valueOf(15));
        assertEquals(pedido.getSituacao(), false);
        assertEquals(pedido.getProdutos().size(), 3);
    }

    @Test
    public void saveSemDesconto() {
        Pedido pedido = new Pedido();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(createProduto(UUID.fromString("5569cb05-1b6f-41e3-9f8b-9f0974286f24"),"Faxina", BigDecimal.valueOf(250), true, false));
        pedido.setProdutos(produtos);
        pedido.setSituacao(false);
        when(pedidoService.save(pedido)).thenAnswer(invocationOnMock -> pedido);
        assertNotNull(pedido);
        assertEquals(pedido.getValorTotal(), BigDecimal.valueOf(250));
        assertEquals(pedido.getDesconto(), null);
        assertEquals(pedido.getSituacao(), false);
        assertEquals(pedido.getProdutos().size(), 1);
    }

    @Test
    public void saveRemoveProduto() {
        Pedido pedido = new Pedido();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(createProduto(UUID.fromString("5569cb05-1b6f-41e3-9f8b-9f0974286f24"),"Faxina", BigDecimal.valueOf(250), true, true));
        produtos.add(createProduto(UUID.fromString("5569cb05-1b6f-41e3-9f8b-9f0974286f24"),"Faxina", BigDecimal.valueOf(250), true, false));
        pedido.setProdutos(produtos);
        pedido.setSituacao(false);
        when(pedidoService.save(pedido)).thenAnswer(invocationOnMock -> pedido);
        assertNotNull(pedido);
        assertEquals(pedido.getValorTotal(), BigDecimal.valueOf(250));
        assertEquals(pedido.getDesconto(), null);
        assertEquals(pedido.getSituacao(), false);
        assertEquals(pedido.getProdutos().size(), 1);
    }

    @Test
    public void deveRetornarTrueAoDeletar() {
        when(pedidoService.delete(UUID.fromString("5569cb05-1b6f-41e3-9f8b-9f0974286f24"))).thenAnswer(invocationOnMock -> Boolean.TRUE);
    }

    @Test
    public void deveFindById() {
        Pedido pedido = new Pedido();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(createProduto(UUID.fromString("b05301a0-7cfb-488d-8996-03f96b25e1f3"),"sapato", BigDecimal.valueOf(100), false, false));
        produtos.add(createProduto(UUID.fromString("b05301a0-7cfb-488d-8996-03f96b25e1f3"),"sapato", BigDecimal.valueOf(150), false, false));
        produtos.add(createProduto(UUID.fromString("5569cb05-1b6f-41e3-9f8b-9f0974286f24"),"Faxina", BigDecimal.valueOf(250), true, false));
        pedido.setProdutos(produtos);
        pedido.setSituacao(false);
        pedido.setValorTotal(BigDecimal.valueOf(785));
        pedido.setDesconto(BigDecimal.valueOf(15));
        pedido.setId(UUID.fromString("5569cb05-1b6f-41e3-9f8b-9f0974286213"));
        when(pedidoService.findById(pedido.getId())).thenReturn(java.util.Optional.of(pedido));
        assertNotNull(pedido);
    }

}