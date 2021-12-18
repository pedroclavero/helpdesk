package com.pedro.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pedro.helpdesk.model.DTOs.ClienteDTO;
import com.pedro.helpdesk.model.entities.Cliente;
import com.pedro.helpdesk.model.entities.Pessoa;
import com.pedro.helpdesk.model.repositories.ClienteRepository;
import com.pedro.helpdesk.model.repositories.PessoaRepository;
import com.pedro.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.pedro.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado!! Id: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfeEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return clienteRepository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfeEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return clienteRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!!");
		}
		clienteRepository.deleteById(id);
	}

	private void validaPorCpfeEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!!");
		}
	}

}
