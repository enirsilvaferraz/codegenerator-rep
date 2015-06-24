package com.sistema.cdg.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassPathConfigVO {

	private File					arquivo;

	private String					nomeArquivo;

	private List<ClasseConfigVO>	listClassesListadas;

	public ClassPathConfigVO(File inputstream, String nomeArquivo) {
		this.arquivo = inputstream;
		this.nomeArquivo = nomeArquivo;
		this.listClassesListadas = new ArrayList<>();
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
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
