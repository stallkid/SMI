package com.ds.smi.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ds.smi.dto.request.SetorFuncionarioRequest;
import com.ds.smi.dto.response.SetorFuncionarioResponse;
import com.ds.smi.model.SetorFuncionario;
import com.ds.smi.services.SetorFuncionarioService;

@RestController
@RequestMapping(value="/setorFuncionarios")
public class SetorFuncionarioController {
	
	@Autowired
	private SetorFuncionarioService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<SetorFuncionarioResponse> find(@PathVariable Integer id) {
		SetorFuncionario obj = service.find(id);
		SetorFuncionarioResponse response = new SetorFuncionarioResponse(obj);
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody SetorFuncionarioRequest objDto) {
		SetorFuncionario obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody SetorFuncionarioRequest objDto, @PathVariable Integer id) {
		SetorFuncionario obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SetorFuncionarioResponse>> findAll() {
		List<SetorFuncionario> list = service.findAll();
		List<SetorFuncionarioResponse> listDto = list.stream().map(obj -> new SetorFuncionarioResponse(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<SetorFuncionarioResponse>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<SetorFuncionario> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<SetorFuncionarioResponse> listDto = list.map(obj -> new SetorFuncionarioResponse(obj));  
		return ResponseEntity.ok().body(listDto);
	}

}
