package org.cdb.bancodigitalcdb.repositories;


import org.cdb.bancodigitalcdb.model.entities.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
}