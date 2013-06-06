package br.com.aps.teste;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Professor;
import br.com.aps.entidade.Usuario;
import br.com.aps.excecao.Excecao;
import br.com.aps.fachada.Biblioteca;

public class BibliotecaTest {
		Biblioteca fachada;
		
	@Before
	public void criarFacadaBiblioteca(){
		 fachada = new Biblioteca();	
	}
	
	@Test 
	public void addUsuario(){
		Usuario usuario = criarUsuario();
		fachada.addUsuario(usuario);
		
		List<Usuario> listaUsuario = fachada.getListUsuario();
		Usuario usuarioCadastrado = listaUsuario.get(0);
		Assert.assertEquals(usuarioCadastrado, usuario);
	}
	
	@Test(expected=Excecao.class)
	public void usuarioSemCadastroTentaLogar(){
		Usuario usuario = new Usuario();
		fachada.login(usuario.getLogin(), usuario.getSenha());
	}
	
	@Test
	public void logarUsuarioSucesso(){ 
		criaCadastroELogaAdministrador();
		Assert.assertTrue(true);		
	}
	
	@Test
	public void addUsuarioSemLogin(){//conferir com o pessoal
		Usuario usuario = new Usuario();
		usuario.setSenha("123");
		fachada.addUsuario(usuario);
		Assert.assertFalse(false);
	}
	
	@Test 
	public void addAluno() {
		criaCadastroELogaAdministrador();
		Aluno a = criarAluno();
		fachada.addAluno(a);
		
		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		Assert.assertEquals(alunoCadastrado, a);		
	}
	
	@Test(expected=Excecao.class)
	public void addAlunoNovamente(){
		criaCadastroELogaAdministrador();
		
		Aluno a = criarAluno();
		fachada.addAluno(a);
		fachada.addAluno(a);			
	}
	
	@Test
	public void deletarAluno(){
		criaCadastroELogaAdministrador(); 	
		Aluno a = criarAluno();
		fachada.addAluno(a);
		
		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		Assert.assertEquals(alunoCadastrado, a);
		fachada.deleteAluno(a);
		Assert.assertTrue(true);//removeu Aluno
	}
	
	@Test(expected=Excecao.class)
	public void deletarAlunoNovamente(){
		criaCadastroELogaAdministrador(); 	
		Aluno a = criarAluno();
		fachada.addAluno(a);
		
		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		Assert.assertEquals(alunoCadastrado, a);
		fachada.deleteAluno(a);
		fachada.deleteAluno(a);
	}
	
	@Test (expected=Excecao.class)
	public void deletarAlunoNaoExistente() {
		criaCadastroELogaAdministrador();
		Aluno alun = criarAluno();
		fachada.deleteAluno(alun);
	}
	
	@Test
	public void alterarAluno(){
		criaCadastroELogaAdministrador();
		Aluno a = criarAluno();
		fachada.addAluno(a);
		
		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		fachada.alterarDadosAluno(a);
		Assert.assertEquals(alunoCadastrado, a);
	}
	
	@Test 
	public void AddAlunoCPFInvalido(){ //Conferir com o pessoal
		criaCadastroELogaAdministrador();
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf("12323");
		aluno.setTelefone("87234567");
		aluno.setCurso("LCC", "123");
		fachada.addAluno(aluno);
		
		Assert.assertFalse(false);
	}
		
	@Test
	public void addAlunoSemCPF(){ //Conferir com o pessoal
		criaCadastroELogaAdministrador();
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf(null);
		aluno.setTelefone("87234567");
		aluno.setCurso("LCC", "123");
		fachada.campoCPFNaoPreenchido();
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void addFuncionario(){
		criaCadastroELogaAdministrador();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);
		Assert.assertEquals(funcionarioCadastrado, f);
	}
	
	@Test (expected=Excecao.class)
	public void addFuncionarioNovamente(){
		criaCadastroELogaAdministrador();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		fachada.addFuncionario(f);
	}
	
	@Test 
	public void deletarFuncionario(){
		criaCadastroELogaAdministrador();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);
		Assert.assertEquals(funcionarioCadastrado, f);
		fachada.deleteFuncionario(f);
		Assert.assertTrue(true);	
	}
	
	@Test (expected=Excecao.class)
	public void deletarFuncionarioNovamente(){
		criaCadastroELogaAdministrador();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);
		Assert.assertEquals(funcionarioCadastrado, f);
		
		fachada.deleteFuncionario(f);
		fachada.deleteFuncionario(f);		
	}
		
	@Test(expected=Excecao.class)
	public void deletarFuncionarioNaoExistente() {
		criaCadastroELogaAdministrador();
		Funcionario f = criarFuncionario();
		fachada.deleteFuncionario(f);
	}
	
	@Test
	public void alterarFuncionario(){
		criaCadastroELogaAdministrador();
		Funcionario funcionario = criarFuncionario();
		fachada.addFuncionario(funcionario);
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);
		
		fachada.alterarDadosFuncionario(funcionario);
		Assert.assertEquals(funcionarioCadastrado, funcionario);
	}
	
	@Test
	public void addFuncionarioSemSetor(){ //conferir com o pessoal
		criaCadastroELogaAdministrador();
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Joao");
		funcionario.setMatricula("80809912");
		funcionario.setCpf("123.472.123-09");
		funcionario.setTelefone("87234567");
		funcionario.setSetor(null);
		
		fachada.campoSetorNaoPreenchido();
		Assert.assertTrue(true);
	}
	
	@Test
	public void addProfessor() {
		criaCadastroELogaAdministrador();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		List<Professor> listaProfessor = fachada.getListProfessor();
		Professor professorCadastrado = listaProfessor.get(0);
		
		Assert.assertEquals(professorCadastrado, p);
	}
	
	@Test (expected=Excecao.class)
	public void addProfessorNovamente() {
		criaCadastroELogaAdministrador();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		fachada.addProfessor(p);
	}
	
	@Test
	public void deletarProfessor() {
		criaCadastroELogaAdministrador();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		List<Professor> listaProfessor = fachada.getListProfessor();
		Professor professorCadastrado = listaProfessor.get(0);	
		Assert.assertEquals(professorCadastrado, p);
		fachada.deleteProfessor(p);
		Assert.assertEquals(professorCadastrado, p);//removeu professor
	}
	
	@Test(expected=Excecao.class)
	public void deletarProfessornaoExistente() {
		criaCadastroELogaAdministrador();
		Professor w = criarProfessor();
		fachada.deleteProfessor(w);
	}
	
	private Aluno criarAluno(){
		 Aluno aluno = new Aluno();
		 aluno.setNome("Joao");
		 aluno.setMatricula("80809912");
		 aluno.setCpf("123.444.345-12");
		 aluno.setTelefone("87234567");
		 aluno.setCurso("LCC", "123");	
		 return aluno;
	}
	
	private Professor criarProfessor(){
		 Professor professor = new Professor();
		 professor.setNome("Joao");
		 professor.setMatricula("80809912");
		 professor.setCpf("123.468.987-56");
		 professor.setTelefone("87234567");
		 professor.setDepartamento("Exatas");	
		 return professor;
	}
	
	private Funcionario criarFuncionario(){
		 Funcionario funcionario = new Funcionario();
		 funcionario.setNome("Joao");
		 funcionario.setMatricula("80809912");
		 funcionario.setCpf("123.472.123-09");
		 funcionario.setTelefone("87234567");
		 funcionario.setSetor("Coordenacao");	
		 return funcionario;
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new Usuario();
			usuario.setLogin("3333");
			usuario.setSenha("1234");
			return usuario;
	}
	
	private void criaCadastroELogaAdministrador(){
		Usuario usuario = criarUsuario();	
		fachada.addUsuario(usuario);
		fachada.login(usuario.getLogin(), usuario.getSenha());
	}
	
	/*
	@Test
	public void AlunoEmprestarLivro(){
		
		criaCadastroELogaAdministrador();
		Aluno aluno = criarAluno();
		fachada.addAluno(aluno);
		Livro livro = criaLivro();
		fachada.addLivro(livro);
		
		List<Livro> listaLivro = fachada.getListLivro();
		Livro livroCadastrado = listaLivro.get(0);
		List<String> aux = new ArrayList<String>();
		aux.add("JAVA");
		aux.add("JSF");
		aux.add("Oracle Database");
	}
		
	@Test(expected=Excecao.class)
	public void AlunoEmprestarVariosLivros(){
		
		criaCadastroELogaAdministrador();
		Aluno aluno = criarAluno();
		
		Livro livro = new Livro();
			livro.setTema(new Tema("Area1", "Descricao2"));
			livro.setEditora("ABC");
			livro.setId(12);
			livro.setTipoAcervo(false);
			livro.setTitulo("JAVA Básico");
			
		fachada.addLivro(livro);
		
		List<String> aux = new ArrayList<String>();
		aux.add("JAVA");
		aux.add("JSF");
		aux.add("Oracle Database");
		aux.add("JPA");
		
		fachada.realizaEmprestimo(aluno, aux, livro);
		
	}
	
	@Test(expected=Excecao.class)
	public void AlunoDevolveLivro(){ //tirara duvida com renan
		
		criaCadastroELogaAdministrador();
		Aluno aluno = criarAluno();
		
		Livro livro = new Livro();
			livro.setTema(new Tema("Area1", "Descricao2"));
			livro.setEditora("ABC");
			livro.setId(12);
			livro.setTipoAcervo(false);
			livro.setTitulo("JAVA Básico");
		fachada.addLivro(livro);
		
		Livro livro2 = new Livro();
			livro.setTema(new Tema("Area2", "Descricao3"));
			livro.setEditora("ABCD");
			livro.setId(13);
			livro.setTipoAcervo(false);
			livro.setTitulo("JAVA");
		fachada.addLivro(livro2);
		
		List<String> aux = new ArrayList<String>();
		aux.add("JAVA");
		aux.add("JSF");
		aux.add("Oracle Database");
				
		List<Acervo> listaAcervo = new ArrayList<Acervo>();
			listaAcervo.add(livro);
			listaAcervo.add(livro2);
			
		Date data = new Date();
			data.setDate(1);
			data.setMonth(8);
			data.setYear(2013);
		
		Date data2 = new Date();
			data.setDate(10);
			data.setMonth(8);
			data.setYear(2013);
			
		Date data3 = new Date();
			data.setDate(10);
			data.setMonth(8);
			data.setYear(2013);
		
		
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setListaAcervo(listaAcervo);
		emprestimo.setPessoa(aluno);
		emprestimo.setIdSolicitacao("1");
		emprestimo.setDataEmprestimo(data);
		emprestimo.setDataPrevistaDevolucao(data2);
		emprestimo.setDataDevolucao(data2);
		
		fachada.devolverAcervo(emprestimo);
		
	}
	
	@Test(expected=Excecao.class)
	public void AlunoemprestarPeriodico(){
		criaCadastroELogaAdministrador();
		Aluno aluno = criarAluno();
		
		Date data = new Date();
		data.setDate(10);
		data.setMonth(8);
		data.setYear(2011);
		
		Periodico periodico = new Periodico();
			periodico.setTema(new Tema("Area1", "Descricao2"));
			periodico.setDataPublicacao(data);
			periodico.setId(12);
			periodico.setTipoAcervo(TipoAcervo.PERIODICO);
			periodico.setTitulo("JAVA Básico");
		fachada.addPeriodico(periodico);
		
		List<String> aux = new ArrayList<String>();
		aux.add("JAVA");
		aux.add("JSF");
		aux.add("Oracle Database");
		
			
	}
	
	private Livro criaLivro(){
		Livro livro = new Livro();
			livro.setTema(new Tema("Area1", "Descricao2"));
			livro.setEditora("ABC");
			livro.setId(12);
			livro.setTipoAcervo(TipoAcervo.LIVRO);
			livro.setTitulo("JAVA Basico");
		return livro;	
	}
	*/
}
	
	
	
