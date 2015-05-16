package com.sistema.cdg.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.archtecture.model.entities.ModelAb;
import com.sistema.cdg.model.enums.TipoTemplate;

@Entity
@Table(name = "TEMPLATE", schema = "code_generator")
public class Template extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4688223940663318600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO", length = 11, nullable = false, unique = true)
	private Long codigo;

	@Column(name = "NOME", length = 50, nullable = false, unique = true)
	private String nome;

	@Column(name = "CODIGO_FONTE", length = 4000, nullable = false, unique = false)
	private String codigoFonte;

	@Enumerated
	@Column(name = "TIPO", length = 1, nullable = false, unique = false)
	private TipoTemplate tipo;

	@ManyToOne
	@JoinColumn(name = "CODIGO_TEMPLATE_TELA")
	private Template teplateTela;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "teplateTela", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Template> listTemplate;

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoFonte() {
		return codigoFonte;
	}

	public void setCodigoFonte(String codigoFonte) {
		this.codigoFonte = codigoFonte;
	}

	@Override
	public String[] getAtributosOrdencao() {
		return null;
	}

	@Override
	public String getLabel() {
		return getNome();
	}

	public TipoTemplate getTipo() {
		return tipo;
	}

	public void setTipo(TipoTemplate tipo) {
		this.tipo = tipo;
	}

	public Template getTeplateTela() {
		return teplateTela;
	}

	public void setTeplateTela(Template teplateTela) {
		this.teplateTela = teplateTela;
	}

	public List<Template> getListTemplate() {
		return listTemplate;
	}

	public void setListTemplate(List<Template> listTemplate) {
		this.listTemplate = listTemplate;
	}

}
