package com.pedro.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.helpdesk.domain.Chamado;
import com.pedro.helpdesk.domain.Cliente;
import com.pedro.helpdesk.domain.Tecnico;
import com.pedro.helpdesk.domain.enums.Perfil;
import com.pedro.helpdesk.domain.enums.Prioridade;
import com.pedro.helpdesk.domain.enums.Status;
import com.pedro.helpdesk.repositories.ChamadoRepository;
import com.pedro.helpdesk.repositories.ClienteRepository;
import com.pedro.helpdesk.repositories.TecnicoRepository;



@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Pedro Clavero", "08410512912", "pedroclavero@mail.com", "123");
		tec1.addPerfis(Perfil.ADMIM);

		Cliente cli1 = new Cliente(null, "Cliente Burro", "55322208089", "clienteburro@mail.com", "321");
		cli1.addPerfis(Perfil.CLIENTE);

		Chamado c1 = new Chamado(tec1, cli1, null, Prioridade.MEDIA, Status.ABERTO, "Chamado 01", "Primeiro Chamado");

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
