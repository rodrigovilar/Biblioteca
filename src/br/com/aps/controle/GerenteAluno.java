package br.com.aps.controle;

import java.io.Serializable;
import java.util.List;
import br.com.aps.entidade.Aluno;
import br.com.aps.excecao.Excecao;
import br.com.aps.util.Validador;

public class GerenteAluno implements Serializable {

	public void addAluno(Aluno aluno) {
		if((aluno.getCurso()== null) || (aluno.getCpf()== null)){
			throw new Excecao("Campos obrigatórios não preenchidos");
		}
		if(Validador.validadorCPF(aluno.getCpf()) == false){
			throw new Excecao("CPF inválido"); 
		}
		isExisteAluno(aluno.getCpf());
		GerentePersistencia.getInstance().getListaAluno().add(aluno);
		GerentePersistencia.persistir();
	}

	public Aluno deleteAluno(Aluno aluno2) {
		Aluno alunoRemovido;
			for (Aluno aluno: GerentePersistencia.getInstance().getListaAluno()) {
				if (aluno.getCpf().equals(aluno2.getCpf())) {
					GerentePersistencia.getInstance().getListaAluno().remove(aluno);
					GerentePersistencia.persistir();
					alunoRemovido = aluno;
					return alunoRemovido;
				}
			}
			throw new Excecao("Aluno não existente");
		}
	

	public Aluno retornarAluno(String cpf) {
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

	public void isExisteAluno(String cpf){
		for(Aluno aluno: GerentePersistencia.getInstance().getListaAluno()){
			if(aluno.getCpf().equals(cpf))
				throw new Excecao("Aluno já existente"); 
		}
	}
}
