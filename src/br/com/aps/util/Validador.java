package br.com.aps.util;

import br.com.aps.controle.GerentePersistencia;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Situacao;
import br.com.aps.entidade.TipoAcervo;
import br.com.aps.entidade.TipoPessoa;
import br.com.aps.excecao.Excecao;

public class Validador {

	public static boolean validadorCPF(String cpf) {
		if (cpf.length() == 14) {
			return true;
		} else {
			throw new Excecao("CPF inválido");
		}
	}

	public static void validadorEmprestimo(Emprestimo emprestimo) {
		if ((emprestimo.getPessoa() == null)
				|| (emprestimo.getAcervo() == null)) {
			throw new Excecao("Empréstimo não pode ser null");
		}

		emprestimo.getAcervo().getSituacao();
		if (emprestimo.getAcervo().getSituacao() != Situacao.DISPONIVEL) {
			throw new Excecao("Acervo emprestado");
		}

		if ((emprestimo.getPessoa().getTipoPessoa() == TipoPessoa.ALUNO)
				&& (emprestimo.getAcervo().getTipoAcervo() == TipoAcervo.PERIODICO)) {
			throw new Excecao("Aluno não pode pegar periodico");
		}

		for (Emprestimo ep : GerentePersistencia.getInstance()
				.getListaEmprestimos()) {

			if (ep.getPessoa().getTipoPessoa() == TipoPessoa.ALUNO) {
				if (ep.getPessoa().getListaEmprestimo().size() > Aluno.QUANTIDADE_EMPRESTIMO) {
					throw new Excecao(
							"Aluno não pode pegar mais de dois livros.");
				}
				if (ep.getAcervo().getTitulo()
						.equals(ep.getAcervo().getTitulo())) {
					throw new Excecao(
							"Aluno não pode pegar dois livros iguais.");
				}
			}

			if (ep.getPessoa().getTipoPessoa() == TipoPessoa.FUNCIONARIO) {
				if (ep.getPessoa().getListaEmprestimo().size() > Funcionario.QUANTIDADE_ACERVO_EMPRESTIMO) {
					throw new Excecao(
							"Funcionário não pode pegar mais de cinco livros.");
				}
				if (ep.getAcervo().getTitulo()
						.equals(ep.getAcervo().getTitulo())) {
					throw new Excecao(
							"Aluno não pode pegar dois livros iguais.");
				}
			}

		}
	}
}
