package org.cdb.bancodigitalcdb.model.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends ContaBancaria {
    private double taxaManutencao;

    public void descontarTaxaMensal() {
        if (this.getSaldo() >= taxaManutencao) {
            this.setSaldo(this.getSaldo() - taxaManutencao);
        } else {
            throw new RuntimeException("Saldo insuficiente para taxa de manutenção!");
        }
    }

    public double getTaxaManutencao() {
        return taxaManutencao;
    }

    public void setTaxaManutencao(double taxaManutencao) {
        this.taxaManutencao = taxaManutencao;
    }
}
