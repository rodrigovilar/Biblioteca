package br.com.aps.entidade;

import java.io.Serializable;



public class Acervo implements Serializable{
	
	private int id;
	private String titulo;
	private String autor;
	private String area;
	private TipoAcervo tipoAcervo;
	private Situacao situacao;
	
	public Acervo(int id, String titulo, String autor, String area, TipoAcervo tipoAcervo, Situacao situacao){
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.area = area;
		this.tipoAcervo = tipoAcervo;
		this.situacao = situacao;
	}
	
	public Acervo() {
		
		this.id = 0;
		this.titulo = "";
		this.autor = "";
		this.area = null;
		this.tipoAcervo = null;
		this.situacao = null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public TipoAcervo getTipoAcervo() {
		return tipoAcervo;
	}
	public void setTipoAcervo(TipoAcervo tipoAcervo) {
		this.tipoAcervo = tipoAcervo;
	}
	
	
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		return true;
		
		if (obj == null)
		return false;
	
		if (getClass() != obj.getClass())
		return false;
	
		Acervo other = (Acervo) obj;
	
		if (id == 0) {
		if (other.id != 0)
			return false;
		} else if (!(id ==(other.id)))
			return false;
		
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		
		if (tipoAcervo == null) {
			if (other.tipoAcervo != null)
				return false;
		} else if (!tipoAcervo.equals(other.tipoAcervo))
			return false;
		
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}

	
	
}
