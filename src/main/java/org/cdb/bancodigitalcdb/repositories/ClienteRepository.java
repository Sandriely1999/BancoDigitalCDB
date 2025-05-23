package org.cdb.bancodigitalcdb.repositories;

import org.cdb.bancodigitalcdb.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByCpf(String cpf);

}