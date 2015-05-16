package com.sistema.cdg.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.architecture.view.PadraoMBImpl;
import com.sistema.cdg.model.Template;
import com.sistema.cdg.model.enums.TipoTemplate;

@ManagedBean
@ViewScoped
public class TemplateCrudMB extends PadraoMBImpl<Template> {

	@PostConstruct
	private void init() {
		getModelSel().setTipo(TipoTemplate.TELA);
		executarPesquisar();
	}

	public String prepararMestreDetalhes() {
		return "template-mstdet";
	}
}
