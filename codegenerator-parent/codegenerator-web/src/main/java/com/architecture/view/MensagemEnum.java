package com.architecture.view;

import javax.faces.application.FacesMessage;

public enum MensagemEnum {

	ERRO_AMBIENTE(FacesMessage.SEVERITY_FATAL, "sistema.erro.ambiente"),
	INFO_PESQUISA_VAZIA(FacesMessage.SEVERITY_WARN, "sistema.info.pesquisa_vazia"),
	INFO_SUCESSO_CADASTRO(FacesMessage.SEVERITY_INFO, "sistema.info.sucesso_cadastro"),
	INFO_SUCESSO_EDICAO(FacesMessage.SEVERITY_INFO, "sistema.info.sucesso_edicao"),
	INFO_SUCESSO_LIMPEZA(FacesMessage.SEVERITY_INFO, "sistema.info.limpeza_concluida"),

	INFO_SUCESSO_EXCLUSAO(FacesMessage.SEVERITY_INFO, "sistema.info.sucesso_exclusao"),

	INFO_CAIXA_SUCESSO_ABERTURA(FacesMessage.SEVERITY_INFO, "caixa.info.sucesso_abertura"),
	INFO_CAIXA_SUCESSO_FECHAMENTO(FacesMessage.SEVERITY_INFO, "caixa.info.sucesso_fechamento"),

	ERRO_VENDA_PRODUTO_NAO_ENCONTRADO(FacesMessage.SEVERITY_ERROR, "venda.erro.produto_nao_encontrado"),

	ERRO_CONFIRMACAO_SENHA(FacesMessage.SEVERITY_ERROR, "erro.confirmacao_senha"),
	ERRO_LOGIN_INVALIDO(FacesMessage.SEVERITY_ERROR, "erro.login_invalido"),
	INFO_SUCESSO_CANCELAMENTO_VENDA(FacesMessage.SEVERITY_INFO, "venda.info.sucesso_cancelamento"),
	INFO_PRODUTO_NAO_ENCONTRADO(FacesMessage.SEVERITY_WARN, "venda.info.produto_nao_encontrado"),
	WARN_VENDA_CAIXA_NAO_CADASTRADO(FacesMessage.SEVERITY_WARN, "venda.warn.venda_caixa_nao_cadastrado"),
	
	ERRO_FECHAR_CAIXA_VENDA_ABERTA(FacesMessage.SEVERITY_ERROR, "erro.erro_fechar_caixa_venda_aberta"),
	
	INSERIR_NULL_NO_CAMPO(FacesMessage.SEVERITY_ERROR, "mysql.erro.insert_null_no_campo"),
	ENTIDADE_DUPLICADA(FacesMessage.SEVERITY_ERROR, "mysql.erro.entidade_duplicada"),
	INSERIR_NULL_NA_ENTIDADE(FacesMessage.SEVERITY_ERROR, "mysql.erro.inserir_null_na_entidade"),
	DELETE_ENTIDADE_JA_UTILIZADA(FacesMessage.SEVERITY_ERROR, "mysql.erro.delete_entidade_ja_utilizada");
	
	private FacesMessage.Severity severity;

	private String sigla;

	private MensagemEnum(FacesMessage.Severity severity, String sigla) {
		this.severity = severity;
		this.sigla = sigla;
	}

	public FacesMessage.Severity getSeverity() {
		return severity;
	}

	public String getSigla() {
		return sigla;
	}
}
