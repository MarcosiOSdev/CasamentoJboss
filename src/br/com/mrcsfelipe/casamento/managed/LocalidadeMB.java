package br.com.mrcsfelipe.casamento.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.mrcsfelipe.casamento.business.LocalidadeBusiness;
import br.com.mrcsfelipe.casamento.model.Localidade;

@ManagedBean
@RequestScoped
public class LocalidadeMB extends AbstractMB implements Serializable{

	private Localidade localidade;
	private List<Localidade> localidades = new ArrayList<Localidade>();
	
	@Inject
	private LocalidadeBusiness localidadeBusiness;
	
	public LocalidadeMB() {
		localidade = new Localidade();
	}
	
	public void cadastrar(){
		try {
			System.out.println(localidade.getNomeLocalidade());
			localidadeBusiness.salvarLocalidade(localidade);
			localidade = new Localidade();
			
			menssagemInfo("Salvo com Sucesso !");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void excluir(Localidade localidade){
		try {
			System.out.println(localidade);
			localidadeBusiness.removerLocalidade(localidade);
			localidade = new Localidade();
			
			menssagemInfo("Excluido com Sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public List<Localidade> getLocalidades() {		
		localidades = localidadeBusiness.trazerTodos();		
		return localidades;
	}
	
	
	
}
