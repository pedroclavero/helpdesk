package com.pedro.helpdesk.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro.helpdesk.model.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
