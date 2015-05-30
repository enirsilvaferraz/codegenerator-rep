package com.sistema.cdg.view;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.architecture.view.PadraoMestreDetalheMBImpl;
import com.sistema.cdg.model.Template;

@ManagedBean
@ViewScoped
public class TokenMstDetMB extends PadraoMestreDetalheMBImpl<Template, Template> {

	@PostConstruct
	public void init() throws Exception {
		getModelSel().setTeplateTela(getModelCad());
		executarPesquisar();
	}

	@Override
	protected void prepararSalvar() throws Exception {
		super.prepararSalvar();

		if (getModelCad().getListTemplate() == null) {
			getModelCad().setListTemplate(new ArrayList<Template>());
		}
		getModelCad().setTeplateTela(getModelDetalhe());
		getModelCad().getListTemplate().add(getModelCad());
	}

	@Override
	public String executarVoltar() {
		return "/pages/template-crud";
	}
}
