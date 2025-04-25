package org.cdb.bancodigitalcdb.model.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("CREDITO")
public class CartaoCredito extends Cartao {
    private double limiteCredito;
    private double saldoUtilizado = 0.0;

    @ManyToMany
    @JoinTable(
            name = "cartao_seguro",
            joinColumns = @JoinColumn(name = "cartao_id"),
            inverseJoinColumns = @JoinColumn(name = "seguro_id")
    )
    private List<Seguro> seguros;

    @Override
    public boolean realizarPagamento(double valor) {
        if (saldoUtilizado + valor <= limiteCredito) {
            saldoUtilizado += valor;
            if (saldoUtilizado > limiteCredito * 0.8) {
                saldoUtilizado += saldoUtilizado * 0.05;
            }
            return true;
        }
        return false;
    }


    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public double getSaldoUtilizado() {
        return saldoUtilizado;
    }

    public void setSaldoUtilizado(double saldoUtilizado) {
        this.saldoUtilizado = saldoUtilizado;
    }

    public List<Seguro> getSeguros() {
        return seguros;
    }

    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }
}
