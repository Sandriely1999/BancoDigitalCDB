package org.cdb.bancodigitalcdb.repositories;

import org.cdb.bancodigitalcdb.model.entities.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<ContaBancaria, String> {
}
