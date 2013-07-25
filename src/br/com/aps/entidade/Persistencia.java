package br.com.aps.entidade;

import java.io.Serializable;
import java.util.LinkedList;

public class Persistencia implements Serializable {

	private LinkedList<Aluno> listaAluno;
	private LinkedList<Funcionario> listaFuncionario;
	private LinkedList<Emprestimo> listaEmprestimo;
	private LinkedList<Curso> listaCurso;
	private LinkedList<Livro> listaLivro;

	public Persistencia() {
		this.listaAluno = new LinkedList<Aluno>();
		this.listaFuncionario = new LinkedList<Funcionario>();
		this.listaEmprestimo = new LinkedList<Emprestimo>();
		this.listaCurso = new LinkedList<Curso>();
		this.listaLivro = new LinkedList<Livro>();
	}

	public LinkedList<Aluno> getListaAluno() {
		return listaAluno;
	}

	public LinkedList<Funcionario> getListaFuncionarios() {
		return listaFuncionario;
	}

	public LinkedList<Emprestimo> getListaEmprestimos() {
		return listaEmprestimo;
	}

	public LinkedList<Curso> getListaCurso() {
		return listaCurso;
	}

	public LinkedList<Livro> getListaLivro() {
		return listaLivro;
	}
}
