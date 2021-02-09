package com.br.senior.challange.model.pedido;

import com.br.senior.challange.model.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class Pedido {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "desconto")
    private BigDecimal desconto;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Produto> produtos;

    @Column(name="situacao")
    private Boolean situacao;

    @Column(name="valorTotal")
    private BigDecimal valorTotal;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
