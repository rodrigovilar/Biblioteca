package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Livro;
import br.com.aps.excecao.Excecao;

public class GerenteLivro {

	List<Livro> listaLivro = new ArrayList<Livro>();
	
	
	public void addLivro(Livro livro){
		isExisteLivro(livro.getId());
		listaLivro.add(livro);
	}
	
	public Livro retornarLivro(int id){
		for(Livro livro: listaLivro){
			if(livro.getId()== id)
				return livro;
		}
		throw new Excecao("Nao existe livro cadastrado com este id");
	}
	
	public List<Livro> getListLivro() {
		return listaLivro;
	}
	
	private void isExisteLivro(int id) {
		for(Livro livro: listaLivro){
			if(livro.getId()== id)
				throw new Excecao("Livro já existe"); 
		}
	}
	
}
