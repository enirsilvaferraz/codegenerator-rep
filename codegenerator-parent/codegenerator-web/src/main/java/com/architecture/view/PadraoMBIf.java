package com.architecture.view;

import java.util.List;

import com.archtecture.model.entities.ModelAb;

public interface PadraoMBIf<Model extends ModelAb> {

	void executarExcluir(Model pModel);

	void executarPesquisar();

	void executarSalvar();

	List<Model> getListModel();

	Model getModelCad();

	Model getModelSel();

	void prepararDetalhes(Model pModel);

	void prepararEditar(Model pModel);

	void executarLimpar();

	// void executarPesquisar();
	//
	// void executarSalvar();
	//
	// void prepararEditar();
	//
	// void executarExcluir(Model pModel);
	//
	// void prepararDetalhes();
	//
	// List<Model> getListModel();
	//
	// Model getModelCad();
	//
	// void setModelCad(Model pModelCad);
}
