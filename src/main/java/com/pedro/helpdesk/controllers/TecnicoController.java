package com.pedro.helpdesk.controllers;

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
	@GetMapping(name = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){ 
		Tecnico obj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}

}
