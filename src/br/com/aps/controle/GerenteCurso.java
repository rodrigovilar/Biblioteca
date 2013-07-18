package br.com.aps.controle;
import java.util.ArrayList;
import java.util.List;
import br.com.aps.entidade.Curso;
import br.com.aps.excecao.Excecao;

public class GerenteCurso {

	List<Curso> listaCursos= new ArrayList<Curso>();

	public void addCursos(Curso curso) {
		if ((curso.getNome() == null) || (curso.getCodigo() == null)){
			throw new Excecao("Campos obrigatórios não preenchidos");
		}
		isExisteCurso(curso.getCodigo());
		listaCursos.add(curso);
	}

	public void deletarCurso(Curso curso) {
		retornarCurso(curso.getCodigo());
		listaCursos.remove(curso);
	}

	public List<Curso> getListCurso() {
		return listaCursos;
	}

	public Curso retornarCurso(String cod) {
		for (Curso curso : listaCursos) {
			if (curso.getCodigo() == cod)
				return curso;
		}
		throw new Excecao("Nao existe Curso cadastrado com este Codigo");
	}
	
	private void isExisteCurso(String codigoCurso){
		for(Curso curso: listaCursos){
			if(curso.getCodigo().equals(codigoCurso))
				throw new Excecao("Curso já existente"); 
		}
	}
	
	
	

}