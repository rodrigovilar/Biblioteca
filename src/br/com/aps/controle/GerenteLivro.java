package br.com.aps.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Acervo;
import br.com.aps.entidade.Curso;
import br.com.aps.entidade.Livro;
import br.com.aps.excecao.Excecao;

public class GerenteLivro extends Acervo implements Serializable {

	public void addLivro(Livro livro) {
		if ((livro.getEditora() == null) || (livro.getTitulo() == null)) {
			throw new Excecao("Campos obrigatórios não preenchidos");
		}
		isExisteLivro(livro.getTitulo());
		GerentePersistencia.getInstance().getListaLivro().add(livro);
		GerentePersistencia.persistir();
	}

	public Livro deleteLivro(Livro livro2) {
		Livro livroRemovido;
		for (Livro livro : GerentePersistencia.getInstance().getListaLivro()) {
			if (livro.getEditora().equals(livro2.getEditora())) {
				GerentePersistencia.getInstance().getListaLivro().remove(livro);
				GerentePersistencia.persistir();
				livroRemovido = livro;
				return livroRemovido;

			}
		}
		throw new Excecao("Livro Não Encontrado ");

	}

	public Livro retornarLivro(String edt) {
		for (Livro livro : GerentePersistencia.getInstance().getListaLivro()) {
			if (livro.getEditora().equals(edt))
				;

			return livro;
		}
		throw new Excecao("Nao existe Livro Cadastrado Com essa Editora!");
	}

	private void isExisteLivro(String tituloLivro) {
		for (Livro livro : GerentePersistencia.getInstance().getListaLivro()) {
			if (livro.getTitulo().equals(tituloLivro));

			throw new Excecao("Livro já existente");
		}
	}

	public List<Livro> getListLivro() {
		return GerentePersistencia.getInstance().getListaLivro();
	}

}
