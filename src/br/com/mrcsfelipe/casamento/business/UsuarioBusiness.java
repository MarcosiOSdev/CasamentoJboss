package br.com.mrcsfelipe.casamento.business;

import javax.ejb.EJB;

import br.com.mrcsfelipe.casamento.dao.UsuarioRespository;
import br.com.mrcsfelipe.casamento.model.Usuario;

public class UsuarioBusiness {

	@EJB
	private UsuarioRespository usuarioRespository;
	
	public UsuarioBusiness() {
		
	}
	
	public String autenticaUsuario(Usuario u){
		Usuario usuario = this.usuarioRespository.autenticar(u);
		if(usuario != null){
			return usuario.getOauth();
		} else {
			return "";
		}
	}
	
	public Usuario buscarUsuarioPorOauth(String oauth){
		return usuarioRespository.buscandoUsuarioPorOauth(oauth);
	}
	
	public Usuario buscarUsuarioPorLogin(String login){
		return usuarioRespository.buscandoUsuarioPorLogin(login);
	}
	
	public void salvarUsuario(Usuario usuario) throws Exception{
		usuarioRespository.save(usuario);
	}
	
	public Usuario buscarCasalDoUsuario(Usuario u){
		return usuarioRespository.buscandoCasalDoUsuario(u.getLogin());
	}
	
	
	
}
