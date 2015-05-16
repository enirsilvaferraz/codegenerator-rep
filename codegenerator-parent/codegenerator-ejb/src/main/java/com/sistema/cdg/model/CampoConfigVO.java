package com.sistema.cdg.model;

import java.lang.reflect.Field;

public class CampoConfigVO {

	private String nome;

	private Boolean utilizaArg;

	private Boolean utilizaSel;

	private Boolean utilizaMan;

	public CampoConfigVO(Field campo) {
		this.nome = campo.getName();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getUtilizaArg() {
		return utilizaArg;
	}

	public void setUtilizaArg(Boolean utilizaArg) {
		this.utilizaArg = utilizaArg;
	}

	public Boolean getUtilizaSel() {
		return utilizaSel;
	}

	public void setUtilizaSel(Boolean utilizaSel) {
		this.utilizaSel = utilizaSel;
	}

	public Boolean getUtilizaMan() {
		return utilizaMan;
	}

	public void setUtilizaMan(Boolean utilizaMan) {
		this.utilizaMan = utilizaMan;
	}

}
