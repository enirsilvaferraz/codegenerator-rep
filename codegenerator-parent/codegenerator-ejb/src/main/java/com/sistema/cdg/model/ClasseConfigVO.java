package com.sistema.cdg.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sistema.cdg.model.enums.LogicaTela;
import com.sistema.cdg.model.enums.TipoTemplate;

public class ClasseConfigVO {

	private String									nomeQualificado;

	private Boolean									deveUtilizar;

	private Map<TipoTemplate, List<CampoConfigVO>>	mapCamposClasse;

	private Class<?>								classe;

	private String									pacoteManagedBean;

	private String									diretorioPagias;

	private LogicaTela								logicaTela;

	private String									tituloTela;

	public ClasseConfigVO(String nomeQualificado) {
		this.nomeQualificado = nomeQualificado;
		this.deveUtilizar = false;
		this.mapCamposClasse = new HashMap<>();
		this.logicaTela = LogicaTela.CRUD_PADRAO;
	}

	public String getNomeQualificado() {
		return nomeQualificado;
	}

	public void setNomeQualificado(String nomeQualificado) {
		this.nomeQualificado = nomeQualificado;
	}

	public Boolean getDeveUtilizar() {
		return deveUtilizar;
	}

	public void setDeveUtilizar(Boolean deveUtilizar) {
		this.deveUtilizar = deveUtilizar;
	}

	public String getTituloTela() {
		return tituloTela;
	}

	public void setTituloTela(String tituloTela) {
		this.tituloTela = tituloTela;
	}

	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}

	public Class<?> getClasse() {
		return classe;
	}

	public String getNomeSimplificado() {
		if (getNomeQualificado().equals("Default")) {
			return getNomeQualificado();
		}
		return getNomeQualificado().split("\\.")[getNomeQualificado().split("\\.").length - 2];
	}

	public String getPacoteManagedBean() {
		return pacoteManagedBean;
	}

	public void setPacoteManagedBean(String pacoteManagedBean) {
		this.pacoteManagedBean = pacoteManagedBean;
	}

	public String getDiretorioPagias() {
		return diretorioPagias;
	}

	public void setDiretorioPagias(String diretorioPagias) {
		this.diretorioPagias = diretorioPagias;
	}

	public LogicaTela getLogicaTela() {
		return logicaTela;
	}

	public void setLogicaTela(LogicaTela logicaTela) {
		this.logicaTela = logicaTela;
	}

	public Map<TipoTemplate, List<CampoConfigVO>> getMapCamposClasse() {
		return mapCamposClasse;
	}

	public void setMapCamposClasse(Map<TipoTemplate, List<CampoConfigVO>> mapCamposClasse) {
		this.mapCamposClasse = mapCamposClasse;
	}

}
