package org.cdb.bancodigitalcdb.controllers;


import org.cdb.bancodigitalcdb.model.entities.ContaBancaria;
import org.cdb.bancodigitalcdb.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ContaBancaria criarConta(@RequestParam String tipo, @RequestParam int clienteId) {
        return contaService.criarConta(tipo, clienteId);
    }

    @GetMapping("/{id}")
    public ContaBancaria obterConta(@PathVariable String id) {
        return contaService.buscarPorId(id);
    }

    @PostMapping("/{id}/transferencia")
    public void transferir(@PathVariable String id, @RequestParam String destino, @RequestParam double valor) {
        contaService.transferir(id, destino, valor);
    }

    @GetMapping("/{id}/saldo")
    public double consultarSaldo(@PathVariable String id) {
        return contaService.consultarSaldo(id);
    }

    @PostMapping("/{id}/deposito")
    public void depositar(@PathVariable String id, @RequestParam double valor) {
        contaService.depositar(id, valor);
    }

    @PostMapping("/{id}/saque")
    public void sacar(@PathVariable String id, @RequestParam double valor) {
        contaService.sacar(id, valor);
    }

    @PutMapping("/{id}/manutencao")
    public void aplicarTaxaManutencao(@PathVariable String id) {
        contaService.aplicarTaxaManutencao(id);
    }

    @PutMapping("/{id}/rendimento")
    public void aplicarRendimento(@PathVariable String id) {
        contaService.aplicarRendimento(id);
    }
}