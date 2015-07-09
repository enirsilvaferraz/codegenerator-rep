package com.sistema.cdg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.archtecture.model.entities.ModelAb;
import com.sistema.cdg.model.enums.TipoCampo;
import com.sistema.cdg.model.enums.TipoToken;

@Getter
@Setter
@Entity
@Table(name = "TEMPLATE_CAMPO", schema = "code_generator")
public class TemplateCampo extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8182049638016402598L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_TEMPLATE_CAMPO", length = 11, nullable = false, unique = true)
	private Long codigo;

	@Enumerated
	@Column(name = "TIPO_TOKEN", length = 2, nullable = false, unique = false)
	private TipoToken tipoToken;

	@Enumerated
	@Column(name = "TIPO_CAMPO", length = 2, nullable = false, unique = false)
	private TipoCampo tipoCampo;

	@Column(name = "CODIGO_FONTE", length = 500, nullable = false, unique = false)
	private String codigoFonte;

	@Override
	public String[] getAtributosOrdencao() {
		return null;
	}
}
