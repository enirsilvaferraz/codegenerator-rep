package com.architecture.view;

import java.lang.reflect.ParameterizedType;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.archtecture.model.entities.ModelAb;

public abstract class PadraoMestreDetalheMBImpl<ModelDetalhe extends ModelAb, ModelMestre extends ModelAb> extends
		PadraoMBImpl<ModelDetalhe> {

	private ModelMestre modelMestre;

	public ModelMestre getModelMestre() throws Exception {
		if (modelMestre == null) {
			Long id = Long.valueOf(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest()).getParameter("ID_MODEL_MESTRE"));
			modelMestre = getPersistenceFacade().carregar(getInstanceMestre(id));
		}
		return modelMestre;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected ModelMestre getInstanceMestre(Long id) {
		try {
			ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class lClasse = (Class) parameterizedType.getActualTypeArguments()[1];
			ModelMestre newInstance = (ModelMestre) lClasse.newInstance();
			newInstance.setCodigo(id);
			return newInstance;
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	@Override
	public void executarSalvar() {

		try {

			prepararSalvar();
			getPersistenceFacade().atualizar(getModelMestre());
			UtilWeb.enviarMensagem(MensagemEnum.INFO_SUCESSO_CADASTRO);

			aposSalvar();
			setModelCad(null);

			executarPesquisar();

			RequestContext.getCurrentInstance().execute("PF('dialogCadastro').hide()");
		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}
	}

	@Override
	public void executarExcluir(ModelDetalhe pModel) {
		super.executarExcluir(pModel);
		executarPesquisar();
	}

	public abstract String executarVoltar();
}
