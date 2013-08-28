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

/*	REVER A QUESTÃO DE APAGAR OS DADOS ASSIM QUE O TESTE ACABA, 
 POIS NÃO ESTÁ APAGANDO E INFLUÊNCIA NA EXECUÇÃO DOS DEMAIS TESTES.
 */
public class BibliotecaTest {
	Biblioteca fachada;

	@Before
	public void criarFacadaBiblioteca() {
		fachada = new Biblioteca();
	}

	@After
	public void fim() throws IOException {
		GerentePersistencia.apagarConteudoArquivo();
	}

	@Test
	public void addUsuario() {
		Usuario usuario = criarUsuarioAdministrador();
		usuario.setNome("Joao");
		usuario.setMatricula("80809912");
		usuario.setCpf("123.472.123-09");
		usuario.setTelefone("87234567");
		usuario.setSetor("administração");
		usuario.setLogin("joao");
		usuario.setSenha("1213");
		fachada.addUsuario(usuario);
		List<Usuario> listaUsuario = fachada.getListUsuario();
		Usuario usuarioCadastrado = listaUsuario.get(0);
		Assert.assertEquals(usuarioCadastrado, usuario);
	}

	@Test
	public void UsuarioFazLoginSucesso() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Assert.assertTrue(true);
	}

	@Test(expected = Excecao.class)
	public void usuarioNaoCadastrodoFazLogin() {
		Usuario usuario = new Usuario();
		fachada.login(usuario.getLogin(), usuario.getSenha());
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
	public void addAluno() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno aluno = criarAluno();
		fachada.addAluno(aluno);

		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		Assert.assertEquals(alunoCadastrado, aluno);
	}

	@Test(expected = Excecao.class)
	public void addAlunoNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();

		Aluno a = criarAluno();
		fachada.addAluno(a);
		fachada.addAluno(a);
	}

	@Test
	public void testListaAtualDeAluno() {
		Assert.assertEquals(fachada.getListAluno().size(), 1);
		Aluno aluno = criarAluno();
		aluno.setCpf("121.222.333-88");
		fachada.addAluno(aluno);
		Assert.assertEquals(fachada.getListAluno().size(), 2);
		Assert.assertEquals(fachada.getListAluno().get(1), aluno);
	}

	@Test
	public void testPersistenciaAluno() {
		Aluno aluno = criarAluno();
		aluno.setCpf("224.456.876-33");
		fachada.addAluno(aluno);
		fachada = new Biblioteca();
		// Assert.assertEquals(aluno, fachada.retornarAluno("234.456.876-33"));
		// VERIFICAR ISTO COM ALGUÉM-------DÚVIDA
		aluno = fachada.retornarAluno("999.888.666-87");// procura o
														// funcionario(pelo cpf)
		aluno.setTelefone("4444-4444");// altera os dados
		fachada.alterarDadosAluno(aluno);// altera no banco de dados
		fachada = new Biblioteca();
		Assert.assertEquals(fachada.retornarAluno("999.888.666-87")
				.getTelefone(), "4444-4444");
	}

	@Test(expected = Excecao.class)
	public void addAlunoSemCPF() {
		criarUsuarioAdministrador_E_FazLoginSistema();
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
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno a = criarAluno();
		a.setCpf("333.444.666-99");
		fachada.addAluno(a);
		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(3);
		Assert.assertEquals(alunoCadastrado, a);
		Assert.assertEquals(alunoCadastrado, fachada.deleteAluno(a.getCpf()));
	}

	@Test(expected = Excecao.class)
	public void deletarAlunoNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno alu = criarAluno();
		fachada.addAluno(alu);

		fachada.deleteAluno(alu.getCpf());
		fachada.deleteAluno(alu.getCpf());
	}

	@Test(expected = Excecao.class)
	public void deletarAlunoNaoExistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno alun = new Aluno();
		fachada.deleteAluno(alun.getCpf());
	}

	@Test
	public void alterarAluno() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno al = criarAluno2();
		fachada.addAluno(al);

		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(2);
		al.setTelefone("12989891121");
		fachada.alterarDadosAluno(al);
		Assert.assertEquals(alunoCadastrado, al);
	}

	@Test(expected = Excecao.class)
	public void AddAlunoCPFInvalido() {
		criarUsuarioAdministrador_E_FazLoginSistema();
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

	private Aluno criarAluno2() {
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf("234.455.876-33");
		aluno.setTelefone("87234567");
		aluno.setCurso(criarCurso());
		aluno.setTipoPessoa(TipoPessoa.ALUNO);
		return aluno;
	}

	// funcionario
	@Test
	public void addFuncionario() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);

		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);
		Assert.assertEquals(funcionarioCadastrado, f);
	}

	@Test(expected = Excecao.class)
	public void addFuncionarioNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		fachada.addFuncionario(f);
	}

	@Test
	public void testListaAtualDeFuncionario() {
		Assert.assertEquals(fachada.getListAluno().size(), 5);
		Funcionario funcionario = criarFuncionario();
		funcionario.setCpf("121.222.333-88");
		fachada.addFuncionario(funcionario);
		Assert.assertEquals(fachada.getListFuncionario().size(), 2);
		Assert.assertEquals(fachada.getListFuncionario().get(0), funcionario);
	}

	@Test(expected = Excecao.class)
	public void addFuncionarioAusenciaSetor() {
		criarUsuarioAdministrador_E_FazLoginSistema();
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
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario f = criarFuncionario2();
		fachada.addFuncionario(f);
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(1);
		Assert.assertEquals(funcionarioCadastrado, f);
		Assert.assertEquals(funcionarioCadastrado,
				fachada.deleteFuncionario(f.getCpf()));
	}

	@Test(expected = Excecao.class)
	public void deletarFuncionarioNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);

		fachada.deleteFuncionario(f.getCpf());
		fachada.deleteFuncionario(f.getCpf());
	}

	@Test(expected = Excecao.class)
	public void deletarFuncionarioNaoExistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario f = new Funcionario();
		fachada.deleteFuncionario(f.getCpf());
	}

	@Test
	public void alterarFuncionario() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario funcionario = criarFuncionario();
		funcionario.setCpf("111.654.777-88");
		fachada.addFuncionario(funcionario);
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(1);

		funcionario.setTelefone("44447564");
		fachada.alterarDadosFuncionario(funcionario);
		Assert.assertEquals(funcionarioCadastrado, funcionario);
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

	private Funcionario criarFuncionario2() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Jo");
		funcionario.setMatricula("8a0809912");
		funcionario.setCpf("123.472.123-29");
		funcionario.setTelefone("87234567e");
		funcionario.setSetor("Coordenacao");
		funcionario.setTipoPessoa(TipoPessoa.FUNCIONARIO);
		return funcionario;
	}

	// Professor
	@Test
	public void addProfessor() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		List<Professor> listaProfessor = fachada.getListProfessor();
		Professor professorCadastrado = listaProfessor.get(0);

		Assert.assertEquals(professorCadastrado, p);
	}

	@Test(expected = Excecao.class)
	public void addProfessorNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		fachada.addProfessor(p);
	}

	@Test(expected = Excecao.class)
	public void addProfessorAusenciaCamposObrigatorios() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Professor prof = new Professor();
		prof.setNome("Joao");
		prof.setMatricula("80809912");
		prof.setCpf(null);
		prof.setTelefone("87234567");
		prof.setDepartamento(null);

		fachada.addProfessor(prof);
	}

	@Test
	public void testListaAtualDeProfessor() {
		Assert.assertEquals(fachada.getListProfessor().size(), 1);
		Professor professor = criarProfessor();
		professor.setCpf("000.444.555-22");
		fachada.addProfessor(professor);
		Assert.assertEquals(fachada.getListProfessor().size(), 2);
		Assert.assertEquals(fachada.getListProfessor().get(1), professor);
	}

	@Test
	public void deletarProfessor() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Professor p = criarProfessor();
		p.setCpf("333.222.111-12");
		fachada.addProfessor(p);
		List<Professor> listaProfessor = fachada.getListProfessor();
		Professor professorCadastrado = listaProfessor.get(2);
		Assert.assertEquals(professorCadastrado, p);
		fachada.deleteProfessor(p.getCpf());
		Assert.assertEquals(professorCadastrado, p);
	}

	@Test
	public void deletarProfessorNaoExistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
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
		criarUsuarioAdministrador_E_FazLoginSistema();
		Curso curso = criarCurso();
		fachada.addCursos(curso);

		List<Curso> listarCurso = fachada.getListCurso();
		Curso cursoCadastrado = listarCurso.get(0);
		Assert.assertEquals(cursoCadastrado, curso);
	}

	@Test(expected = Excecao.class)
	public void addCursoNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Curso c = criarCurso();
		fachada.addCursos(c);
		fachada.addCursos(c);
	}

	@Test(expected = Excecao.class)
	public void addCursoAusenciaCodigo() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Curso curso = new Curso();
		curso.setCodigo(null);
		curso.setNome("LCC");

		fachada.addCursos(curso);
	}

	@Test
	public void deletarCadastroDeCurso() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Curso c = criarCurso();
		c.setCodigo("4343");
		fachada.addCursos(c);
		List<Curso> listaCursos = fachada.getListCurso();
		Curso cadastroDeCurso = listaCursos.get(1);
		Assert.assertEquals(cadastroDeCurso, c);

		fachada.deleteCurso(c.getCodigo());
		Assert.assertEquals(cadastroDeCurso, c);
	}

	@Test(expected = Excecao.class)
	public void deletarCursoNaoExistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Curso c = criarCurso2();
		fachada.deleteCurso(c.getCodigo());
	}

	@Test
	public void testListaAtualDeCurso() {
		Assert.assertEquals(fachada.getListCurso().size(), 1);
		Curso curso = criarCurso();
		curso.setCodigo("888");
		fachada.addCursos(curso);
		Assert.assertEquals(fachada.getListCurso().size(), 2);
		Assert.assertEquals(fachada.getListCurso().get(1), curso);
	}

	private Curso criarCurso() {
		Curso curso = new Curso();
		curso.setCodigo("1234");
		curso.setNome("computacao");
		return curso;
	}

	private Curso criarCurso2() {
		Curso curso = new Curso();
		curso.setCodigo("12345");
		curso.setNome("computacaao");
		return curso;
	}

	// Livro
	@Test
	public void addLivro() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Livro livro = criarLivro();
		fachada.addLivro(livro);

		List<Livro> listaLivro = fachada.getListLivro();
		Livro cadastroDeLivros = listaLivro.get(0);
		Assert.assertEquals(cadastroDeLivros, livro);
	}

	@Test(expected = Excecao.class)
	public void AdicionarLivroNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Livro l = criarLivro();
		fachada.addLivro(l);
		fachada.addLivro(l);
	}

	@Test
	public void deletarLivro() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Livro l = criarLivro();
		l.setId(3);
		fachada.addLivro(l);
		List<Livro> listaDeLivro = fachada.getListLivro();
		Livro livroCadastrado = listaDeLivro.get(1);
		Assert.assertEquals(livroCadastrado, l);

		fachada.deleteLivro(l.getId());
		Assert.assertEquals(livroCadastrado, l);
	}

	@Test(expected = Excecao.class)
	public void deletarLivroInexistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Livro l = new Livro();
		fachada.deleteLivro(l.getId());
	}

	@Test(expected = Excecao.class)
	public void AdicionarLivroSemID() {
		criarUsuarioAdministrador_E_FazLoginSistema();
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
		Assert.assertEquals(fachada.getListLivro().size(), 1);
		Livro livro = criarLivro();
		livro.setId(2);
		fachada.addLivro(livro);
		Assert.assertEquals(fachada.getListLivro().size(), 2);
		Assert.assertEquals(fachada.getListLivro().get(1), livro);
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
		criarUsuarioAdministrador_E_FazLoginSistema();
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);

		List<Periodico> listaPeriodico = fachada.getListPeriodico();
		Periodico cadastroDePeriodico = listaPeriodico.get(0);
		Assert.assertEquals(cadastroDePeriodico, periodico);
	}

	@Test(expected = Excecao.class)
	public void deletarPeriodicoNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Periodico periodico = criarPeriodico();
		periodico.setId(3);
		fachada.addPeriodico(periodico);
		fachada.addPeriodico(periodico);
	}

	@Test
	public void deletarPeriodico() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Periodico periodico = criarPeriodico();
		periodico.setId(2);
		fachada.addPeriodico(periodico);

		List<Periodico> listaPeriodico = fachada.getListPeriodico();
		Periodico cadastroDePeriodico = listaPeriodico.get(2);
		Assert.assertEquals(cadastroDePeriodico, periodico);

		fachada.deletePeriodico(periodico.getId());
		Assert.assertEquals(cadastroDePeriodico, periodico);
	}

	@Test(expected = Excecao.class)
	public void deletarPeriodicoInexistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Periodico periodico = new Periodico();
		fachada.deletePeriodico(periodico.getId());
	}

	@Test(expected = Excecao.class)
	public void AdicionarPeriodicoSemID() {
		criarUsuarioAdministrador_E_FazLoginSistema();
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
		Assert.assertEquals(fachada.getListPeriodico().size(), 3);
		Periodico periodico = criarPeriodico();
		periodico.setId(4);
		fachada.addPeriodico(periodico);
		Assert.assertEquals(fachada.getListPeriodico().size(), 4);
		Assert.assertEquals(fachada.getListPeriodico().get(3), periodico);
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
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep = criarEmprestimoAluno();
		fachada.realizaEmprestimo(ep);

		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	private Emprestimo criarEmprestimoAluno() {
		Emprestimo emprestimo = new Emprestimo();
		Aluno aluno = criarAluno();
		aluno.setCpf("545.545.666-88");
		Livro livro = criarLivro();
		livro.setId(5);
		fachada.addAluno(aluno);
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
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep = criarEmprestimoFuncionario();
		fachada.realizaEmprestimo(ep);

		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	private Emprestimo criarEmprestimoFuncionario() {
		Emprestimo emprestimo = new Emprestimo();
		Funcionario funcionario = criarFuncionario();
		funcionario.setCpf("000.555.333-99");
		fachada.addFuncionario(funcionario);
		Periodico periodico = criarPeriodico();
		periodico.setId(5);
		fachada.addPeriodico(periodico);
		emprestimo.setPessoa(funcionario);
		emprestimo.setIdSolicitacao("01");
		emprestimo.setDataEmprestimo(criarDataEmprestimo());
		emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
		return emprestimo;
	}

	@Test
	public void realizarEmprestimoParaProfessor() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep = criarEmprestimoProfessor();
		fachada.realizaEmprestimo(ep);

		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	private Emprestimo criarEmprestimoProfessor() {
		Emprestimo emprestimo = new Emprestimo();
		Professor professor = criarProfessor();
		professor.setCpf("110.555.333-99");
		fachada.addProfessor(professor);
		Periodico periodico = criarPeriodico();
		periodico.setId(6);
		fachada.addPeriodico(periodico);
		emprestimo.setIdSolicitacao("01");
		emprestimo.setDataEmprestimo(criarDataEmprestimo());
		emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
		return emprestimo;
	}

	@Test(expected = Excecao.class)
	public void realizarEmprestimoComQuantidadeMaiorQuePermitidaDeLivroParaAluno() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep1 = criarEmprestimoAluno();
		Livro livro2 = criarLivro();
		livro2.setId(10);
		fachada.addLivro(livro2);
		ep1.setAcervo(livro2);
		ep1.setIdSolicitacao("24");
		Emprestimo ep2 = criarEmprestimoAluno();
		Livro livro3 = criarLivro();
		livro3.setId(11);
		fachada.addLivro(livro3);
		ep2.setAcervo(livro3);
		ep1.setIdSolicitacao("25");
		Emprestimo ep3 = criarEmprestimoAluno();
		Livro livro4 = criarLivro();
		livro3.setId(12);
		fachada.addLivro(livro4);
		ep2.setAcervo(livro4);
		ep1.setIdSolicitacao("26");

		fachada.realizaEmprestimo(ep1);
		fachada.realizaEmprestimo(ep2);
		fachada.realizaEmprestimo(ep3);
	}

	@Test(expected = Excecao.class)
	public void realizarEmprestimoComQuantidadeMaiorQuePermitidaDeAcervosParaFuncionario() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep1 = criarEmprestimoFuncionario();
		Livro livro2 = criarLivro();
		livro2.setId(13);
		fachada.addLivro(livro2);
		ep1.setAcervo(livro2);
		ep1.setIdSolicitacao("27");

		Emprestimo ep2 = criarEmprestimoFuncionario();
		Livro livro3 = criarLivro();
		livro3.setId(14);
		fachada.addLivro(livro3);
		ep2.setAcervo(livro3);
		ep1.setIdSolicitacao("28");

		Emprestimo ep3 = criarEmprestimoFuncionario();
		Periodico periodico2 = criarPeriodico();
		periodico2.setId(7);
		fachada.addPeriodico(periodico2);
		ep3.setAcervo(periodico2);
		ep3.setIdSolicitacao("29");

		Emprestimo ep4 = criarEmprestimoFuncionario();
		Periodico periodico3 = criarPeriodico();
		periodico3.setId(8);
		fachada.addPeriodico(periodico3);
		ep3.setAcervo(periodico3);
		ep3.setIdSolicitacao("30");

		fachada.realizaEmprestimo(ep1);
		fachada.realizaEmprestimo(ep2);
		fachada.realizaEmprestimo(ep3);
		fachada.realizaEmprestimo(ep4);
	}

	@Test(expected = Excecao.class)
	public void realizarEmprestimoComPeriodicoParaAluno() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep1 = criarEmprestimoAluno();
		Aluno aluno2 = criarAluno();
		aluno2.setCpf("000.766.232-77");
		Periodico periodico1 = criarPeriodico();
		periodico1.setId(9);
		fachada.addPeriodico(periodico1);
		ep1.setPessoa(aluno2);
		ep1.setAcervo(periodico1);
		ep1.setIdSolicitacao("31");
		fachada.realizaEmprestimo(ep1);
	}

	@Test
	public void alunoDevolveEmprestimoNoPrazo() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep = criarEmprestimoComDevolucaoNoPrazo();
		Aluno aluno= criarAluno();
		aluno.setCpf("000.999.122-34");
		ep.setPessoa(aluno);
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	private Emprestimo criarEmprestimoComDevolucaoNoPrazo() {
		Emprestimo emprestimo = criarEmprestimoAluno();
		emprestimo.setDataDevolucao(criarDataPrevistaEntrega());
		return emprestimo;
	}

	@Test
	public void AlunodevolverEmprestimoForaDoPrazoEComMultaPaga() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep = criarEmprestimoAluno();
		Aluno aluno= criarAluno();
		aluno.setCpf("120.999.122-34");
		ep.setPessoa(aluno);
		ep.setDataDevolucao(criarDataEntregaForaDoPrazo());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	@Test(expected = Excecao.class)
	public void AlunoDevolveEmprestimoForaDoPrazoEPendeciaPagamentoMulta() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep = criarEmprestimoAluno();
		ep.setDataDevolucao(criarDataEntregaForaDoPrazo());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
	}
	
	@Test
	public void FuncionarioDevolveEmprestimoNoPrazo(){
		criarUsuarioAdministrador_E_FazLoginSistema();
		Emprestimo ep = criarEmprestimoFuncionario();
		ep.setDataDevolucao(criarDataPrevistaEntrega());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}
	
	@Test
	public void FuncionarioDevolveEmprestimoForaDoPrazo(){
		criarUsuarioAdministrador_E_FazLoginSistema();
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
		Emprestimo ep = criarEmprestimoProfessor();
		ep.setDataDevolucao(criarDataPrevistaEntrega());
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	 }

	private Usuario criarUsuarioAdministrador() {
		Usuario usuario = new Usuario();
		usuario.setLogin("3333");
		usuario.setSenha("1234");
		return usuario;
	}

	private void criarUsuarioAdministrador_E_FazLoginSistema() {
		Usuario usuario = criarUsuarioAdministrador();
		usuario.setNome("Joao");
		usuario.setMatricula("80809912");
		usuario.setCpf("123.472.123-09");
		usuario.setTelefone("87234567");
		usuario.setSetor("admi");
		usuario.setLogin("joao");
		usuario.setSenha("1234");
		fachada.addUsuario(usuario);
		fachada.login(usuario.getLogin(), usuario.getSenha());
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