package com.sistema.cdg.model;

import java.util.ArrayList;
import java.util.List;

public class ProjetoConfiguracaoVO {

	private String diretorioPrincipal;

	private String arquivoLabel;

	private List<ClassPathConfigVO> listDependencias;

	private ClassPathConfigVO selectedClasspath;

	public ClassPathConfigVO getSelectedClasspath() {
		if (getListDependencias() != null && getListDependencias().size() > 0) {
			selectedClasspath = getListDependencias().get(0);
		}
		return selectedClasspath;
	}

	public void setSelectedClasspath(ClassPathConfigVO selectedClasspath) {
		this.selectedClasspath = selectedClasspath;
	}

	public ProjetoConfiguracaoVO() {
		this.listDependencias = new ArrayList<>();
		this.selectedClasspath = new ClassPathConfigVO(null, null);
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
