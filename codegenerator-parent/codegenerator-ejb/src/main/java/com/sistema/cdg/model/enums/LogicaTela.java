package com.sistema.cdg.model.enums;

public enum LogicaTela {

	CRUD_PADRAO(0, "crud"), TABULAR(1, "tab"), CONFIGURACAO(2, "conf");

	public static LogicaTela valueOf(Integer id) {
		return LogicaTela.values()[id];
	}

	private Integer codigo;

	private String extesaoTela;

	private LogicaTela(Integer codigo, String extesaoTela) {
		this.codigo = codigo;
		this.extesaoTela = extesaoTela;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getExtesaoTela() {
		return extesaoTela;
	}

}
