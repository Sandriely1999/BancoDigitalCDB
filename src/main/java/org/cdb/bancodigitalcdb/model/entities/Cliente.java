package org.cdb.bancodigitalcdb.model.entities;

import jakarta.persistence.*;
import org.cdb.bancodigitalcdb.model.enums.CategoriaCliente;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaCliente categoriaCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ContaBancaria> contas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cartao> cartoes;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public CategoriaCliente getCategoriaCliente() { return categoriaCliente; }
    public void setCategoriaCliente(CategoriaCliente categoriaCliente) { this.categoriaCliente = categoriaCliente; }

    public List<ContaBancaria> getContas() { return contas; }
    public void setContas(List<ContaBancaria> contas) { this.contas = contas; }

    public List<Cartao> getCartoes() { return cartoes; }
    public void setCartoes(List<Cartao> cartoes) { this.cartoes = cartoes; }
}
