package br.com.mrcsfelipe.casamento.business;

import java.util.List;

import javax.ejb.EJB;

import br.com.mrcsfelipe.casamento.dao.ConvidadosRepository;
import br.com.mrcsfelipe.casamento.model.Convidados;


public class ConvidadosBusiness {

	@EJB
	private ConvidadosRepository repository;
	
	
	public ConvidadosBusiness() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void salvar(Convidados convidado){
		try {
			repository.save(convidado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exclui(Convidados convidados){
		repository.delete(convidados.getIdConvidado(), Convidados.class);
	}
	
	public List<Convidados> buscarTudo(){
		return repository.findAll();
	}
	
	public List<Convidados> buscarTodosDoCasal(String login){
		return repository.buscarTodosDoUsuario(login);
	}
	
	public List<String> buscarTodosConvidadosDoCasal(String login){
		return repository.buscarTodosConvidadosDoCasal(login);
	}
	
}
