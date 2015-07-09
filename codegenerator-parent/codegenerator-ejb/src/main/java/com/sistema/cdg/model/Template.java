package com.sistema.cdg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.archtecture.model.entities.ModelAb;

@Getter
@Setter
@Entity
@Table(name = "TEMPLATE", schema = "code_generator")
public class Template extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4688223940663318600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO", length = 11, nullable = false, unique = true)
	private Long codigo;

	@Column(name = "NOME", length = 50, nullable = false, unique = true)
	private String nome;

	@Column(name = "CODIGO_FONTE", length = 4000, nullable = false, unique = false)
	private String codigoFonte;

	@Override
	public String getLabel() {
		return getNome();
	}

	@Override
	public String[] getAtributosOrdencao() {
		return null;
	}

	@Override
	public String toString() {
		return getNome();
	}
}
