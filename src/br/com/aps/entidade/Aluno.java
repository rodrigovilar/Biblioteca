package br.com.aps.entidade;

import java.io.Serializable;

public class Aluno extends Pessoa implements Serializable {

	public static final int QUANTIDADE_EMPRESTIMO = 3;
	private Curso curso;

	public Aluno() {
		this.curso = null;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;

		return true;
	}
}
