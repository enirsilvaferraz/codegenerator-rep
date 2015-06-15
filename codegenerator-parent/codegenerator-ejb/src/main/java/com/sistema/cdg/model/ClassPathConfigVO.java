package com.sistema.cdg.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassPathConfigVO {

	private File arquivo;

	private String nomeArquivo;

	private List<ClasseConfigVO> listClassesSelecionadas;

	private List<ClasseConfigVO> listClassesListadas;

	private ClasseConfigVO selectedClass;

	public ClassPathConfigVO(File inputstream, String nomeArquivo) {
		this.arquivo = inputstream;
		this.nomeArquivo = nomeArquivo;
		this.listClassesSelecionadas = new ArrayList<>();
		this.listClassesListadas = new ArrayList<>();
	}

	public ClasseConfigVO getSelectedClass() {
		for (ClasseConfigVO vo : getListClassesSelecionadas()) {
			if (vo.getDeveUtilizar()) {
				selectedClass = vo;
			}
		}
		return selectedClass;
	}

	public void setSelectedClass(ClasseConfigVO selectedClass) {
		this.selectedClass = selectedClass;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	public List<ClasseConfigVO> getListClassesSelecionadas() {
		return listClassesSelecionadas;
	}

	public void setListClassesSelecionadas(List<ClasseConfigVO> listClassesSelecionadas) {
		this.listClassesSelecionadas = listClassesSelecionadas;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public List<ClasseConfigVO> getListClassesListadas() {
		return listClassesListadas;
	}

	public void setListClassesListadas(List<ClasseConfigVO> listClassesListadas) {
		this.listClassesListadas = listClassesListadas;
	}

}
