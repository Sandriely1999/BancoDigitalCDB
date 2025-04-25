package org.cdb.bancodigitalcdb.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Apolice {
    @Id
    private String numeroApolice;

    @Column(nullable = false)
    private LocalDate dataContratacao = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private CartaoCredito cartaoAssegurado;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private String condicoesAcionamento;

    public String getNumeroApolice() { return numeroApolice; }
    public void setNumeroApolice(String numeroApolice) { this.numeroApolice = numeroApolice; }
    public LocalDate getDataContratacao() { return dataContratacao; }
    public void setDataContratacao(LocalDate dataContratacao) { this.dataContratacao = dataContratacao; }
    public CartaoCredito getCartaoAssegurado() { return cartaoAssegurado; }
    public void setCartaoAssegurado(CartaoCredito cartaoAssegurado) { this.cartaoAssegurado = cartaoAssegurado; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getCondicoesAcionamento() { return condicoesAcionamento; }
    public void setCondicoesAcionamento(String condicoesAcionamento) { this.condicoesAcionamento = condicoesAcionamento; }
}