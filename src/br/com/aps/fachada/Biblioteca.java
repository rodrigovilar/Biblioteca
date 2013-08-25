package br.com.aps.fachada;

import java.util.List;

import br.com.aps.controle.GerenteAluno;
import br.com.aps.controle.GerenteCurso;
import br.com.aps.controle.GerenteEmprestimo;
import br.com.aps.controle.GerenteFuncionario;
import br.com.aps.controle.GerenteLivro;
import br.com.aps.controle.GerentePeriodico;
import br.com.aps.controle.GerenteProfessor;
import br.com.aps.controle.GerenteUsuario;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Curso;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Livro;
import br.com.aps.entidade.Periodico;
import br.com.aps.entidade.Professor;
import br.com.aps.entidade.Usuario;

public class Biblioteca {

	GerenteUsuario gu = new GerenteUsuario();
	GerenteAluno ga = new GerenteAluno();
	GerenteFuncionario gf = new GerenteFuncionario();
	GerenteProfessor gp = new GerenteProfessor();
	GerentePeriodico gperiod = new GerentePeriodico();
	GerenteLivro gl = new GerenteLivro();
	GerenteCurso gc = new GerenteCurso();
	GerenteEmprestimo ge = new GerenteEmprestimo();

	public void login(String cpf, String senha) {
		gu.login(cpf, senha);
	}

	public void addUsuario(Usuario usuario) {
		gu.addUsuario(usuario);
	}

	public void deleteAdministrador(Usuario usuario) {
		gu.deleteUsuario(usuario);
	}

	public Usuario retornarUsuario(String cpfUsuario) {
		return gu.retornarUsuario(cpfUsuario);
	}

	public List<Usuario> getListUsuario() {
		return gu.getListUsuario();
	}

	// Dados para o aluno
	public void addAluno(Aluno aluno) {
		ga.addAluno(aluno);
	}

	public Aluno deleteAluno(String cpf) {
		return ga.deleteAluno(cpf);
	}

	public Aluno retornarAluno(String cpfAluno) {
		return ga.consultarAluno(cpfAluno);
	}

	public Aluno alterarDadosAluno(Aluno aluno) {
		return ga.alterarDadosAluno(aluno);
	}

	public List<Aluno> getListAluno() {
		return ga.getListAluno();
	}

	// Dados para o funcionario

	public void addFuncionario(Funcionario funcionario) {
		gf.addFuncionario(funcionario);
	}

	public Funcionario deleteFuncionario(String cpf) {
		return gf.deleteFuncionario(cpf);
	}

	public Funcionario retornarFuncionario(String cpfFuncionario) {
		return gf.consultarFuncionario(cpfFuncionario);
	}

	public Funcionario alterarDadosFuncionario(Funcionario funcionario) {
		return gf.alterarDadosFuncionario(funcionario);
	}

	public List<Funcionario> getListFuncionario() {
		return gf.getListFuncionario();
	}

	// Dados do professor
	public void addProfessor(Professor professor) {
		gp.addProfessor(professor);
	}

	public void deleteProfessor(String cpf) {
		gp.deleteProfessor(cpf);
	}

	public Professor retornarProfessor(String cpfProfessor) {
		return gp.consultarProfessor(cpfProfessor);
	}

	public Professor alterarDadosProfessor(Professor professor) {
		return gp.alterarDadosProfessor(professor);
	}

	public List<Professor> getListProfessor() {
		return gp.getListProfessor();
	}

	// Dados de acervo
	public void addLivro(Livro livro) {
		gl.addLivro(livro);
	}

	public void deleteLivro(int id) {
		gl.deleteLivro(id);
	}

	public Livro retornarLivro(int id) {
		return gl.consultarLivro(id);
	}

	public List<Livro> getListLivro() {
		return gl.getListLivro();
	}

	public void addPeriodico(Periodico periodico) {
		gperiod.addPeriodico(periodico);
	}

	public Periodico deletePeriodico(int id) {
		return gperiod.deletePeriodico(id);
	}
	
	public Periodico retornarPeriodico(int id) {
		return gperiod.consultarPeriodico(id);
	}

	public List<Periodico> getListPeriodico() {
		return gperiod.getListPeriodico();
	}

	// dados de emprestimo
	public void realizaEmprestimo(Emprestimo emprestimo) {
		ge.realizaEmprestimo(emprestimo);
	}

	public void devolverEmprestimo(Emprestimo p) {
		ge.devolverEmprestimo(p);
	}

	public List<Emprestimo> getListEmprestimo() {
		return ge.getListEmprestimo();
	}

	// Dados para Curso

	public void addCursos(Curso curso) {
		gc.addCursos(curso);
	}

	public Curso retornarCurso(String cod) {
		return gc.retornarCurso(cod);
	}

	public void deleteCurso(String codigo) {
		gc.deleteCurso(codigo);
	}

	public List<Curso> getListCurso() {
		return gc.getListCurso();
	}

	// gerais

}

/*
 * public String livrosAlugadosPorAluno(Aluno aluno) { return
 * ge.livrosAlugadosPorAluno(aluno); }
 * 
 * public String livrosAlugadosPorFuncionario(Funcionario funcionario) { return
 * ge.livrosAlugadosPorFuncionario(funcionario); }
 */