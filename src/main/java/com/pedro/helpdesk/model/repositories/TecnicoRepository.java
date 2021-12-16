package com.pedro.helpdesk.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro.helpdesk.model.entities.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
