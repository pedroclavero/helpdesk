package com.pedro.helpdesk.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.helpdesk.model.DTOs.TecnicoDTO;
import com.pedro.helpdesk.model.entities.Tecnico;
import com.pedro.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

//	metodo implementado no curso
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}

// metodo implementado e modificado - Pedro Clavero
//	@GetMapping
//	public ResponseEntity<TecnicoDTO> findById(@RequestParam(name = "id") Integer id) {
//		Tecnico obj = tecnicoService.findById(id);
//		return ResponseEntity.ok().body(new TecnicoDTO(obj));
//	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {

		List<Tecnico> list = tecnicoService.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
}
