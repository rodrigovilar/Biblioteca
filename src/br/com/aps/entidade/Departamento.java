package br.com.aps.entidade;

import java.io.Serializable;


public class Departamento implements Serializable {
	
	private String setor;
	
	public Departamento(String setor){
		this.setSetor(setor);
	}
	
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
		Departamento other = (Departamento) obj;
		if (setor == null) {
		if (other.setor != null)
			return false;
		}else if (!setor.equals(other.setor))
			return false;
		return true;
	}
		
}
