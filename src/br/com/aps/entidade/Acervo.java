package br.com.aps.entidade;



public class Acervo {
	
	private int id;
	private String titulo;
	private String autor;
	private Tema tema;
	private TipoAcervo tipoAcervo;
	
		
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
	
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	public TipoAcervo getTipoAcervo() {
		return tipoAcervo;
	}
	public void setTipoAcervo(TipoAcervo tipoAcervo) {
		this.tipoAcervo = tipoAcervo;
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
		
		if (tema == null) {
			if (other.tema != null)
				return false;
		} else if (!tema.equals(other.tema))
			return false;
		
		if (tipoAcervo == null) {
			if (other.tipoAcervo != null)
				return false;
		} else if (!tipoAcervo.equals(other.tipoAcervo))
			return false;
		return true;
	}

	
	
}
