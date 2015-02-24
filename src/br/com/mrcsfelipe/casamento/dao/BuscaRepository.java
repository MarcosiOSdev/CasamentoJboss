package br.com.mrcsfelipe.casamento.dao;

import java.util.List;

import br.com.mrcsfelipe.casamento.model.Busca;

public interface BuscaRepository extends RepositoryDAO<Busca, String>{

	public  List<Busca> autoComplete(String sql);
	
}
