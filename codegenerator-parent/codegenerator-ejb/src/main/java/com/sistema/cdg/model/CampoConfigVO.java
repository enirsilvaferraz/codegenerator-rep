package com.sistema.cdg.model;

import java.lang.reflect.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampoConfigVO {

	private String nome;

	private String texto;

	private Boolean utilizaArg;

	private Boolean utilizaSel;

	private Boolean utilizaMan;

	private Template template;

	public CampoConfigVO(Field campo) {
		this.nome = campo.getName();
	}

}
