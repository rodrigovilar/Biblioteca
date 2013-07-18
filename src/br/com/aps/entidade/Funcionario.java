package br.com.aps.entidade;

import java.io.Serializable;


public class Funcionario extends Pessoa implements Serializable{

	public static final int QUANTIDADE_ACERVO_EMPRESTIMO = 5;
	private String setor;

	
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (setor == null) {
		if (other.setor != null)
			return false;
		}else if (!setor.equals(other.setor))
			return false;		
		return true;
	}
}
