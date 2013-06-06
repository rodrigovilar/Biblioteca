package br.com.aps.entidade;


public class Tema {

	private String area;
	private String descricao;
	
	public Tema(String area, String descricao){
		this.setArea(area);
		this.setDescricao(descricao);
	}


	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tema other = (Tema) obj;
		if (area == null) {
		if (other.area != null)
			return false;
		}else if (!area.equals(other.area))
			return false;
		
		if (descricao == null) {
		if (other.descricao != null)
			return false;
		}else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}	
	
}
