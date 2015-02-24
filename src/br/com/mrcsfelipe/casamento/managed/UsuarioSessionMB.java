package br.com.mrcsfelipe.casamento.managed;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.mrcsfelipe.casamento.business.UsuarioBusiness;
import br.com.mrcsfelipe.casamento.model.Usuario;

@SessionScoped
@ManagedBean(name="usuarioSessionMB")
public class UsuarioSessionMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	 
	@Inject
    private UsuarioBusiness usuarioBusiness;
 
    public Usuario getUsuario(){
        if(usuario == null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String login = context.getUserPrincipal().getName();
            usuario = usuarioBusiness.buscarUsuarioPorLogin(login);
        }
 
        return usuario;
    }
    
    public boolean usuarioLogado(){
    	 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
   
         if(context.getUserPrincipal().getName().trim().equals("") || context.getUserPrincipal().getName() == null){
        	 return false;
         } else {
        	 return true;
         }
    }
 
    public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public boolean isUserAdmin(){ //Usuario é  admin
        return getRequest().isUserInRole("ADMIN");
    }
    public boolean isUserUser(){ //Usuario é usuario
        return getRequest().isUserInRole("USUARIO");
    }
 
    public String logOut(){ // Sair do sistema
        getRequest().getSession().invalidate();
        return "logout";
    }
 
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }



	
	
}
