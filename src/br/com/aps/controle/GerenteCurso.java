package br.com.aps.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import br.com.aps.entidade.Curso;
import br.com.aps.excecao.Excecao;

public class GerenteCurso implements Serializable {

	public void addCursos(Curso curso) {
		if ((curso.getNome() == null) || (curso.getCodigo() == null)) {
			throw new Excecao("Campos obrigatórios não preenchidos");
		}
		isExisteCurso(curso.getCodigo());
		GerentePersistencia.getInstance().getListaCurso().add(curso);
		GerentePersistencia.persistir();
	}

	public Curso deleteCurso(Curso curso2) {
		Curso cursoRemovido;
		for (Curso curso : GerentePersistencia.getInstance().getListaCurso()) {
			if (curso.getCodigo().equals(curso2.getCodigo())) {
				GerentePersistencia.getInstance().getListaCurso().remove(curso);
				GerentePersistencia.persistir();
				cursoRemovido = curso;
				return cursoRemovido;

			}
		}
		throw new Excecao("Curso Não Encontrado ");

	}

	public Curso retornarCurso(String cod) {
		for (Curso curso : GerentePersistencia.getInstance().getListaCurso()) {
			if (curso.getCodigo().equals(cod));

			return curso;
		}
		throw new Excecao("Nao existe Curso cadastrado com este Codigo");
	}

	private void isExisteCurso(String codigoCurso) {
		for (Curso curso : GerentePersistencia.getInstance().getListaCurso()) {
			if (curso.getCodigo().equals(codigoCurso));

			throw new Excecao("Curso já existente");
		}
	}
	
	private Curso alteraDadosDoCurso(Curso curso){
		for (Curso c: GerentePersistencia.getInstance().getListaCurso()){
			if (c.getCodigo().equals(curso.getCodigo())){
			  c = curso;
			  GerentePersistencia.persistir();
			  return c;
			}
			
		}
		throw new Excecao("Não existe curso com este codigo ");
	}
	public List<Curso> getListCurso(){
		return GerentePersistencia.getInstance().getListaCurso();
	}

}