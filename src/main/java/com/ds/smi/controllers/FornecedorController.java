package com.ds.smi.controllers;

import java.net.URI;
import java.util.List;

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

import com.ds.smi.dto.FornecedorDTO;
import com.ds.smi.model.Fornecedor;
import com.ds.smi.services.FornecedorService;

@RestController
@RequestMapping(value="/Fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Fornecedor> find(@PathVariable Integer id) {
		Fornecedor obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FornecedorDTO objDTO) {
		Fornecedor obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Fornecedor obj, @PathVariable Integer id) {
		//Fornecedor obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<Fornecedor>> findAll() {
		List<Fornecedor> list = service.findAll();
		//List<Fornecedor> listDto = list.stream().map(obj -> new Fornecedor(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Fornecedor>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Fornecedor> list = service.findPage(page, linesPerPage, orderBy, direction);
		//Page<Fornecedor> listDto = list.map(obj -> new Fornecedor(obj));  
		return ResponseEntity.ok().body(list);
	}

}
