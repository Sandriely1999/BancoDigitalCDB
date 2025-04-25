package org.cdb.bancodigitalcdb.model.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEBITO")
public class CartaoDebito extends Cartao {
    private double limiteDiario = 1000.0;
    private double valorUtilizadoHoje = 0.0;

    @Override
    public boolean realizarPagamento(double valor) {
        if (valorUtilizadoHoje + valor <= limiteDiario &&
                getContaVinculada().getSaldo() >= valor) {
            valorUtilizadoHoje += valor;
            getContaVinculada().sacar(valor);
            return true;
        }
        return false;
    }

    public void resetarUtilizacaoDiaria() {
        this.valorUtilizadoHoje = 0.0;
    }

    public double getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(double limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public double getValorUtilizadoHoje() {
        return valorUtilizadoHoje;
    }

    public void setValorUtilizadoHoje(double valorUtilizadoHoje) {
        this.valorUtilizadoHoje = valorUtilizadoHoje;
    }
}
