package br.com.mrcsfelipe.casamento.business;

import java.util.List;

import javax.ejb.EJB;

import br.com.mrcsfelipe.casamento.dao.LocalidadeRepository;
import br.com.mrcsfelipe.casamento.model.Localidade;


public class LocalidadeBusiness {

	@EJB
	private LocalidadeRepository localidadeRepository;
	
	private UsuarioBusiness usuarioBusiness; 
	
	public LocalidadeBusiness() {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean todosDados(Localidade local){
		
		if(local.getIdLocalidade() == null) 
			return false;
		else if(local.getNomeLocalidade().trim().equals(""))
			return false;
		else if(local.getOauth().trim().equals(""))
			return false;
		else 
			return true;
	}
	
	public Localidade salvarLocalidade(Localidade local) throws Exception{
		
		localidadeRepository.save(local);
		
		return null;
//		if(todosDados(local)){
//			localidadeRepository.save(local);
//			return local;
//		} else {
//			return null;
//		}
	}
	public void removerLocalidade(Localidade local){
		localidadeRepository.delete(local.getIdLocalidade(), Localidade.class);
	}
	
	public Localidade trazerPeloId(Integer id){ 
		return localidadeRepository.findByID(id);
	}
	public List<Localidade> trazerTodos(){
		return localidadeRepository.findAll();
	}
	
	
}
