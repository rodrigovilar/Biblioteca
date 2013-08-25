package br.com.aps.controle;

import java.io.Serializable;
import java.util.List;

import br.com.aps.entidade.Livro;
import br.com.aps.entidade.Periodico;
import br.com.aps.excecao.Excecao;


public class GerentePeriodico implements Serializable{
	
	public void addPeriodico(Periodico periodico){
		
		validadorCamposPreenchidos(periodico);
		isExistePeriodico(periodico.getId());
		GerentePersistencia.getInstance().getListaPeriodico().add(periodico);
		GerentePersistencia.persistir();
	}
	
	public Periodico deletePeriodico(int id) {
		Periodico periodicoRemovido;
		for (Periodico periodico: GerentePersistencia.getInstance().getListaPeriodico()) {
			if (periodico.getId() == id) {
				GerentePersistencia.getInstance().getListaLivro().remove(periodico);
				GerentePersistencia.persistir();
				periodicoRemovido = periodico;
				return periodicoRemovido;
			}
		}
		throw new Excecao("Periodico Não Encontrado ");

	}
	
	public boolean consultarSituacaoDePeriodico(int id){
		for(Periodico periodico: GerentePersistencia.getInstance().getListaPeriodico()){
			if(periodico.getSituacao()== periodico.getSituacao().DISPONIVEL)
				return true;
		}throw new Excecao("Periodico Ocupado");
	}
	
	public Periodico consultarPeriodico(int id) {
		for (Periodico periodivo : GerentePersistencia.getInstance().getListaPeriodico()) {
			if (periodivo.getId() == id);
			return periodivo;
		}
		throw new Excecao("Nao existe Periodico Cadastrado com este ID!");
	}

	public List<Periodico> getListPeriodico() {
		return GerentePersistencia.getInstance().getListaPeriodico();
	}

	private void isExistePeriodico(int id) {
		for(Periodico periodico: GerentePersistencia.getInstance().getListaPeriodico()){
			if(periodico.getId()== id)
				throw new Excecao("Periodico já existe"); 
		}
	}
	
	private boolean validadorCamposPreenchidos(Periodico periodico) {
		if ((periodico.getId() == 0) || (periodico.getSituacao() == null))
			throw new Excecao("Campos obrigatórios não preenchidos");
		return true;
	}

}
