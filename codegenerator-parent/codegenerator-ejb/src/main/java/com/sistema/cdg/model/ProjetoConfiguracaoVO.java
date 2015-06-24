package com.sistema.cdg.model;

import java.util.ArrayList;
import java.util.List;

public class ProjetoConfiguracaoVO {

	private String					diretorioPrincipal;

	private String					arquivoLabel;

	private List<ClassPathConfigVO>	listDependencias;

	public ProjetoConfiguracaoVO() {
		this.listDependencias = new ArrayList<>();
	}

	public List<ClassPathConfigVO> getListDependencias() {
		return listDependencias;
	}

	public void setListDependencias(List<ClassPathConfigVO> listDependencias) {
		this.listDependencias = listDependencias;
	}

	public String getDiretorioPrincipal() {
		return diretorioPrincipal;
	}

	public void setDiretorioPrincipal(String diretorioPrincipal) {
		this.diretorioPrincipal = diretorioPrincipal;
	}

	public String getArquivoLabel() {
		return arquivoLabel;
	}

	public void setArquivoLabel(String arquivoLabel) {
		this.arquivoLabel = arquivoLabel;
	}
}
