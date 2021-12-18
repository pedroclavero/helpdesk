package com.pedro.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.helpdesk.model.entities.Chamado;
import com.pedro.helpdesk.model.repositories.ChamadoRepository;
import com.pedro.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoServices {
	@Autowired
	private ChamadoRepository chamadoReposity;
	
	public Chamado findbyId(Integer id) {
		Optional<Chamado> obj = chamadoReposity.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado ! ID: " + id));
	}

	public List<Chamado> findAll() {
		return chamadoReposity.findAll();
	}
}
