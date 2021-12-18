package com.pedro.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.helpdesk.model.DTOs.ChamadoDTO;
import com.pedro.helpdesk.model.entities.Chamado;
import com.pedro.helpdesk.model.entities.Cliente;
import com.pedro.helpdesk.model.entities.Tecnico;
import com.pedro.helpdesk.model.entities.enums.Prioridade;
import com.pedro.helpdesk.model.entities.enums.Status;
import com.pedro.helpdesk.model.repositories.ChamadoRepository;
import com.pedro.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoServices {
	@Autowired
	private ChamadoRepository chamadoReposity;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findbyId(Integer id) {
		Optional<Chamado> obj = chamadoReposity.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado ! ID: " + id));
	}

	public List<Chamado> findAll() {
		return chamadoReposity.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		return chamadoReposity.save(newChamado(objDTO));
	}

	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());

		Chamado chamado = new Chamado();
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}
}
