package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;
import br.com.aps.entidade.Periodico;
import br.com.aps.excecao.Excecao;


public class GerentePeriodico {
	
	List<Periodico> listaPeriodico = new ArrayList<Periodico>();
	
	public void addPeriodico(Periodico periodico){
		isExistePeriodico(periodico.getId());
		listaPeriodico.add(periodico);
	}
	
	public Periodico retornarPeriodico(int id){
		for(Periodico periodico: listaPeriodico){
			if(periodico.getId()== id)
				return periodico;
		}
		throw new Excecao("Nao existe periodico cadastrado com este id");
	}

	public List<Periodico> getListPeriodico() {
		return listaPeriodico;
	}
	
	private void isExistePeriodico(int id) {
		for(Periodico periodico: listaPeriodico){
			if(periodico.getId()== id)
				throw new Excecao("Periodico já existe"); 
		}
	}
	
	

}
