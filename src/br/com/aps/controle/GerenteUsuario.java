package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;
import br.com.aps.entidade.Usuario;
import br.com.aps.excecao.Excecao;

public class GerenteUsuario {

	List<Usuario> listaUsuario = new ArrayList<Usuario>();
	Usuario usuarioLogado = null;
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void login(String cpf, String senha) {
		for (Usuario p : listaUsuario) {
				if (p.getLogin().equals(cpf) && (p.getSenha().equals(senha)))
						usuarioLogado = p;
				return;
			}
		throw new Excecao("O usuário não tem cadastro");
	}

	public void addUsuario(Usuario usuario){
		listaUsuario.add(usuario);
	}
	
	public void deleteUsuario(Usuario usuario){
		retornarUsuario(usuario.getLogin());
		listaUsuario.remove(usuario);	
	}
	
	public Usuario retornarUsuario(String cpf) {
		for(Usuario usuario: listaUsuario){
			if(usuario.getLogin().equals(cpf));
				return usuario;
		}
		throw new Excecao("Não existe usuário com este cpf");
	}
	
	public List<Usuario> getListUsuario(){
		return listaUsuario;
	}
	

	
		
	
	
	
	
}
