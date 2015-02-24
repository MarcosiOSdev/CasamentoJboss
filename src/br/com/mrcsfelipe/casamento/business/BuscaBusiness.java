package br.com.mrcsfelipe.casamento.business;

import java.util.List;

import javax.ejb.EJB;

import br.com.mrcsfelipe.casamento.dao.BuscaRepository;
import br.com.mrcsfelipe.casamento.model.Busca;

public class BuscaBusiness {

	@EJB
	private BuscaRepository buscaRepository;
	
	public List<Busca> autoComplete(String sql){
		return buscaRepository.autoComplete(sql);
	}
	
	
}
