package com.sistema.cdg.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.sistema.cdg.model.enums.LogicaTela;

public class ClasseConfigVO {

	private String				nomeQualificado;

	private Boolean				deveUtilizar;

	private List<CampoConfigVO>	listCamposSelecionados;

	private List<CampoConfigVO>	listCamposClasse;

	private Class<?>			classe;

	private String				pacoteManagedBean;

	private String				diretorioPagias;

	private LogicaTela			logicaTela;

	public ClasseConfigVO(String nomeQualificado) {
		this.nomeQualificado = nomeQualificado;
		this.deveUtilizar = false;
		this.listCamposClasse = new ArrayList<>();
		this.listCamposSelecionados = new ArrayList<>();
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

	public void setClasse(Class<?> classe) {
		this.classe = classe;
		getListCamposClasse().clear();
		for (Field campo : classe.getDeclaredFields()) {
			getListCamposClasse().add(new CampoConfigVO(campo));
		}
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

	public List<CampoConfigVO> getListCamposSelecionados() {
		return listCamposSelecionados;
	}

	public void setListCamposSelecionados(List<CampoConfigVO> listCamposSelecionados) {
		this.listCamposSelecionados = listCamposSelecionados;
	}

	public List<CampoConfigVO> getListCamposClasse() {
		return listCamposClasse;
	}

	public void setListCamposClasse(List<CampoConfigVO> listCamposClasse) {
		this.listCamposClasse = listCamposClasse;
	}

	public LogicaTela getLogicaTela() {
		return logicaTela;
	}

	public void setLogicaTela(LogicaTela logicaTela) {
		this.logicaTela = logicaTela;
	}

}
