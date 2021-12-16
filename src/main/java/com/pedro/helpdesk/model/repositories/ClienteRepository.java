package com.pedro.helpdesk.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro.helpdesk.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
