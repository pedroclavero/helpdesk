package com.pedro.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.helpdesk.model.DTOs.TecnicoDTO;
import com.pedro.helpdesk.model.entities.Pessoa;
import com.pedro.helpdesk.model.entities.Tecnico;
import com.pedro.helpdesk.model.repositories.PessoaRepository;
import com.pedro.helpdesk.model.repositories.TecnicoRepository;
import com.pedro.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.pedro.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectnotFoundException("Objeto não encontrado!! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfeEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}

	private void validaPorCpfeEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj =  pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!!");
		}
	}
}
