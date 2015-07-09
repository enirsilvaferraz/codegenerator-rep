package com.sistema.cdg.view;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.architecture.view.PadraoMBImpl;
import com.sistema.cdg.model.TemplateCampo;
import com.sistema.cdg.model.enums.TipoCampo;
import com.sistema.cdg.model.enums.TipoToken;

@ViewScoped
@ManagedBean
public class TemplateCampoCrudMB extends PadraoMBImpl<TemplateCampo> {

	public List<TipoToken> getListTipoToken() {
		return Arrays.asList(TipoToken.values());
	}

	public List<TipoCampo> getListTipoCampo() {
		return Arrays.asList(TipoCampo.values());
	}
}