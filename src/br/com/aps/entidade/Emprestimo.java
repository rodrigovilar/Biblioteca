package br.com.aps.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class Emprestimo implements Serializable {

	private Pessoa pessoa;
	private Acervo acervo;
	private String idSolicitacao;
	private Date dataEmprestimo;
	private Date dataDevolucao;
	private Date dataPrevistaDevolucao;
	
	public static final float VALOR_MULTA = (float) 0.50;
	
		
	public Acervo getAcervo() {
		return acervo;
	}

	public void setAcervo(Acervo acervo) {
		this.acervo = acervo;
	}

	public Date getDataPrevistaDevolucao() {
		return dataPrevistaDevolucao;
	}

	public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		if (pessoa == null) {
		if (other.pessoa != null)
			return false;
		}else if (!pessoa.equals(other.pessoa))
			return false;
		
		if (idSolicitacao == null) {
			if (other.idSolicitacao != null)
				return false;
			}else if (!idSolicitacao.equals(other.idSolicitacao))
				return false;
		
		if (dataEmprestimo == null) {
			if (other.dataEmprestimo != null)
				return false;
			}else if (!dataEmprestimo.equals(other.dataEmprestimo))
				return false;
		
		if (dataDevolucao == null) {
			if (other.dataDevolucao != null)
				return false;
			}else if (!dataDevolucao.equals(other.dataDevolucao))
				return false;
		
		if (dataPrevistaDevolucao == null) {
			if (other.dataPrevistaDevolucao != null)
				return false;
			}else if (!dataPrevistaDevolucao.equals(other.dataPrevistaDevolucao))
				return false;
		return true;
	}	
	
	
}
