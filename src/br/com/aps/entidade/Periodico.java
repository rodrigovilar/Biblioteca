package br.com.aps.entidade;

import java.io.Serializable;
import java.util.Date;

public class Periodico extends Acervo implements Serializable {

	private Date dataPublicacao;
	
	public Periodico(){
		
	}
	
	public Periodico(int id, String titulo, String autor, String area,
			TipoAcervo tipoAcervo, Situacao situacao, Date dataPublicacao){
		super(id, titulo, autor, area, tipoAcervo, situacao);
		this.dataPublicacao = dataPublicacao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Periodico other = (Periodico) obj;
		if (dataPublicacao == null) {
			if (other.dataPublicacao != null)
				return false;
		} else if (!dataPublicacao.equals(other.dataPublicacao))
			return false;

		return true;
	}
}
