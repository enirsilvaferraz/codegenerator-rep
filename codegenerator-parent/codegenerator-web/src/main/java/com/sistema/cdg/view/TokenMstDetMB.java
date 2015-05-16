package com.sistema.cdg.view;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.architecture.view.PadraoMestreDetalheMBImpl;
import com.sistema.cdg.model.Template;
import com.sistema.cdg.model.enums.TipoTemplate;

@ManagedBean
@ViewScoped
public class TokenMstDetMB extends PadraoMestreDetalheMBImpl<Template, Template> {

	@PostConstruct
	public void init() throws Exception {
		getModelSel().setTeplateTela(getModelMestre());
		executarPesquisar();
	}

	@Override
	protected void prepararSalvar() throws Exception {
		super.prepararSalvar();
		if (getModelMestre().getListTemplate()==null){
			getModelMestre().setListTemplate(new ArrayList<Template>());
		}
		getModelCad().setTipo(TipoTemplate.TOKEN);
		getModelCad().setTeplateTela(getModelMestre());
		getModelMestre().getListTemplate().add(getModelCad());
	}

	@Override
	public String executarVoltar() {
		return "/pages/template-crud";
	}
}
