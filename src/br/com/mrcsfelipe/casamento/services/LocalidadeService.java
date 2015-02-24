package br.com.mrcsfelipe.casamento.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.mrcsfelipe.casamento.business.LocalidadeBusiness;
import br.com.mrcsfelipe.casamento.model.Localidade;

@Path("/localidade")
public class LocalidadeService {

	
	@Inject
	private LocalidadeBusiness localidadeBusiness;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public String salvarLocalidade(Localidade local){
		
		
		System.out.print(local.getNomeLocalidade());
		System.out.println(local.getOauth());
		
		
		return "ok";
		
		//Localidade l = localidadeBusiness.salvarLocalidade(local);
//		if(l != null){
//			return Response.status(201).entity(local).build();
//		}
//		return Response.status(202).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Localidade trazerLocalidade(){
		Localidade l = new Localidade();
		l.setIdLocalidade(1);
		l.setNomeLocalidade("RUA");
		l.setOauth("12314akdasjdkals");
		
		l = localidadeBusiness.trazerPeloId(1);
		
		return l;
	}
	
}
