package com.sistema.cdg.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import com.sistema.cdg.model.ClassPathConfigVO;
import com.sistema.cdg.model.ClasseConfigVO;
import com.sistema.cdg.model.ProjetoConfiguracaoVO;
import com.sistema.codegenerator.model.enums.LogicaTela;
import com.sistema.codegenerator.util.ClassLoaderExtention;

@ManagedBean
@SessionScoped
public class ProjetoConfMB {

	private ProjetoConfiguracaoVO model;

	public ProjetoConfiguracaoVO getModel() {
		if (model == null) {
			model = new ProjetoConfiguracaoVO();
		}
		return model;
	}

	public void setModel(ProjetoConfiguracaoVO model) {
		this.model = model;
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

	@SuppressWarnings("resource")
	public void carregarClassPath() {

		try {

			for (ClassPathConfigVO classpath : getModel().getListDependencias()) {

				ZipInputStream zip = new ZipInputStream(new FileInputStream(classpath.getArquivo()));
				for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
					if (!entry.isDirectory() && entry.getName().endsWith(".class")) {

						ClasseConfigVO classe = new ClasseConfigVO(entry.getName().replace('/', '.'));
						classpath.getListClassesListadas().add(classe);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void carregarClassloader() {

		List<File> lFiles = new ArrayList<>();

		try {

			for (ClassPathConfigVO classpath : getModel().getListDependencias()) {
				lFiles.add(classpath.getArquivo());
			}

			ClassLoaderExtention instance = ClassLoaderExtention.getInstance(lFiles);

			for (ClassPathConfigVO classepath : getModel().getListDependencias()) {

				classepath.getListClassesSelecionadas().clear();

				for (ClasseConfigVO classe : classepath.getListClassesListadas()) {
					if (classe.getDeveUtilizar()) {
						classepath.getListClassesSelecionadas().add(classe);
					}
				}

				for (ClasseConfigVO classe : classepath.getListClassesSelecionadas()) {
					classe.setClasse(instance.loadClass(classe.getNomeQualificado().replace(".class", "")));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			for (File file : lFiles) {
				file.delete();
			}
		}
	}

	public List<LogicaTela> getListLogicaTela() {
		return Arrays.asList(LogicaTela.values());
	}
}
