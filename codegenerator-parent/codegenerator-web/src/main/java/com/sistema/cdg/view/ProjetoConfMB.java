package com.sistema.cdg.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import com.architecture.view.UtilWeb;
import com.archtecture.model.facedes.PersistenceFacadeLocal;
import com.sistema.cdg.model.CampoConfigVO;
import com.sistema.cdg.model.ClassPathConfigVO;
import com.sistema.cdg.model.ClasseConfigVO;
import com.sistema.cdg.model.ProjetoConfiguracaoVO;
import com.sistema.cdg.model.enums.LogicaTela;
import com.sistema.cdg.model.enums.TipoToken;
import com.sistema.codegenerator.util.ClassLoaderExtention;

@ManagedBean
@SessionScoped
public class ProjetoConfMB {

	@EJB
	private PersistenceFacadeLocal persistenceFacade;

	@Getter
	@Setter
	private ProjetoConfiguracaoVO model;

	@Getter
	@Setter
	private ClasseConfigVO selectedClass;

	// @Getter
	// private List<TemplateCampo> listTemplateCampo;

	@PostConstruct
	public void init() {

		try {

			model = new ProjetoConfiguracaoVO();
			// listTemplateCampo = persistenceFacade.pesquisarLista(new TemplateCampo(), null);

		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}
	}

	public void carregarClasseSelecionada(ClasseConfigVO item) {

		// Recupera o item do grid
		selectedClass = item;

		if (!item.getDeveUtilizar()) {
			return;
		}

		// Lista fisica de arquivos
		List<File> lFiles = new ArrayList<>();

		try {

			// Itera as dependencias inseridas no upload
			for (ClassPathConfigVO classpath : getModel().getListDependencias()) {

				// Adiciona os arquivos fisicos para exclusao
				lFiles.add(classpath.getArquivo());
			}

			// Incorpora as classes ao classloader
			ClassLoaderExtention instance = ClassLoaderExtention.getInstance(lFiles);

			// Instancia a classe
			selectedClass.setClasse(instance.loadClass(selectedClass.getNomeQualificado().replace(".class", "")));

			// Atualiza os campos
			selecionarLogicaTela();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {

			// Remove os arquivos
			for (File file : lFiles) {
				file.delete();
			}
		}
	}

	public void selecionarLogicaTela() {

		// Limpa o mapa
		selectedClass.getMapCamposClasse().clear();

		// Preenche o mapa com as novas informa�oes
		for (TipoToken tipo : selectedClass.getLogicaTela().getListaTipoTemplate()) {

			// Lista todos os campos da classes escolhida. A cria��o da lista deve ser interna para evitar referencia
			List<CampoConfigVO> listaCampos = new ArrayList<>();
			for (Field field : selectedClass.getClasse().getDeclaredFields()) {
				listaCampos.add(new CampoConfigVO(field));
			}

			selectedClass.getMapCamposClasse().put(tipo, listaCampos);
		}
	}

	@SuppressWarnings("resource")
	public void carregarClassPath() {

		try {

			for (ClassPathConfigVO classpath : getModel().getListDependencias()) {

				ZipInputStream zip = new ZipInputStream(new FileInputStream(classpath.getArquivo()));
				for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
					if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
						classpath.getListClassesListadas().add(new ClasseConfigVO(entry.getName().replace('/', '.')));
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<LogicaTela> getListLogicaTela() {
		return Arrays.asList(LogicaTela.values());
	}

	public List<TipoToken> getListTipoTemplate() {
		return new ArrayList<>(selectedClass.getMapCamposClasse().keySet());
	}

	public void uploadDependencias(FileUploadEvent event) {

		try {

			final File tempFile = File.createTempFile(event.getFile().getFileName().replace(".jar", ""), ".jar");
			tempFile.deleteOnExit();
			try (FileOutputStream out = new FileOutputStream(tempFile)) {
				IOUtils.copy(event.getFile().getInputstream(), out);
				out.close();
			}

			getModel().getListDependencias().add(new ClassPathConfigVO(tempFile, event.getFile().getFileName()));

			carregarClassPath();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
