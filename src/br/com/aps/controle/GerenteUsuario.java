package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Professor;
import br.com.aps.entidade.Usuario;
import br.com.aps.excecao.Excecao;

public class GerenteUsuario {

	List<Usuario> listaUsuario = new ArrayList<Usuario>();
	Usuario usuarioLogado = null;
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public boolean login(String cpf, String senha) {
		for (Usuario usuario: GerentePersistencia.getInstance().getListaUsuario()) {
				if (usuario.getLogin().equals(cpf) && (usuario.getSenha().equals(senha)))
						usuarioLogado = usuario;
				return true;
			}
		throw new Excecao("O usuário não tem cadastro");
	}

	public void addUsuario(Usuario usuario){
		isExisteUsuario(usuario.getCpf());
		if(((usuario.getSetor()==null) || (usuario.getLogin()== null) 
			 || (usuario.getSenha()==null))){
			throw new Excecao("Campos obrigatórios não preenchidos");
		}
		GerentePersistencia.getInstance().getListaUsuario().add(usuario);
		GerentePersistencia.persistir();
	}
	
	public Usuario deleteUsuario(String cpf){
		Usuario usuarioRemovido;
		for (Usuario usuario: GerentePersistencia.getInstance().getListaUsuario()){
			if (usuario.getCpf().equals(cpf)) {
				GerentePersistencia.getInstance().getListaUsuario().remove(usuario);
				GerentePersistencia.persistir();
				usuarioRemovido = usuario;
				return usuarioRemovido;
			}
		}
		throw new Excecao("Usuário não existente");
	}	
	
	public Usuario consultarUsuario(String cpf) {
		for (Usuario usuario: GerentePersistencia.getInstance().getListaUsuario()) {
			if(usuario.getCpf().equals(cpf));
				return usuario;
		}
		throw new Excecao("Não existe usuário com este cpf");
	}
	
	public List<Usuario> getListUsuario(){
		return GerentePersistencia.getInstance().getListaUsuario();
	}
	
	private void isExisteUsuario(String cpfUsuario) {
		for (Usuario usuario: GerentePersistencia.getInstance().getListaUsuario()) {
			if (usuario.getCpf().equals(cpfUsuario))
				throw new Excecao("Usuário já existente");
		}
	}
}
