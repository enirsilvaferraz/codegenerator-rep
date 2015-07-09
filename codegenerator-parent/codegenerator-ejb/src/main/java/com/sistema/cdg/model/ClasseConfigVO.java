package com.sistema.cdg.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.sistema.cdg.model.enums.LogicaTela;
import com.sistema.cdg.model.enums.TipoToken;

@Getter
@Setter
public class ClasseConfigVO {

	private String nomeQualificado;

	private Boolean deveUtilizar;

	private Map<TipoToken, List<CampoConfigVO>> mapCamposClasse;

	private Class<?> classe;

	private String pacoteManagedBean;

	private String diretorioPagias;

	private LogicaTela logicaTela;

	private String tituloTela;

	public ClasseConfigVO(String nomeQualificado) {
		this.nomeQualificado = nomeQualificado;
		this.deveUtilizar = false;
		this.mapCamposClasse = new HashMap<>();
		this.logicaTela = LogicaTela.CRUD_PADRAO;
	}

	public String getNomeSimplificado() {
		if (getNomeQualificado().equals("Default")) {
			return getNomeQualificado();
		}
		return getNomeQualificado().split("\\.")[getNomeQualificado().split(
				"\\.").length - 2];
	}
}
