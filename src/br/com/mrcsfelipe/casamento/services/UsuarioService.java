package br.com.mrcsfelipe.casamento.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.mrcsfelipe.casamento.business.UsuarioBusiness;
import br.com.mrcsfelipe.casamento.model.Usuario;

@Path("/usuario")
public class UsuarioService {
	
	@Inject
	private UsuarioBusiness usuarioBusiness;
	
	
	public UsuarioService() {
		// TODO Auto-generated constructor stub
	}
	
//	@GET
//	@Consumes("application/json")
//	@Produces("application/json")
//	public Response autenticacao(){
//		String result = " OK ";
//		return Response.status(201).entity(result).build();
//	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response autenticacaoUsuario(Usuario usuario){
		
		String oauth = this.usuarioBusiness.autenticaUsuario(usuario);
		
		if(oauth.trim().equals("")){
			return Response.status(505).build();
		} else {
			usuario.setOauth(oauth);
			return Response.status(201).entity(usuario).build();
		}
		
		
		
	}
	
	
	
}
