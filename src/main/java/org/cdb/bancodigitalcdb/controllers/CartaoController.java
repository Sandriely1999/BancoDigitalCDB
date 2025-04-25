package org.cdb.bancodigitalcdb.controllers;

import org.cdb.bancodigitalcdb.model.entities.Cartao;
import org.cdb.bancodigitalcdb.services.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public Cartao emitirCartao(@RequestParam String tipo, @RequestParam int clienteId, @RequestParam String contaId) {
        return cartaoService.emitirCartao(tipo, clienteId, contaId);
    }

    @GetMapping("/{id}")
    public Cartao obterCartao(@PathVariable String id) {
        return cartaoService.buscarPorId(id);
    }

    @PostMapping("/{id}/pagamento")
    public void realizarPagamento(@PathVariable String id, @RequestParam double valor) {
        cartaoService.realizarPagamento(id, valor);
    }

    @PutMapping("/{id}/status")
    public void alterarStatus(@PathVariable String id, @RequestParam boolean ativo) {
        cartaoService.alterarStatus(id, ativo);
    }

    @PutMapping("/{id}/limite")
    public void alterarLimite(@PathVariable String id, @RequestParam double novoLimite) {
        cartaoService.alterarLimite(id, novoLimite);
    }

    @PutMapping("/{id}/limite-diario")
    public void alterarLimiteDiario(@PathVariable String id, @RequestParam double novoLimite) {
        cartaoService.alterarLimiteDiario(id, novoLimite);
    }
}