package br.com.aps.teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.aps.entidade.Acervo;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Curso;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Livro;
import br.com.aps.entidade.Periodico;
import br.com.aps.entidade.Professor;
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
		fachada = new Biblioteca();
	}

	@Test
	public void addUsuario() {
		Usuario usuario = criarUsuarioAdministrador();
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
	public void usuarioNaoCadastrodoFazLogin(Usuario usuario) {
		fachada.login(usuario.getLogin(), usuario.getSenha());
	}

	@Test (expected = Excecao.class)
	public void addUsuarioAusenciaDados_Senha() {
		Usuario usuario = new Usuario();
		usuario.setSenha("123");
		fachada.addUsuario(usuario);
	}

	@Test
	public void addAluno() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno a = criarAluno();
		fachada.addAluno(a);

		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		Assert.assertEquals(alunoCadastrado, a);
	}

	@Test(expected = Excecao.class)
	public void addAlunoNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();

		Aluno a = criarAluno();
		fachada.addAluno(a);
		fachada.addAluno(a);
	}

	@Test
	public void deletarAluno() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno a = criarAluno();
		fachada.addAluno(a);
		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		Assert.assertEquals(alunoCadastrado, a);

		fachada.deleteAluno(a);
		int size = listaAlunno.size();
		Assert.assertEquals(0, size);
	}

	@Test(expected = Excecao.class)
	public void deletarAlunoNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno a = criarAluno();
		fachada.addAluno(a);

		fachada.deleteAluno(a);
		fachada.deleteAluno(a);
	}

	@Test(expected = Excecao.class)
	public void deletarAlunoNaoExistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno alun = criarAluno();
		fachada.deleteAluno(alun);
	}

	@Test
	public void alterarAluno() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno a = criarAluno();
		fachada.addAluno(a);

		List<Aluno> listaAlunno = fachada.getListAluno();
		Aluno alunoCadastrado = listaAlunno.get(0);
		a.setTelefone("12121121");
		fachada.alterarDadosAluno(a);
		Assert.assertEquals(alunoCadastrado, a);
	}

	@Test (expected = Excecao.class)
	public void AddAlunoCPFInvalido() { 
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf("12323");
		aluno.setTelefone("87234567");
		aluno.setCurso(criarCurso());
		fachada.addAluno(aluno);
		fachada.cpfvalido(aluno.getCpf());	
	}

	@Test ()
	public void addAlunoSemCPF() { 
		criarUsuarioAdministrador_E_FazLoginSistema();
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf(null);
		aluno.setTelefone("87234567");
		aluno.setCurso(criarCurso());
		
	}

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
	public void deletarFuncionario() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);
		Assert.assertEquals(funcionarioCadastrado, f);
		
		fachada.deleteFuncionario(f);
		int size = listaFuncionario.size();
		Assert.assertEquals(0, size);
	}

	@Test(expected = Excecao.class)
	public void deletarFuncionarioNovamente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.addFuncionario(f);
		
		fachada.deleteFuncionario(f);
		fachada.deleteFuncionario(f);
	}

	@Test(expected = Excecao.class)
	public void deletarFuncionarioNaoExistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario f = criarFuncionario();
		fachada.deleteFuncionario(f);
	}

	@Test
	public void alterarFuncionario() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Funcionario funcionario = criarFuncionario();
		fachada.addFuncionario(funcionario);
		List<Funcionario> listaFuncionario = fachada.getListFuncionario();
		Funcionario funcionarioCadastrado = listaFuncionario.get(0);

		funcionario.setTelefone("44447564");
		fachada.alterarDadosFuncionario(funcionario);
		Assert.assertEquals(funcionarioCadastrado, funcionario);
	}

	@Test
	public void addFuncionarioSemSetor() {
		criarUsuarioAdministrador_E_FazLoginSistema();
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

	@Test
	public void deletarProfessor() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Professor p = criarProfessor();
		fachada.addProfessor(p);
		List<Professor> listaProfessor = fachada.getListProfessor();
		Professor professorCadastrado = listaProfessor.get(0);
		Assert.assertEquals(professorCadastrado, p);
		
		fachada.deleteProfessor(p);
		int size = listaProfessor.size();
		Assert.assertEquals(0, size);
	}

	@Test(expected = Excecao.class)
	public void deletarProfessornaoExistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Professor w = criarProfessor();
		fachada.deleteProfessor(w);
	}

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

	@Test
	public void deletarCadastroDeCurso() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Curso c = criarCurso();
		fachada.addCursos(c);
		List<Curso> listaCursos = fachada.getListCurso();
		Curso cadastroDeCurso = listaCursos.get(0);
		Assert.assertEquals(cadastroDeCurso, c);
		
		fachada.deletarCurso(c);
		int size = listaCursos.size();
		Assert.assertEquals(0, size);
	}

	@Test(expected = Excecao.class)
	public void removerCursoNaoExistente() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Curso c = criarCurso();
		fachada.deletarCurso(c);
	}

	@Test(expected = Exception.class)
	public void addCursoComCodigoNulo() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Curso c1 = criarCurso();
		c1.setCodigo(null);
		fachada.addCursos(c1);
	}

	@Test
	public void addLivro() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Livro livro = criarLivro();
		fachada.addLivro(livro);

		List<Livro> listaLivro = fachada.getListLivro();
		Livro cadastroDeLivros = listaLivro.get(0);
		Assert.assertEquals(cadastroDeLivros, livro);
	}

	@Test
	public void addPeriodico() {
		criarUsuarioAdministrador_E_FazLoginSistema();
		Periodico periodico = criarPeriodico();
		fachada.addPeriodico(periodico);

		List<Periodico> listaPeriodico = fachada.getListPeriodico();
		Periodico cadastroDePeriodico = listaPeriodico.get(0);
		Assert.assertEquals(cadastroDePeriodico, periodico);
	}
	
	@Test
	public void realizarEmprestimoLivroParaAluno(){
		Emprestimo ep = criarEmprestimoSoLivro();
		fachada.realizaEmprestimo(ep);
		
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}
	
	@Test(expected = Excecao.class)
	public void realizarEmprestimoPeriodicoParaAluno(){
		Emprestimo ep3 = criarEmprestimoAlunoComPeriodico();
		fachada.realizaEmprestimo(ep3);
	}
	
	@Test(expected = Excecao.class)
	public void realizarEmprestimoVariosLivroParaAluno(){
		Emprestimo ep = criarEmprestimoIndevido();
		fachada.realizaEmprestimo(ep);	
	}
	
	@Test
	public void alunoDevolveEmprestimoNoPrazo(){
		Emprestimo ep = criarEmprestimoComDevolucaoNoPrazo();
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		
		fachada.devolverEmprestimo(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);	
	}

	@Test(expected=Excecao.class)
	public void devolverEmprestimoComMultaPendente(){
		Emprestimo ep = criarEmprestimoComDevolucaoForadoPrazo();
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);

		fachada.devolverEmprestimo(emprestimoCadastrado);
	}
	
	@Test
	public void devolverEmprestimoComMultaPaga(){
		Emprestimo ep = criarEmprestimoComDevolucaoForadoPrazo();
		fachada.realizaEmprestimo(ep);
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		
		fachada.devolverEmprestimoForaDoPrazoEComMultaPaga(emprestimoCadastrado);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}

	@Test
	public void realizarEmprestimoParaProfessor(){
		Emprestimo ep = criarEmprestimo();
		fachada.realizaEmprestimo(ep);
		
		List<Emprestimo> listaEmprestimo = fachada.getListEmprestimo();
		Emprestimo emprestimoCadastrado = listaEmprestimo.get(0);
		Assert.assertEquals(emprestimoCadastrado, ep);
	}
	
	private Aluno criarAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome("Joao");
		aluno.setMatricula("80809912");
		aluno.setCpf("123.444.345-12");
		aluno.setTelefone("87234567");
		aluno.setCurso(criarCurso());
		aluno.setTipoPessoa(TipoPessoa.ALUNO);
		return aluno;
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

	private Usuario criarUsuarioAdministrador() {
		Usuario usuario = new Usuario();
		usuario.setLogin("3333");
		usuario.setSenha("1234");
		return usuario;
	}

	private void criarUsuarioAdministrador_E_FazLoginSistema () {
		Usuario usuario = criarUsuarioAdministrador();
		fachada.addUsuario(usuario);
		fachada.login(usuario.getLogin(), usuario.getSenha());
	}

	private Curso criarCurso() {
		Curso curso = new Curso();
		curso.setCodigo("1234");
		curso.setNome("compuatacao");
		return curso;
	}

	private Livro criarLivro() {
		Livro livro = new Livro();
		livro.setEditora("test");
		livro.setAutor("abc");
		livro.setTitulo("Java");
		livro.setTipoAcervo(TipoAcervo.LIVRO);
		livro.setTema(new Tema("Exatas", "ABC"));
		livro.setId(1);
		return livro;
	}

	private Livro criarLivro2() {
		Livro livro = new Livro();
		livro.setEditora("AVC");
		livro.setAutor("JOAO");
		livro.setTitulo("REDES");
		livro.setTipoAcervo(TipoAcervo.LIVRO);
		livro.setTema(new Tema("Exatas", "TRD"));
		livro.setId(2);
		return livro;
	}

	
	private Periodico criarPeriodico() {
		Periodico periodico = new Periodico();
			periodico.setAutor("Jorge");
			periodico.setDataPublicacao(new Date(12 / 12 / 2011));
			periodico.setTema(new Tema("Exatas", "VCS"));
			periodico.setTipoAcervo(TipoAcervo.PERIODICO);
			periodico.setTitulo("kkdsds");
		return periodico;
	}

	private List<Acervo> criarListaAcervoSoLivro() {
		List<Acervo> listaAcervo = new ArrayList<Acervo>();
			listaAcervo.add(criarLivro());
			listaAcervo.add(criarLivro2());
		return listaAcervo;
	}
	
	private List<Acervo> criarListaAcervovariosLivros() {
		List<Acervo> listaAcervo = new ArrayList<Acervo>();
			listaAcervo.add(criarLivro());
			listaAcervo.add(criarLivro2());
			listaAcervo.add(criarLivro2());
			listaAcervo.add(criarLivro());
		return listaAcervo;
	}
	
	private List<Acervo> criarListaAcervo() {
		List<Acervo> listaAcervo2 = new ArrayList<Acervo>();
			listaAcervo2.add(criarLivro());
			listaAcervo2.add(criarLivro2());
			listaAcervo2.add(criarPeriodico());
		return listaAcervo2;
	}
	
	@SuppressWarnings("deprecation")
	private Date criarDataEmprestimo(){
		Date data = new Date();
			data.setDate(1);
			data.setMonth(8);
			data.setYear(2013);
		return data;	
	}
	
	@SuppressWarnings("deprecation")
	private Date criarDataPrevistaEntrega(){
		Date data2 = new Date();
			data2.setDate(10);
			data2.setMonth(8);
			data2.setYear(2013);
		return data2;
	}
	
	@SuppressWarnings("deprecation")
	private Date criarDataEntregaNoPrazo(){
		Date data3 = new Date();
			data3.setDate(10);
			data3.setMonth(8);
			data3.setYear(2013);
		return data3;
	}
	
	@SuppressWarnings("deprecation")
	private Date criarDataEntregaForaDoPrazo(){
		Date data4 = new Date();
			data4.setDate(15);
			data4.setMonth(8);
			data4.setYear(2013);
		return data4;
	}


	private Emprestimo criarEmprestimoSoLivro() {
		Emprestimo emprestimo = new Emprestimo();
			emprestimo.setPessoa(criarAluno());
			emprestimo.setListaAcervo(criarListaAcervoSoLivro());
			emprestimo.setIdSolicitacao("01");
			emprestimo.setDataEmprestimo(criarDataEmprestimo());
			emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
		return emprestimo;	
	}
	
	private Emprestimo criarEmprestimoComDevolucaoNoPrazo() {
		Emprestimo emprestimo = criarEmprestimoSoLivro();
			emprestimo.setDataDevolucao(criarDataEntregaNoPrazo());
		return emprestimo;
	}
	
	private Emprestimo criarEmprestimoComDevolucaoForadoPrazo() {
		Emprestimo emprestimo = criarEmprestimoSoLivro();
			emprestimo.setDataDevolucao(criarDataEntregaForaDoPrazo());
		return emprestimo;
	}
		
	private Emprestimo criarEmprestimo(){
		Emprestimo emprestimo = new Emprestimo();
			emprestimo.setPessoa(criarProfessor());
			emprestimo.setListaAcervo(criarListaAcervo());
			emprestimo.setIdSolicitacao("04");
			emprestimo.setDataEmprestimo(criarDataEmprestimo());
			emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
		return emprestimo;
	}
	
	private Emprestimo criarEmprestimoIndevido(){
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setPessoa(criarAluno());
		emprestimo.setListaAcervo(criarListaAcervovariosLivros());
		emprestimo.setIdSolicitacao("07");
		emprestimo.setDataEmprestimo(criarDataEmprestimo());
		emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
	return emprestimo;
	}
	
	private Emprestimo criarEmprestimoAlunoComPeriodico(){
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setPessoa(criarAluno());
		emprestimo.setListaAcervo(criarListaAcervo());
		emprestimo.setIdSolicitacao("05");
		emprestimo.setDataEmprestimo(criarDataEmprestimo());
		emprestimo.setDataPrevistaDevolucao(criarDataPrevistaEntrega());
	return emprestimo;
	}
}
