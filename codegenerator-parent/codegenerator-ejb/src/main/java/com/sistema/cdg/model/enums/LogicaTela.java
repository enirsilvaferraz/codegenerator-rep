package com.sistema.cdg.model.enums;


public enum LogicaTela {

	CRUD_PADRAO(0, "crud", TipoToken.ARGUMENTO, TipoToken.CADASTRO, TipoToken.DATATABLE, TipoToken.DETALHE),

	TABULAR(1, "tab", TipoToken.CADASTRO, TipoToken.DATATABLE, TipoToken.DETALHE),

	CONFIGURACAO(2, "conf", TipoToken.CADASTRO);

	public static LogicaTela valueOf(Integer id) {
		return LogicaTela.values()[id];
	}

	private Integer			codigo;

	private String			extesaoTela;

	private TipoToken[]	listaTipoTemplate;

	private LogicaTela(Integer codigo, String extesaoTela, TipoToken... listaTipoTemplate) {
		this.codigo = codigo;
		this.extesaoTela = extesaoTela;
		this.listaTipoTemplate = listaTipoTemplate;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getExtesaoTela() {
		return extesaoTela;
	}

	public TipoToken[] getListaTipoTemplate() {
		return listaTipoTemplate;
	}
}
