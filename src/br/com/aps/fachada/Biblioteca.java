package br.com.aps.fachada;

import java.util.List;
import br.com.aps.controle.GerenteAluno;
import br.com.aps.controle.GerenteEmprestimo;
import br.com.aps.controle.GerenteFuncionario;
import br.com.aps.controle.GerenteLivro;
import br.com.aps.controle.GerentePeriodico;
import br.com.aps.controle.GerenteProfessor;
import br.com.aps.controle.GerenteUsuario;
import br.com.aps.entidade.Acervo;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Livro;
import br.com.aps.entidade.Periodico;
import br.com.aps.entidade.Professor;
import br.com.aps.entidade.TipoAcervo;
import br.com.aps.entidade.Usuario;


public class Biblioteca{

	GerenteUsuario gu = new GerenteUsuario();
	GerenteAluno ga = new GerenteAluno();
	GerenteFuncionario gf = new GerenteFuncionario();
	GerenteProfessor gp = new GerenteProfessor();
	GerentePeriodico gperiod = new GerentePeriodico();
	GerenteLivro gl = new GerenteLivro();
	GerenteEmprestimo ge = new GerenteEmprestimo();
	
	public void teste(){
		Acervo acervo = new Acervo();
		acervo.setTipoAcervo(TipoAcervo.LIVRO);
		TipoAcervo.values();
		
		if(acervo.getTipoAcervo().equals(TipoAcervo.LIVRO)){
			
		}
	}
	
	
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
	
	public List<Usuario> getListUsuario(){
		return gu.getListUsuario();
	}
	
	//Dados para o aluno
	public void addAluno(Aluno aluno) {
		ga.addAluno(aluno);
	}
	
	public void deleteAluno(Aluno aluno){
		ga.deleteAluno(aluno);
	}
	
	public Aluno retornarAluno(String cpfAluno) {
		return ga.retornarAluno(cpfAluno);
	}
	
	public Aluno alterarDadosAluno(Aluno aluno){
		return ga.alterarDadosAluno(aluno);
	}

	public List<Aluno> getListAluno(){
		return ga.getListAluno();
	}
	
	//Dados para o funcionario
		
	public void addFuncionario(Funcionario funcionario) {
		gf.addFuncionario(funcionario);
	}

	public void deleteFuncionario(Funcionario funcionario) {
		gf.deleteFuncionario(funcionario);
	}
	
	public Funcionario retornarFuncionario(String cpfFuncionario) {
		return gf.retornarFuncionario(cpfFuncionario);
	}

	public Funcionario alterarDadosFuncionario(Funcionario funcionario) {
		return gf.alterarDadosFuncionario(funcionario);
	}
	
	public List<Funcionario> getListFuncionario(){
		return gf.getListFuncionario();
	}
	
	public boolean campoSetorNaoPreenchido(){
		return gf.campoSetorNaoPreenchido();
	}

	//Dados do professor
	public void addProfessor(Professor professor) {
		gp.addProfessor(professor);
	}
	
	public void deleteProfessor(Professor professor) {
		gp.deleteProfessor(professor);
	}
		
	public Professor retornarProfessor(String cpfProfessor) {
		return gp.retornarProfessor(cpfProfessor);
	}
	
	public Professor alterarDadosProfessor(Professor professor) {
		return gp.alterarDadosProfessor(professor);
	}
	
	public List<Professor> getListProfessor(){
		return gp.getListProfessor();
	}

	//Dados de acervo
	public void addLivro(Livro livro){
		gl.addLivro(livro);
	}
	
	public Livro retornarLivro(int id){
		return gl.retornarLivro(id);
	}
	
	public List<Livro> getListLivro() {
		return gl.getListLivro();
	}
	
	public void addPeriodico(Periodico periodico){
		gperiod.addPeriodico(periodico);
	}
	
	public Periodico retornarPeriodico(int id){
		return gperiod.retornarPeriodico(id);
	}
	
	public List<Periodico> getListPeriodico() {
		return gperiod.getListPeriodico();
	}
	
	
	//dados de emprestimo
	public void realizaEmprestimo(Emprestimo emprestimo){
		ge.realizaEmprestimo(emprestimo);
	}
	
	public String livrosAlugadosPorAluno(Aluno aluno) {
		return ge.livrosAlugadosPorAluno(aluno);
	}
	
	public String livrosAlugadosPorFuncionario(Funcionario funcionario) {
		return ge.livrosAlugadosPorFuncionario(funcionario);
	}
	
	public void devolverAcervo(Emprestimo emprestimo){
		ge.devolverAcervo(emprestimo);
	}
	
	public boolean campoCPFNaoPreenchido(){
		return ga.campoCPFNaoPreenchido();
	}
	
	//gambiarra
	public boolean cpfvalido(String cpf){
		return ga.cpfvalido(cpf);
}


	
}
