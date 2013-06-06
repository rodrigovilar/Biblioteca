package br.com.aps.entidade;


public class Funcionario extends Pessoa{

	public static final int QUANTIDADE_ACERVO_EMPRESTIMO = 5;
	private String setor;
	private Usuario usuario;
	
	
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (setor == null) {
		if (other.setor != null)
			return false;
		}else if (!setor.equals(other.setor))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
			}else if (!usuario.equals(other.usuario))
				return false;
		
		return true;
	}
	
	
	
}
