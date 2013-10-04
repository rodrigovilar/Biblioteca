package br.com.aps.entidade;

import java.io.Serializable;

public class Livro extends Acervo implements Serializable {

	private String editora;

	public Livro(int id, String titulo, String autor, String area,
			TipoAcervo tipoAcervo, Situacao situacao, String editora) {
		super(id, titulo, autor, area, tipoAcervo, situacao);
		this.editora = editora;
	}
	
	public Livro(){
		
	}
	
	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}


	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (editora == null) {
			if (other.editora != null)
				return false;
		} else if (!editora.equals(other.editora))
			return false;

		return true;
	}
}
