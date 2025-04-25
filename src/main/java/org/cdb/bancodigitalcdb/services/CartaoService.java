package org.cdb.bancodigitalcdb.services;

import org.cdb.bancodigitalcdb.model.entities.*;
import org.cdb.bancodigitalcdb.model.enums.CategoriaCliente;
import org.cdb.bancodigitalcdb.repositories.CartaoRepository;
import org.cdb.bancodigitalcdb.repositories.ClienteRepository;
import org.cdb.bancodigitalcdb.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    public Cartao emitirCartao(String tipo, int clienteId, String contaId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        ContaBancaria conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));

        Cartao cartao;
        String numeroCartao = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

        if (tipo.equalsIgnoreCase("CREDITO")) {
            CartaoCredito cc = new CartaoCredito();
            cc.setNumeroCartao(UUID.randomUUID().toString().replace("-", "").substring(0, 16));
            cc.setLimiteCredito(calcularLimiteCredito(cliente.getCategoriaCliente()));
            cartao = cc;
        } else {
            CartaoDebito cd = new CartaoDebito();
            cd.setNumeroCartao(numeroCartao);
            cd.setLimiteDiario(1000.0);
            cartao = cd;
        }

        cartao.setCliente(cliente);
        cartao.setContaVinculada(conta);
        return cartaoRepository.save(cartao);
    }

    private double calcularLimiteCredito(CategoriaCliente categoria) {
        return switch (categoria) {
            case COMUM -> 1000.0;
            case SUPER -> 5000.0;
            case PREMIUM -> 10000.0;
        };
    }

    public Cartao buscarPorId(String id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado!"));
    }

    public void realizarPagamento(String id, double valor) {
        Cartao cartao = buscarPorId(id);
        if (!cartao.realizarPagamento(valor)) {
            throw new RuntimeException("Pagamento não autorizado (limite excedido ou saldo insuficiente)!");
        }
        cartaoRepository.save(cartao);
    }

    public void alterarStatus(String id, boolean ativo) {
        Cartao cartao = buscarPorId(id);
        cartao.setStatus(ativo);
        cartaoRepository.save(cartao);
    }

    public void alterarLimite(String id, double novoLimite) {
        Cartao cartao = buscarPorId(id);
        if (cartao instanceof CartaoCredito cc) {
            cc.setLimiteCredito(novoLimite);
            cartaoRepository.save(cc);
        } else {
            throw new RuntimeException("Apenas cartões de crédito têm limite ajustável!");
        }
    }

    public void alterarLimiteDiario(String id, double novoLimite) {
        Cartao cartao = buscarPorId(id);
        if (cartao instanceof CartaoDebito cd) {
            cd.setLimiteDiario(novoLimite);
            cartaoRepository.save(cd);
        } else {
            throw new RuntimeException("Apenas cartões de débito têm limite diário!");
        }
    }
}