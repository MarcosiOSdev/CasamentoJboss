package br.com.mrcsfelipe.casamento.managed;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.mrcsfelipe.casamento.business.UsuarioBusiness;
import br.com.mrcsfelipe.casamento.model.Permissoes;
import br.com.mrcsfelipe.casamento.model.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioMB {

	private Usuario usuario;
	
	@Inject
	UsuarioBusiness usuarioBusiness;
	
	public UsuarioMB() {
		usuario = new Usuario();
	}
	
	
	public void cadastrar(){
		
		try {
			usuario.setPermissao(Permissoes.USUARIO);
			
			usuarioBusiness.salvarUsuario(usuario);
			
			usuario = new Usuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
