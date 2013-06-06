package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Aluno;
import br.com.aps.excecao.Excecao;

public class GerenteAluno {

	List<Aluno> listaAluno = new ArrayList<Aluno>();

	public void addAluno(Aluno aluno) {
		isExisteAluno(aluno.getCpf());
		listaAluno.add(aluno);
	}

	public void deleteAluno(Aluno aluno) {
		retornarAluno(aluno.getCpf());
		listaAluno.remove(aluno);
	}

	public Aluno retornarAluno(String cpf) {
		for (Aluno aluno : listaAluno) {
			if (aluno.getCpf().equals(cpf));
			return aluno;
		}
		throw new Excecao("Não existe aluno com este cpf");
	}

	public Aluno alterarDadosAluno(Aluno aluno) {
		for (Aluno a : listaAluno) {
			if (aluno.getCpf().equals(a.getCpf())) {
				a = aluno;
				listaAluno.add(a);
				return a;
			}
		}
		throw new Excecao("Não existe aluno com este cpf");
	}
	
	public List<Aluno> getListAluno(){
		return listaAluno;
	}

	public boolean campoCPFNaoPreenchido(){
		return true;
	}
	
	public boolean cpfvalido(String cpf){
		if(cpf.length()==15){
			return true;
		}else{
			return false;
		}
	}
	public void isExisteAluno(String cpf){
		for(Aluno aluno: listaAluno){
			if(aluno.getCpf().equals(cpf))
				throw new Excecao("Aluno já existente"); 
		}
	}
}
