package br.com.aps.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Acervo;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Curso;
import br.com.aps.entidade.Livro;
import br.com.aps.entidade.Situacao;
import br.com.aps.entidade.TipoAcervo;
import br.com.aps.excecao.Excecao;
import br.com.aps.util.Validador;

public class GerenteLivro implements Serializable {

	public void addLivro(Livro livro) {

		validadorCamposPreenchidos(livro);
		isExisteLivro(livro.getId());
		GerentePersistencia.getInstance().getListaLivro().add(livro);
		GerentePersistencia.persistir();
	}

	public Livro deleteLivro(int id) {
		Livro livroRemovido;
		for (Livro livro : GerentePersistencia.getInstance().getListaLivro()) {
			if (livro.getId() == id) {
				GerentePersistencia.getInstance().getListaLivro().remove(livro);
				GerentePersistencia.persistir();
				livroRemovido = livro;
				return livroRemovido;
			}
		}
		throw new Excecao("Livro Não Encontrado ");

	}
	
	public boolean consultarSituacaoDeLivro(int id){
		for(Livro livro: GerentePersistencia.getInstance().getListaLivro()){
			if(livro.getSituacao()== livro.getSituacao().DISPONIVEL)
				return true;
		}throw new Excecao("Livro Ocupado");
	}

	public Livro consultarLivro(int id) {
		for (Livro livro : GerentePersistencia.getInstance().getListaLivro()) {
			if (livro.getId() == id);
			return livro;
		}
		throw new Excecao("Nao existe Livro Cadastrado Com essa Editora!");
	}

	public List<Livro> getListLivro() {
		return GerentePersistencia.getInstance().getListaLivro();
	}

	private void isExisteLivro(int id) {
		for (Livro livro : GerentePersistencia.getInstance().getListaLivro()) {
			if (livro.getId() == id)
				throw new Excecao("Livro já existente");
		}
	}
	
	private boolean validadorCamposPreenchidos(Livro livro) {
		if ((livro.getId() == 0) || (livro.getSituacao() == null))
			throw new Excecao("Campos obrigatórios não preenchidos");
		return true;
	}
	
}
