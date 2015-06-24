package com.sistema.cdg.model.enums;


public enum LogicaTela {

	CRUD_PADRAO(0, "crud", TipoTemplate.ARGUMENTO, TipoTemplate.CADASTRO, TipoTemplate.DATATABLE, TipoTemplate.DETALHE),

	TABULAR(1, "tab", TipoTemplate.CADASTRO, TipoTemplate.DATATABLE, TipoTemplate.DETALHE),

	CONFIGURACAO(2, "conf", TipoTemplate.CADASTRO);

	public static LogicaTela valueOf(Integer id) {
		return LogicaTela.values()[id];
	}

	private Integer			codigo;

	private String			extesaoTela;

	private TipoTemplate[]	listaTipoTemplate;

	private LogicaTela(Integer codigo, String extesaoTela, TipoTemplate... listaTipoTemplate) {
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

	public TipoTemplate[] getListaTipoTemplate() {
		return listaTipoTemplate;
	}
}
