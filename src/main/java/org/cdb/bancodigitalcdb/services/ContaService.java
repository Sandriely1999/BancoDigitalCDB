package org.cdb.bancodigitalcdb.services;


import org.cdb.bancodigitalcdb.model.entities.Cliente;
import org.cdb.bancodigitalcdb.model.entities.ContaBancaria;
import org.cdb.bancodigitalcdb.model.entities.ContaCorrente;
import org.cdb.bancodigitalcdb.model.entities.ContaPoupanca;
import org.cdb.bancodigitalcdb.model.enums.CategoriaCliente;
import org.cdb.bancodigitalcdb.repositories.ClienteRepository;
import org.cdb.bancodigitalcdb.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ContaBancaria criarConta(String tipo, int clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        ContaBancaria conta;
        String numeroConta = UUID.randomUUID().toString().substring(0, 8);

        if (tipo.equalsIgnoreCase("CORRENTE")) {
            ContaCorrente cc = new ContaCorrente();
            cc.setNumeroConta(numeroConta);
            cc.setTaxaManutencao(calcularTaxaManutencao(cliente.getCategoriaCliente()));
            conta = cc;
        } else {
            ContaPoupanca cp = new ContaPoupanca();
            cp.setNumeroConta(numeroConta);
            cp.setTaxaRendimentoAnual(calcularTaxaRendimento(cliente.getCategoriaCliente()));
            conta = cp;
        }

        conta.setCliente(cliente);
        return contaRepository.save(conta);
    }

    private double calcularTaxaManutencao(CategoriaCliente categoria) {
        return switch (categoria) {
            case COMUM -> 12.0;
            case SUPER -> 8.0;
            case PREMIUM -> 0.0;
        };
    }

    private double calcularTaxaRendimento(CategoriaCliente categoria) {
        return switch (categoria) {
            case COMUM -> 0.005; // 0.5% ao ano
            case SUPER -> 0.007; // 0.7% ao ano
            case PREMIUM -> 0.009; // 0.9% ao ano
        };
    }

    public ContaBancaria buscarPorId(String id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
    }

    public void transferir(String origemId, String destinoId, double valor) {
        ContaBancaria origem = buscarPorId(origemId);
        ContaBancaria destino = buscarPorId(destinoId);

        if (origem.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente!");
        }

        origem.sacar(valor);
        destino.depositar(valor);
        contaRepository.save(origem);
        contaRepository.save(destino);
    }

    public double consultarSaldo(String id) {
        return buscarPorId(id).getSaldo();
    }

    public void depositar(String id, double valor) {
        ContaBancaria conta = buscarPorId(id);
        conta.depositar(valor);
        contaRepository.save(conta);
    }

    public void sacar(String id, double valor) {
        ContaBancaria conta = buscarPorId(id);
        if (!conta.sacar(valor)) {
            throw new RuntimeException("Saldo insuficiente!");
        }
        contaRepository.save(conta);
    }

    public void aplicarTaxaManutencao(String id) {
        ContaBancaria conta = buscarPorId(id);
        if (conta instanceof ContaCorrente cc) {
            cc.descontarTaxaMensal();
            contaRepository.save(cc);
        } else {
            throw new RuntimeException("Não é uma conta corrente!");
        }
    }

    public void aplicarRendimento(String id) {
        ContaBancaria conta = buscarPorId(id);
        if (conta instanceof ContaPoupanca cp) {
            cp.calcularRendimentoMensal();
            contaRepository.save(cp);
        } else {
            throw new RuntimeException("Não é uma conta poupança!");
        }
    }
}