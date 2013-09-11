package br.com.aps.controle;

import java.io.Serializable;
import java.util.List;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Pessoa;
import br.com.aps.excecao.Excecao;
import br.com.aps.util.Validador;

public class GerenteAluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addAluno(Aluno alunoo) {
		Validador.validadorCPF(alunoo.getCpf());
		validadorCamposPreenchidos(alunoo);
		isExisteAluno(alunoo.getCpf());
		GerentePersistencia.getInstance().getListaAluno().add(alunoo);
		GerentePersistencia.persistir();
	}

	public Aluno deleteAluno(String cpf) {
		Aluno alunoRemovido;
			for (Aluno aluno: GerentePersistencia.getInstance().getListaAluno()) {
				if (aluno.getCpf().equals(cpf)) {
					GerentePersistencia.getInstance().getListaAluno().remove(aluno);
					GerentePersistencia.persistir();
					alunoRemovido = aluno;
					return alunoRemovido;
				}
			}
			throw new Excecao("Aluno não existente");
		}
	

	public Aluno consultarAluno(String cpf) {
		for (Aluno aluno: GerentePersistencia.getInstance().getListaAluno()) {
			if (aluno.getCpf().equals(cpf));
			return aluno;
		}
		throw new Excecao("Não existe Aluno com este cpf");
	}

	public Aluno alterarDadosAluno(Aluno aluno) {
		for (Aluno a : GerentePersistencia.getInstance().getListaAluno()) {
			if (a.getCpf().equals(aluno.getCpf())) {
				a = aluno;
				GerentePersistencia.persistir();
				return a;
			}
		}
		throw new Excecao("Não existe Aluno com este cpf");
	}
	
	public List<Aluno> getListAluno(){
		return GerentePersistencia.getInstance().getListaAluno();
	}

	private void isExisteAluno(String cpf){
		for(Aluno aluno: GerentePersistencia.getInstance().getListaAluno()){
			if(aluno.getCpf().equals(cpf))
				throw new Excecao("Aluno já existente"); 
		}
	}
	
	private boolean validadorCamposPreenchidos(Aluno aluno){
			if((aluno.getCurso()== null) || (aluno.getCpf()== null))
				throw new Excecao("Campos obrigatórios não preenchidos");
			return true;
	}
}
