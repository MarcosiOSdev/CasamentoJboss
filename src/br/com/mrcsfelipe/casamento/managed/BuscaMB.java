package br.com.mrcsfelipe.casamento.managed;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.mrcsfelipe.casamento.business.BuscaBusiness;
import br.com.mrcsfelipe.casamento.model.Busca;

@Named
public class BuscaMB {

	@Inject
	private BuscaBusiness buscaBusiness;
	
	public BuscaMB() {
		// TODO Auto-generated constructor stub
	}
	
	private String text1;
	
	
	public List<String> completeText(String query) {
		System.out.println(query);
		
        List<Busca> results = new ArrayList<Busca>();
        results = buscaBusiness.autoComplete(query); 
        
        List<String> resultComplete = new ArrayList<String>();
        for(Busca b : results){
        	resultComplete.add(b.getNome());
        	//System.out.println(b.getNome());
        }
 
        return resultComplete;
    }
	
	
	

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		System.out.println(text1);
		this.text1 = text1;
	}
	
	
	
	
}
