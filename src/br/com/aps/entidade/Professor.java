package br.com.aps.entidade;


public class Professor extends Pessoa {
	
	

	private Departamento departamento;
	

	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = new Departamento(departamento);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
			
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Professor other = (Professor) obj;
		if (departamento == null) {
		if (other.departamento!= null)
			return false;
		}else if (!departamento.equals(other.departamento))
			return false;

		return true;
	}
}
