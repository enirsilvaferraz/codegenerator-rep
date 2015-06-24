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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import com.sistema.cdg.model.CampoConfigVO;
import com.sistema.cdg.model.ClassPathConfigVO;
import com.sistema.cdg.model.ClasseConfigVO;
import com.sistema.cdg.model.ProjetoConfiguracaoVO;
import com.sistema.cdg.model.enums.LogicaTela;
import com.sistema.cdg.model.enums.TipoTemplate;
import com.sistema.codegenerator.util.ClassLoaderExtention;

@ManagedBean
@SessionScoped
public class ProjetoConfMB {

	private ProjetoConfiguracaoVO	model;

	private ClasseConfigVO			selectedClass;

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

		// Lista todos os campos da classes escolhida
		List<CampoConfigVO> listaCampos = new ArrayList<>();
		for (Field field : selectedClass.getClasse().getDeclaredFields()) {
			listaCampos.add(new CampoConfigVO(field));
		}

		// Limpa o mapa
		selectedClass.getMapCamposClasse().clear();

		// Preenche o mapa com as novas informaçoes
		for (TipoTemplate tipo : selectedClass.getLogicaTela().getListaTipoTemplate()) {
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

	public List<TipoTemplate> getListTipoTemplate() {
		return new ArrayList<>(selectedClass.getMapCamposClasse().keySet());
	}

	public ProjetoConfiguracaoVO getModel() {
		if (model == null) {
			model = new ProjetoConfiguracaoVO();
		}
		return model;
	}

	public ClasseConfigVO getSelectedClass() {
		return selectedClass;
	}

	public void setModel(ProjetoConfiguracaoVO model) {
		this.model = model;
	}

	public void setSelectedClass(ClasseConfigVO selectedClass) {
		this.selectedClass = selectedClass;
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
