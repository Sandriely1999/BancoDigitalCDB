package org.cdb.bancodigitalcdb.model.entities;

import jakarta.persistence.*;
import org.cdb.bancodigitalcdb.model.enums.TipoSeguro;

import java.util.List;

@Entity
public class Seguro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSeguro tipo;

    @Column(nullable = false)
    private double valorMensal;

    @Column(nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "seguros")
    private List<CartaoCredito> cartoes;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TipoSeguro getTipo() { return tipo; }
    public void setTipo(TipoSeguro tipo) { this.tipo = tipo; }

    public double getValorMensal() { return valorMensal; }
    public void setValorMensal(double valorMensal) { this.valorMensal = valorMensal; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<CartaoCredito> getCartoes() { return cartoes; }
    public void setCartoes(List<CartaoCredito> cartoes) { this.cartoes = cartoes; }
}
