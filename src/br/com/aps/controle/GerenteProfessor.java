package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Professor;
import br.com.aps.excecao.Excecao;
import br.com.aps.util.Validador;

public class GerenteProfessor {

	List<Professor> listaProfessor = new ArrayList<Professor>();

	public void addProfessor(Professor professor) {
		validadorCamposPreenchidos(professor);
		Validador.validadorCPF(professor.getCpf());
		isExisteProfessor(professor.getCpf());
		GerentePersistencia.getInstance().getListaProfessor().add(professor);
		GerentePersistencia.persistir();
	}

	public Professor deleteProfessor(String cpf) {
		Professor professorRemovido;
		for (Professor professor: GerentePersistencia.getInstance().getListaProfessor()){
			if (professor.getCpf().equals(cpf)) {
				GerentePersistencia.getInstance().getListaAluno().remove(professor);
				GerentePersistencia.persistir();
				professorRemovido = professor;
				return professorRemovido;
			}
		}
		throw new Excecao("Professor não existente");
	}

	public Professor consultarProfessor(String cpfProfessor) {
		for (Professor professor : GerentePersistencia.getInstance()
				.getListaProfessor()) {
			if (professor.getCpf().equals(cpfProfessor));
			return professor;
		}
		throw new Excecao("Não existe professor com este cpf");
	}

	public Professor alterarDadosProfessor(Professor professor) {
		for (Professor prof : GerentePersistencia.getInstance()
				.getListaProfessor()) {
			if (professor.getCpf().equals(prof.getCpf())) {
				listaProfessor.remove(prof);
				prof = professor;
				GerentePersistencia.persistir();
				return prof;
			}

		}
		throw new Excecao("Não existe professor com este cpf");
	}

	public List<Professor> getListProfessor() {
		return GerentePersistencia.getInstance().getListaProfessor();
	}

	private void isExisteProfessor(String cpfProfessor) {
		for (Professor professor : GerentePersistencia.getInstance().getListaProfessor()) {
			if (professor.getCpf().equals(cpfProfessor))
				throw new Excecao("Professor já existente");
		}
	}

	private boolean validadorCamposPreenchidos(Professor professor) {
		if ((professor.getCpf() == null)|| (professor.getDepartamento() == null))
			throw new Excecao("Campos obrigatórios não preenchidos");
		return true;
	}
}
