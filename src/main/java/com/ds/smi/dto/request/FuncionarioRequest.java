package com.ds.smi.dto.request;

import java.io.Serializable;

public class FuncionarioRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String sobreNome;
	private Integer usuarioId;
	private Integer setorId;
	
	public FuncionarioRequest() {
		super();
	}
	public FuncionarioRequest(String nome, String sobreNome, Integer usuarioId, Integer setorId) {
		super();
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.usuarioId = usuarioId;
		this.setorId = setorId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Integer getSetorId() {
		return setorId;
	}
	public void setSetorId(Integer setorId) {
		this.setorId = setorId;
	}

}
