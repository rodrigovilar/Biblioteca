package br.com.aps.teste;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.aps.controle.GerentePersistencia;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Curso;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Livro;
import br.com.aps.entidade.Periodico;
import br.com.aps.entidade.Pessoa;
import br.com.aps.entidade.Professor;
import br.com.aps.entidade.Situacao;
import br.com.aps.entidade.Tema;
import br.com.aps.entidade.TipoAcervo;
import br.com.aps.entidade.TipoPessoa;
import br.com.aps.entidade.Usuario;
import br.com.aps.excecao.Excecao;
import br.com.aps.fachada.Biblioteca;

public class BibliotecaTest {
	Biblioteca fachada;

	@Before
	public void criarFacadaBiblioteca() {
		GerentePersistencia.reset();
		fachada = new Biblioteca();
	}

	@After
	public void fim() throws IOException {
		GerentePersistencia.apagarConteudoArquivo();
	}

	@Test
	public void addUsuario() {
		Usuario usuario = criarUsuarioAdministrador();
		fachada.addUsuario(usuario);
			
		List<Usuario> listaUsuario = fachada.getListUsuario();
		Usuario usuarioCadastrado = listaUsuario.get(0);
		Assert.assertEquals(usuarioCadastrado,usuario);
	}

	@Test
	public void UsuarioFazLoginSucesso() {
		criarUsuarioAdministradorEFazLoginSistema();
		Assert.assertTrue(true);
	}

	@Test(expected = Excecao.class)
	public void usuarioNaoCadastrodoFazLogin() {
		Usuario usuario = criarUsuarioAdministrador();
		fachada.login(usuario.getLogin(), usuario.getSenha());
	}
	
	@Test(expected = Excecao.class)
	public void addUsuarioNovamente() {
		Usuario usuario = criarUsuarioAdministrador();
		
		fachada.addUsuario(usuario);
		fachada.addUsuario(usuario);	
	}

	@Test(expected = Excecao.class)
	public void addUsuarioAusenciaDadoLogin() {
		Usuario usuario = new Usuario();
		usuario.setNome("Joao");
		usuario.setMatricula("80809912");
		usuario.setCpf("123.472.123-09");
		usuario.setTelefone("87234567");
		usuario.setSetor(null);
		usuario.setLogin(null);
		usuario.setSenha(null);

		fachada.addUsuario(usuario);
	}
	
	@Test
	public void testListaAtualDeUsuario() {
		Assert.assertEquals(fachada.getListUsuario().size(), 0);
		Usuario usuario = criarUsuarioAdministrador();
		fachada.addUsuario(usuario);
		Assert.assertEquals(fachada.getListUsuario().size(), 1);
		Assert.assertEquals(fachada.getListUsuario().get(0), usuario);
	}

	private Usuario criarUsuarioAdministrador() {
		Usuario usuario = new Usuario();
		usuario.setNome("Joao");
		usuario.setMatricula("80809912");
		usuario.setCpf("123.472.123-09");
		usuario.setTelefone("87234567");
		usuario.setSetor("administração");
		usuario.setLogin("joao");
		usuario.setSenha("1213");
		return usuario;
	}
	
	private void criarUsuarioAdministradorEFazLoginSistema() {
		Usuario usuario = criarUsuarioAdministrador();
		fachada.addUsuario(usuario);
		fachada.login(usuario.getLogin(), usuario.getSenha());
	}

	// Aluno
	@Test
	public void addAluno() {
		criarUsuarioAdministradorEFazLoginSistema();
		Aluno aluno = criarAluno();
		fachada.addAluno(aluno);

		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		Assert.assertEquals(alunoCadastrado, aluno);
	}

	@Test(expected = Excecao.class)
	public void addAlunoNovamente() {
		criarUsuarioAdministradorEFazLoginSistema();

		Aluno a = criarAluno();
		fachada.addAluno(a);
		fachada.addAluno(a);
	}

	@Test
	public void testListaAtualDeAluno() {
		Assert.assertEquals(fachada.getListAluno().size(), 0);
		Aluno aluno = criarAluno();
		fachada.addAluno(aluno);
		Assert.assertEquals(fachada.getListAluno().size(), 1);
		Assert.assertEquals(fachada.getListAluno().get(0), aluno);
	}

	@Test
	public void testPersistenciaAluno() {
		Aluno aluno = criarAluno();
		fachada.addAluno(aluno);
		fachada = new Biblioteca();
		aluno = fachada.retornarAluno("999.888.666-87");// procura o funcionario pelo cpf
		aluno.setTelefone("4444-4444");// altera os dados
		fachada.alterarDadosAluno(aluno);// altera no banco de dados
		fachada = new Biblioteca();
		Assert.assertEquals(fachada.retornarAluno("999.888.666-87")
				.getTelefone(), "4444-4444");
	}

	@Test(expected = Excecao.class)
	public void addAlunoSemCPF() {
		criarUsuarioAdministradorEFazLoginSistema();
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf("");
		aluno.setTelefone("87234567");
		aluno.setCurso(criarCurso());
		fachada.addAluno(aluno);
	}

	@Test
	public void deletarAluno() {
		criarUsuarioAdministradorEFazLoginSistema();
		Aluno a = criarAluno();
		fachada.addAluno(a);
		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		Assert.assertEquals(alunoCadastrado, a);
		Assert.assertEquals(alunoCadastrado, fachada.deleteAluno(a.getCpf()));
	}

	@Test(expected = Excecao.class)
	public void deletarAlunoNovamente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Aluno alu = criarAluno();
		fachada.addAluno(alu);

		fachada.deleteAluno(alu.getCpf());
		fachada.deleteAluno(alu.getCpf());
	}

	@Test(expected = Excecao.class)
	public void deletarAlunoInexistente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Aluno alun = new Aluno();
		fachada.deleteAluno(alun.getCpf());
	}

	@Test
	public void alterarDadosAluno() {
		criarUsuarioAdministradorEFazLoginSistema();
		Aluno al = criarAluno();
		fachada.addAluno(al);

		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		al.setTelefone("12989891121");
		fachada.alterarDadosAluno(al);
		Assert.assertEquals(alunoCadastrado, al);
	}

	@Test(expected = Excecao.class)
	public void AddAlunoCPFInvalido() {
		criarUsuarioAdministradorEFazLoginSistema();
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf("12323");
		aluno.setTelefone("87234567");
		aluno.setCurso(criarCurso());
		fachada.addAluno(aluno);
	}

	private Aluno criarAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf("234.456.876-33");
		aluno.setTelefone("87234567");
		aluno.setCurso(criarCurso());
		aluno.setTipoPessoa(TipoPessoa.ALUNO);
		return aluno;
	}

	// funcionario
	@Test
	public void addFuncionario() {
		criarUsuarioAdministradorEFazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);

		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);
		Assert.assertEquals(funcionarioCadastrado, f);
	}

	@Test(expected = Excecao.class)
	public void addFuncionarioNovamente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		fachada.addFuncionario(f);
	}

	@Test
	public void testListaAtualDeFuncionario() {
		Assert.assertEquals(fachada.getListAluno().size(), 0);
		Funcionario funcionario = criarFuncionario();
		fachada.addFuncionario(funcionario);
		Assert.assertEquals(fachada.getListFuncionario().size(), 1);
		Assert.assertEquals(fachada.getListFuncionario().get(0), funcionario);
	}

	@Test(expected = Excecao.class)
	public void addFuncionarioAusenciaSetor() {
		criarUsuarioAdministradorEFazLoginSistema();
		Funcionario f = new Funcionario();
		f.setNome("Joao");
		f.setMatricula("80809912");
		f.setCpf("123.472.123-09");
		f.setTelefone("87234567");
		f.setSetor(null);
		fachada.addFuncionario(f);
	}

	@Test
	public void deletarFuncionario() {
		criarUsuarioAdministradorEFazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);

		Assert.assertEquals(funcionarioCadastrado, f);
		Assert.assertEquals(funcionarioCadastrado,
				fachada.deleteFuncionario(f.getCpf()));
	}

	@Test(expected = Excecao.class)
	public void deletarFuncionarioNovamente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);

		fachada.deleteFuncionario(f.getCpf());
		fachada.deleteFuncionario(f.getCpf());
	}

	@Test(expected = Excecao.class)
	public void deletarFuncionarioInexistente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Funcionario f = new Funcionario();
		fachada.deleteFuncionario(f.getCpf());
	}

	@Test
	public void alterarDadosFuncionario() {
		criarUsuarioAdministradorEFazLoginSistema();
		Funcionario funcionario = criarFuncionario();
		fachada.addFuncionario(funcionario);
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);

		funcionario.setTelefone("44447564");
		fachada.alterarDadosFuncionario(funcionario);
		Assert.assertEquals(funcionarioCadastrado, funcionario);
	}

	@Test
	public void testPersistenciaFuncionario() {
		Funcionario funcionario = criarFuncionario();
		fachada.addFuncionario(funcionario);
		fachada = new Biblioteca();
		funcionario = fachada.retornarFuncionario("123.472.123-09");// procura o funcionario pelo cpf
		funcionario.setTelefone("4444-4444");// altera os dados
		fachada.alterarDadosFuncionario(funcionario);// altera no banco de dados
		fachada = new Biblioteca();
		Assert.assertEquals(fachada.retornarFuncionario("123.472.123-09")
				.getTelefone(), "4444-4444");
	}

	private Funcionario criarFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Joao");
		funcionario.setMatricula("80809912");
		funcionario.setCpf("123.472.123-09");
		funcionario.setTelefone("87234567");
		funcionario.setSetor("Coordenacao");
		funcionario.setTipoPessoa(TipoPessoa.FUNCIONARIO);
		return funcionario;
	}

	// Professor
	@Test
	public void addProfessor() {
		criarUsuarioAdministradorEFazLoginSistema();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		List<Professor> listaProfessor = fachada.getListProfessor();
		Professor professorCadastrado = listaProfessor.get(0);

		Assert.assertEquals(professorCadastrado, p);
	}

	@Test(expected = Excecao.class)
	public void addProfessorNovamente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		fachada.addProfessor(p);
	}

	@Test(expected = Excecao.class)
	public void addProfessorAusenciaCamposObrigatorios() {
		criarUsuarioAdministradorEFazLoginSistema();
		Professor prof = new Professor();
		prof.setNome("Joao");
		prof.setMatricula("80809912");
		prof.setCpf(null);
		prof.setTelefone("87234567");
		prof.setDepartamento(null);

		fachada.addProfessor(prof);
	}

	@Test(expected = Excecao.class)
	public void addProfessorCPFInvalido() {
		criarUsuarioAdministradorEFazLoginSistema();
		Professor prof = new Professor();
		prof.setNome("Joao");
		prof.setMatricula("80809912");
		prof.setCpf("000.988-12");
		prof.setTelefone("87234567");
		prof.setDepartamento("Exatas");

		fachada.addProfessor(prof);
	}

	@Test
	public void testListaAtualDeProfessor() {
		Assert.assertEquals(fachada.getListProfessor().size(), 0);
		Professor professor = criarProfessor();
		fachada.addProfessor(professor);
		Assert.assertEquals(fachada.getListProfessor().size(), 1);
		Assert.assertEquals(fachada.getListProfessor().get(0), professor);
	}

	@Test
	public void deletarProfessor() {
		criarUsuarioAdministradorEFazLoginSistema();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		List<Professor> listaProfessor = fachada.getListProfessor();
		Professor professorCadastrado = listaProfessor.get(0);
		Assert.assertEquals(professorCadastrado, p);

		fachada.deleteProfessor(p.getCpf());
		Assert.assertEquals(professorCadastrado, p);
	}

	@Test(expected = Excecao.class)
	public void deletarProfessorInexistente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Professor professor = criarProfessor();
		fachada.deleteProfessor(professor.getCpf());
	}

	private Professor criarProfessor() {
		Professor professor = new Professor();
		professor.setNome("Joao");
		professor.setMatricula("80809912");
		professor.setCpf("123.468.987-56");
		professor.setTelefone("87234567");
		professor.setDepartamento("Exatas");
		professor.setTipoPessoa(TipoPessoa.PROFESSOR);
		return professor;
	}

	// curso
	@Test
	public void addCurso() {
		criarUsuarioAdministradorEFazLoginSistema();
		Curso curso = criarCurso();
		fachada.addCursos(curso);

		List<Curso> listarCurso = fachada.getListCurso();
		Curso cursoCadastrado = listarCurso.get(0);
		Assert.assertEquals(cursoCadastrado, curso);
	}

	@Test(expected = Excecao.class)
	public void addCursoNovamente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Curso c = criarCurso();
		fachada.addCursos(c);
		fachada.addCursos(c);
	}

	@Test(expected = Excecao.class)
	public void addCursoAusenciaCodigo() {
		criarUsuarioAdministradorEFazLoginSistema();
		Curso curso = new Curso();
		curso.setCodigo(null);
		curso.setNome("LCC");

		fachada.addCursos(curso);
	}

	@Test
	public void deletarCadastroDeCurso() {
		criarUsuarioAdministradorEFazLoginSistema();
		Curso c = criarCurso();
		fachada.addCursos(c);
		List<Curso> listaCursos = fachada.getListCurso();
		Curso cadastroDeCurso = listaCursos.get(0);
		Assert.assertEquals(cadastroDeCurso, c);

		fachada.deleteCurso(c.getCodigo());
		Assert.assertEquals(cadastroDeCurso, c);
	}

	@Test(expected = Excecao.class)
	public void deletarCursoInexistente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Curso c = criarCurso();
		fachada.deleteCurso(c.getCodigo());
	}

	@Test
	public void testListaAtualDeCurso() {
		Assert.assertEquals(fachada.getListCurso().size(), 0);
		Curso curso = criarCurso();
		fachada.addCursos(curso);
		Assert.assertEquals(fachada.getListCurso().size(), 1);
		Assert.assertEquals(fachada.getListCurso().get(0), curso);
	}

	private Curso criarCurso() {
		Curso curso = new Curso();
		curso.setCodigo("1234");
		curso.setNome("computacao");
		return curso;
	}

	// Livro
	@Test
	public void addLivro() {
		criarUsuarioAdministradorEFazLoginSistema();
		Livro livro = criarLivro();
		fachada.addLivro(livro);

		List<Livro> listaLivro = fachada.getListLivro();
		Livro cadastroDeLivros = listaLivro.get(0);
		Assert.assertEquals(cadastroDeLivros, livro);
	}

	@Test(expected = Excecao.class)
	public void AdicionarLivroNovamente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Livro livro = criarLivro();
		fachada.addLivro(livro);
		fachada.addLivro(livro);
	}

	@Test
	public void deletarLivro() {
		criarUsuarioAdministradorEFazLoginSistema();
		Livro livro = criarLivro();
		fachada.addLivro(livro);
		List<Livro> listaDeLivro = fachada.getListLivro();
		Livro livroCadastrado = listaDeLivro.get(0);
		Assert.assertEquals(livroCadastrado, livro);

		fachada.deleteLivro(livro.getId());
		Assert.assertEquals(livroCadastrado, livro);
	}

	@Test(expected = Excecao.class)
	public void deletarLivroInexistente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Livro livro = new Livro();
		fachada.deleteLivro(livro.getId());
	}

	@Test(expected = Excecao.class)
	public void AdicionarLivroSemID() {
		criarUsuarioAdministradorEFazLoginSistema();
		Livro livro = new Livro();
		livro.setEditora("test");
		livro.setAutor("abc");
		livro.setTitulo("Java");
		livro.setTipoAcervo(TipoAcervo.LIVRO);
		livro.setArea("Exatas");
		livro.setId(0);

		fachada.addLivro(livro);
	}

	@Test
	public void testListaAtualDeLivro() {
		Assert.assertEquals(fachada.getListLivro().size(), 0);
		Livro livro = criarLivro();
		fachada.addLivro(livro);

		Assert.assertEquals(fachada.getListLivro().size(), 1);
		Assert.assertEquals(fachada.getListLivro().get(0), livro);
	}

	private Livro criarLivro() {
		Livro livro = new Livro();
		livro.setEditora("test");
		livro.setAutor("abc");
		livro.setTitulo("Java");
		livro.setTipoAcervo(TipoAcervo.LIVRO);
		livro.setArea("Exatas");
		livro.setSituacao(Situacao.DISPONIVEL);
		livro.setId(1);
		return livro;
	}

	// Periodico
	@Test
	public void addPeriodico() {
		criarUsuarioAdministradorEFazLoginSistema();
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);

		List<Periodico> listaPeriodico = fachada.getListPeriodico();
		Periodico cadastroDePeriodico = listaPeriodico.get(0);
		Assert.assertEquals(cadastroDePeriodico, periodico);
	}

	@Test(expected = Excecao.class)
	public void adicionarPeriodicoNovamente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);
		fachada.addPeriodico(periodico);
	}

	@Test
	public void deletarPeriodico() {
		criarUsuarioAdministradorEFazLoginSistema();
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);

		List<Periodico> listaPeriodico = fachada.getListPeriodico();
		Periodico cadastroDePeriodico = listaPeriodico.get(0);
		Assert.assertEquals(cadastroDePeriodico, periodico);

		fachada.deletePeriodico(periodico.getId());
		Assert.assertEquals(cadastroDePeriodico, periodico);
	}

	@Test(expected = Excecao.class)
	public void deletarPeriodicoInexistente() {
		criarUsuarioAdministradorEFazLoginSistema();
		Periodico periodico = new Periodico();
		fachada.deletePeriodico(periodico.getId());
	}

	@Test(expected = Excecao.class)
	public void AdicionarPeriodicoSemID() {
		criarUsuarioAdministradorEFazLoginSistema();
		Periodico periodico = new Periodico();
		periodico.setAutor("Jorge");
		periodico.setDataPublicacao(new Date(12 / 12 / 2011));
		periodico.setArea("Exatas");
		periodico.setTipoAcervo(TipoAcervo.PERIODICO);
		periodico.setTitulo("kkdsds");

		fachada.addPeriodico(periodico);
	}

	@Test
	public void testListaAtualDePeriodico() {
		Assert.assertEquals(fachada.getListPeriodico().size(), 0);
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);
		Assert.assertEquals(fachada.getListPeriodico().size(), 1);
		Assert.assertEquals(fachada.getListPeriodico().get(0), periodico);
	}

	private Periodico criarPeriodico() {
		Periodico periodico = new Periodico();
		periodico.setAutor("Jorge");
		periodico.setDataPublicacao(new Date(12 / 12 / 2011));
		periodico.setArea("Exatas");
		periodico.setTipoAcervo(TipoAcervo.PERIODICO);
		periodico.setSituacao(Situacao.DISPONIVEL);
		periodico.setTitulo("kkdsds");
		periodico.setId(1);
		return periodico;
	}

	// Emprestimos e devoluções

	@Test
	public void realizarEmprestimoParaAluno() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoAluno();
		fachada.realizaEmprestimo(ep);

		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	private Emprestimo criarEmprestimoAluno() {
		Emprestimo emprestimo = new Emprestimo();
		Aluno aluno = criarAluno();
		fachada.addAluno(aluno);
		Livro livro = criarLivro();
		fachada.addLivro(livro);
		emprestimo.setPessoa(aluno);
		emprestimo.setAcervo(livro);
		emprestimo.setIdSolicitacao("01");
		emprestimo.setDataEmprestimo(criarDataEmprestimo());
		emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
		return emprestimo;
	}

	@Test
	public void realizarEmprestimoParaFuncionario() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoFuncionario();
		fachada.realizaEmprestimo(ep);

		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	private Emprestimo criarEmprestimoFuncionario() {
		Emprestimo emprestimo = new Emprestimo();
		Funcionario funcionario = criarFuncionario();
		fachada.addFuncionario(funcionario);
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);
		emprestimo.setPessoa(funcionario);
		emprestimo.setAcervo(periodico);
		emprestimo.setIdSolicitacao("01");
		emprestimo.setDataEmprestimo(criarDataEmprestimo());
		emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
		return emprestimo;
	}

	@Test
	public void realizarEmprestimoParaProfessor() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoProfessor();
		fachada.realizaEmprestimo(ep);

		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	private Emprestimo criarEmprestimoProfessor() {
		Emprestimo emprestimo = new Emprestimo();
		Professor professor = criarProfessor();
		fachada.addProfessor(professor);
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);

		emprestimo.setPessoa(professor);
		emprestimo.setAcervo(periodico);
		emprestimo.setIdSolicitacao("01");
		emprestimo.setDataEmprestimo(criarDataEmprestimo());
		emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
		return emprestimo;
	}

	@Test(expected = Excecao.class)
	public void realizarEmprestimoComQuantidadeMaiorQuePermitidaParaAluno() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep1 = criarEmprestimoAluno();
		fachada.realizaEmprestimo(ep1);

		Emprestimo ep2 = criarEmprestimoAluno();
		Livro livro2 = criarLivro();
		fachada.addLivro(livro2);
		ep2.setAcervo(livro2);
		ep1.setIdSolicitacao("25");
		fachada.realizaEmprestimo(ep2);

		Emprestimo ep3 = criarEmprestimoAluno();
		Livro livro3 = criarLivro();
		fachada.addLivro(livro3);
		ep2.setAcervo(livro3);
		ep1.setIdSolicitacao("26");
		fachada.realizaEmprestimo(ep3);
	}

	@Test(expected = Excecao.class)
	public void realizarEmprestimoComQuantidadeMaiorQuePermitidaParaFuncionario() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep1 = criarEmprestimoFuncionario();
		fachada.realizaEmprestimo(ep1);

		Emprestimo ep2 = criarEmprestimoFuncionario();
		Livro livro2 = criarLivro();
		fachada.addLivro(livro2);
		ep2.setAcervo(livro2);
		ep1.setIdSolicitacao("28");
		fachada.realizaEmprestimo(ep2);

		Emprestimo ep3 = criarEmprestimoFuncionario();
		Periodico periodico1 = criarPeriodico();
		fachada.addPeriodico(periodico1);
		ep3.setAcervo(periodico1);
		ep3.setIdSolicitacao("29");
		fachada.realizaEmprestimo(ep3);

		Emprestimo ep4 = criarEmprestimoFuncionario();
		Periodico periodico2 = criarPeriodico();
		fachada.addPeriodico(periodico2);
		ep3.setAcervo(periodico2);
		ep3.setIdSolicitacao("30");
		fachada.realizaEmprestimo(ep4);
	}

	@Test(expected = Excecao.class)
	public void realizarEmprestimoComPeriodicoParaAluno() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo emprestimo = criarEmprestimoAluno();
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);
		emprestimo.setAcervo(periodico);
		fachada.realizaEmprestimo(emprestimo);
	}

	@Test
	public void alunoDevolveEmprestimoNoPrazo() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarDevolucaoNoPrazo();
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	private Emprestimo criarDevolucaoNoPrazo() {
		Emprestimo emprestimo = criarEmprestimoAluno();
		emprestimo.setDataDevolucao(criarDataPrevistaEntrega());
		return emprestimo;
	}

	@Test
	public void AlunodevolverEmprestimoForaDoPrazoEComMultaPaga() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoAluno();
		ep.setDataDevolucao(criarDataEntregaForaDoPrazo());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	@Test
	public void AlunoDevolveEmprestimoForaDoPrazoEPendeciaPagamentoMulta() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoAluno();
		ep.setDataDevolucao(criarDataEntregaForaDoPrazo());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	@Test
	public void FuncionarioDevolveEmprestimoNoPrazo() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoFuncionario();
		ep.setDataDevolucao(criarDataPrevistaEntrega());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	@Test
	public void FuncionarioDevolveEmprestimoForaDoPrazo() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoFuncionario();
		ep.setDataDevolucao(criarDataEntregaForaDoPrazo());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	@Test
	public void ProfessorDevolverEmprestimoForaPrazo() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoProfessor();
		ep.setDataDevolucao(criarDataEntregaForaDoPrazo());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	@Test
	public void ProfessorDevolverEmprestimoNoPrazo() {
		criarUsuarioAdministradorEFazLoginSistema();
		Emprestimo ep = criarEmprestimoProfessor();
		ep.setDataDevolucao(criarDataPrevistaEntrega());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	@SuppressWarnings("deprecation")
	private Date criarDataEmprestimo() {
		Date data = new Date();
		data.setDate(1);
		data.setMonth(8);
		data.setYear(2013);
		return data;
	}

	@SuppressWarnings("deprecation")
	private Date criarDataPrevistaEntrega() {
		Date data2 = new Date();
		data2.setDate(10);
		data2.setMonth(8);
		data2.setYear(2013);
		return data2;
	}

	@SuppressWarnings("deprecation")
	private Date criarDataEntregaForaDoPrazo() {
		Date data4 = new Date();
		data4.setDate(15);
		data4.setMonth(8);
		data4.setYear(2013);
		return data4;
	}
}