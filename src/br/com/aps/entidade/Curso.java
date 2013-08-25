package br.com.aps.entidade;

import java.io.Serializable;

public class Curso implements Serializable{

	private String nome;
	private String codigo;

	public Curso(String nome, String codigo){
		this.setNome(nome);
		this.setCodigo(codigo);
	}
	
	public Curso(){
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (nome == null) {
		if (other.nome != null)
			return false;
		}else if (!nome.equals(other.nome))
			return false;
		
		if (codigo == null) {
		if (other.codigo != null)
			return false;
		}else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
		
}
