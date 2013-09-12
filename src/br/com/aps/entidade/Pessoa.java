package br.com.aps.entidade;

import java.io.Serializable;
import java.util.List;

public class Pessoa implements Serializable{

	private String nome;
	private String matricula;
	private String telefone;
	private String cpf;
	private TipoPessoa tipoPessoa;
	private List<Emprestimo> listaEmprestimo;
	
	
	public List<Emprestimo> getListaEmprestimo() {
		return listaEmprestimo;
	}

	public void setListaEmprestimo(List<Emprestimo> listaEmprestimo) {
		this.listaEmprestimo = listaEmprestimo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		return true;
		
		if (obj == null)
		return false;
	
		if (getClass() != obj.getClass())
		return false;
	
		Pessoa other = (Pessoa) obj;
	
		if (nome == null) {
		if (other.nome != null)
			return false;
		} else if (!nome.equals(other.nome))
			return false;
		
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		
		if (tipoPessoa == null) {
			if (other.tipoPessoa != null)
				return false;
		} else if (!tipoPessoa.equals(other.tipoPessoa))
			return false;
		return true;
	}
	
}


