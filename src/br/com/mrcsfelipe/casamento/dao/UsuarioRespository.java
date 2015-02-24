package br.com.mrcsfelipe.casamento.dao;

import javax.ejb.Local;

import br.com.mrcsfelipe.casamento.model.Usuario;

@Local
public interface UsuarioRespository extends RepositoryDAO<Usuario, Integer>{
	
	public Usuario autenticar(Usuario u);
	public Usuario buscandoUsuarioPorOauth(String oauth);
	public Usuario buscandoUsuarioPorLogin(String login);
	public Usuario buscandoCasalDoUsuario(String login);
}
