package org.cdb.bancodigitalcdb.services;


import org.cdb.bancodigitalcdb.model.entities.Cliente;
import org.cdb.bancodigitalcdb.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        // Valida CPF único
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        // Valida idade (>= 18 anos)
        if (Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears() < 18) {
            throw new RuntimeException("Cliente deve ser maior de idade!");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(int id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public Cliente atualizarCliente(int id, Cliente cliente) {
        Cliente clienteExistente = buscarPorId(id);
        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEndereco(cliente.getEndereco());
        clienteExistente.setCategoriaCliente(cliente.getCategoriaCliente());
        return clienteRepository.save(clienteExistente);
    }

    public void deletarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}