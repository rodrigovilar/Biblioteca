package br.com.aps.entidade;

import java.io.Serializable;
import java.util.LinkedList;

public class Persistencia implements Serializable {

	private LinkedList<Aluno> listaAluno;
	private LinkedList<Funcionario> listaFuncionario;
	private LinkedList<Professor> listaProfessor;
	private LinkedList<Usuario> listaUsuario;
	private LinkedList<Emprestimo> listaEmprestimo;
	private LinkedList<Curso> listaCurso;
	private LinkedList<Livro> listaLivro;
	private LinkedList<Periodico> listaPeriodico;
	

	public Persistencia() {
		this.listaAluno = new LinkedList<Aluno>();
		this.listaFuncionario = new LinkedList<Funcionario>();
		this.listaProfessor = new LinkedList<Professor>();
		this.listaUsuario = new LinkedList<Usuario>();
		this.listaEmprestimo = new LinkedList<Emprestimo>();
		this.listaCurso = new LinkedList<Curso>();
		this.listaLivro = new LinkedList<Livro>();
		this.listaPeriodico = new LinkedList<Periodico>();
	}

	public LinkedList<Aluno> getListaAluno() {
		return listaAluno;
	}

	public LinkedList<Funcionario> getListaFuncionarios() {
		return listaFuncionario;
	}
	public LinkedList<Professor> getListaProfessor(){
		return listaProfessor;
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
	
	public LinkedList<Periodico> getListaPeriodico() {
		return listaPeriodico;
	}
	
	public LinkedList<Usuario> getListaUsuario(){
		return listaUsuario;
	}
}
