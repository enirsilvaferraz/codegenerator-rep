package com.architecture.view;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.primefaces.context.RequestContext;

import com.archtecture.model.entities.ModelAb;
import com.archtecture.model.enums.TipoOrdenacao;
import com.archtecture.model.facedes.PersistenceFacadeLocal;

public abstract class PadraoMBImpl<Model extends ModelAb> implements PadraoMBIf<Model> {

	@EJB
	private PersistenceFacadeLocal persistenceFacade;

	private List<Model> listModel;

	private Model modelCad;

	private Model modelSel;

	protected void aposSalvar() throws Exception {

	}

	@Override
	public void executarExcluir(Model pModel) {

		try {
			getPersistenceFacade().excluir(pModel);
			UtilWeb.enviarMensagem(MensagemEnum.INFO_SUCESSO_EXCLUSAO);

			setModelCad(null);

		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}
	}

	@Override
	public void executarPesquisar() {
		try {
			setListModel(getPersistenceFacade().pesquisarLista(getModelSel(), getTipoOrdenacao(),
					getAtributosOrdenacao()));

			if (getListModel().isEmpty()) {
				UtilWeb.enviarMensagem(MensagemEnum.INFO_PESQUISA_VAZIA);
			}

		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}
	}

	@Override
	public void executarSalvar() {

		try {

			if (getModelCad().getCodigo() == null || getModelCad().getCodigo().equals(0)) {
				prepararSalvar();
				getPersistenceFacade().inserir(getModelCad());
				UtilWeb.enviarMensagem(MensagemEnum.INFO_SUCESSO_CADASTRO);
			} else {
				getPersistenceFacade().atualizar(getModelCad());
				UtilWeb.enviarMensagem(MensagemEnum.INFO_SUCESSO_EDICAO);
			}

			aposSalvar();

			setModelCad(null);

			RequestContext.getCurrentInstance().execute("PF('dialogCadastro').hide()");
		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}
	}

	@Override
	public void executarLimpar() {
		setModelSel(null);
		setListModel(null);
		UtilWeb.enviarMensagem(MensagemEnum.INFO_SUCESSO_LIMPEZA);
	}

	protected String[] getAtributosOrdenacao() {
		return getModelSel().getAtributosOrdencao();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Model getInstance() {
		try {
			ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class lClasse = (Class) parameterizedType.getActualTypeArguments()[0];
			return (Model) lClasse.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	@Override
	public List<Model> getListModel() {
		if (listModel == null) {
			listModel = new ArrayList<>();
		}
		return listModel;
	}

	@Override
	public Model getModelCad() {
		if (modelCad == null) {
			modelCad = getInstance();
		}
		return modelCad;
	}

	@Override
	public Model getModelSel() {
		if (modelSel == null) {
			modelSel = getInstance();
		}
		return modelSel;
	}

	public PersistenceFacadeLocal getPersistenceFacade() {
		return persistenceFacade;
	}

	protected TipoOrdenacao getTipoOrdenacao() {
		return TipoOrdenacao.ASC;
	}

	protected List<? extends ModelAb> montarCombo(ModelAb pModel, TipoOrdenacao pTipoOrdenacao,
			String... pAtributosOrdenacao) {

		List<ModelAb> listCombo = new ArrayList<ModelAb>();

		if (pTipoOrdenacao == null) {
			pTipoOrdenacao = TipoOrdenacao.ASC;
		}

		try {
			listCombo = getPersistenceFacade().pesquisarLista(pModel, pTipoOrdenacao, pAtributosOrdenacao);
		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}

		return listCombo;
	}

	@Override
	public void prepararDetalhes(Model pModel) {
		setModelCad(pModel);
		RequestContext.getCurrentInstance().execute("PF('dialogDetalhes').show()");
	}

	@Override
	public void prepararEditar(Model pModel) {
		setModelCad(pModel);
		RequestContext.getCurrentInstance().execute("PF('dialogCadastro').show()");
	}

	protected void prepararSalvar() throws Exception {

	}

	public void setListModel(List<Model> listModel) {
		this.listModel = listModel;
	}

	public void setModelCad(Model modelCad) {
		this.modelCad = modelCad;
	}

	public void setModelSel(Model modelSel) {
		this.modelSel = modelSel;
	}
}
