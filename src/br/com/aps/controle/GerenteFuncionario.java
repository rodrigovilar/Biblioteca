package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Funcionario;
import br.com.aps.excecao.Excecao;

public class GerenteFuncionario {

	List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

	public void addFuncionario(Funcionario funcionario) {
		isExisteFuncionario(funcionario.getCpf());
		listaFuncionario.add(funcionario);
	}

	public void deleteFuncionario(Funcionario funcionario) {
		retornarFuncionario(funcionario.getCpf());
		listaFuncionario.remove(funcionario);
	}

	public Funcionario retornarFuncionario(String cpfFuncionario) {
		for (Funcionario funcionario : listaFuncionario) {
			if (funcionario.getCpf().equals(cpfFuncionario))
				return funcionario;
		}
		throw new Excecao("Não existe funcionario com este cpf");
	}

	public Funcionario alterarDadosFuncionario(Funcionario funcionario) {
		for (Funcionario func : listaFuncionario) {
			if (funcionario.getCpf().equals(func.getCpf())) {
				func = funcionario;
				listaFuncionario.add(func);
				return func;
			}
		}
		throw new Excecao("Não existe funcionairo com este cpf");
	}
	
	public List<Funcionario> getListFuncionario(){
		return listaFuncionario;
	}
	
	public boolean campoSetorNaoPreenchido(){
		return true;
	}

	private void isExisteFuncionario(String cpfFuncionario){
		for(Funcionario funcionario: listaFuncionario){
			if(funcionario.getCpf().equals(cpfFuncionario))
				throw new Excecao("Funcionário já existente"); 
		}
	}
	

}
