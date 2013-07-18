package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Professor;
import br.com.aps.excecao.Excecao;
import br.com.aps.util.Validador;

public class GerenteProfessor {

	List<Professor> listaProfessor = new ArrayList<Professor>();

	public void addProfessor(Professor professor) {
		if((professor.getCpf()==null) || (professor.getDepartamento()==null)){
			throw new Excecao("Campos obrigatórios não preenchidos");
		}
		if(Validador.validadorCPF(professor.getCpf())== false){
			throw new Excecao("CPF inválido");
		}	
		isExisteProfessor(professor.getCpf());
		listaProfessor.add(professor);
	}

	public void deleteProfessor(Professor professor) {
		retornarProfessor(professor.getCpf());
		listaProfessor.remove(professor);
	}

	public Professor retornarProfessor(String cpfProfessor) {
		for (Professor professor : listaProfessor) {
			if (professor.getCpf().equals(cpfProfessor))
				;
			return professor;
		}
		throw new Excecao("Não existe professor com este cpf");
	}

	public Professor alterarDadosProfessor(Professor professor) {
		for (Professor prof : listaProfessor) {
			if (professor.getCpf().equals(prof.getCpf())) {
				listaProfessor.remove(prof);
				prof = professor;
				listaProfessor.add(prof);
				return prof;
			}
		}
		throw new Excecao("Não existe professor com este cpf");
	}
	
	public List<Professor> getListProfessor() {
		return listaProfessor;
	}
	

	private void isExisteProfessor(String cpfProfessor){
		for(Professor professor: listaProfessor){
			if(professor.getCpf().equals(cpfProfessor))
				throw new Excecao("Professor não existente"); 
		}
	}
	
}
