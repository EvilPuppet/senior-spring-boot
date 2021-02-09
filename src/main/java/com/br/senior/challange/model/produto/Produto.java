package com.br.senior.challange.model.produto;

import com.br.senior.challange.model.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "nome")
    private String nome;

    @Column(name = "servico")
    private Boolean servico;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "desativado")
    private Boolean desativado;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "i_pedidos")
    private Pedido pedido;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getServico() {
        return servico;
    }

    public void setServico(Boolean servico) {
        this.servico = servico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getDesativado() {
        return desativado;
    }

    public void setDesativado(Boolean desativado) {
        this.desativado = desativado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
