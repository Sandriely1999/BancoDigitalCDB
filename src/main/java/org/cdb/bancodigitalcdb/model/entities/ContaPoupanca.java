package org.cdb.bancodigitalcdb.model.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends ContaBancaria {
    private double taxaRendimentoAnual;

    public void calcularRendimentoMensal() {
        double rendimento = this.getSaldo() * (taxaRendimentoAnual / 12);
        this.setSaldo(this.getSaldo() + rendimento);
    }

    public double getTaxaRendimentoAnual() {
        return taxaRendimentoAnual;
    }

    public void setTaxaRendimentoAnual(double taxaRendimentoAnual) {
        this.taxaRendimentoAnual = taxaRendimentoAnual;
    }
}
