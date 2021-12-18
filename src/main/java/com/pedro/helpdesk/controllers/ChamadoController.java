package com.pedro.helpdesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.helpdesk.model.DTOs.ChamadoDTO;
import com.pedro.helpdesk.model.entities.Chamado;
import com.pedro.helpdesk.services.ChamadoServices;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoServices chamadoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		Chamado obj = chamadoService.findbyId(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}

}
