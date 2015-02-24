package br.com.mrcsfelipe.casamento.dao;

import javax.ejb.Local;

import br.com.mrcsfelipe.casamento.model.Localidade;

@Local
public interface LocalidadeRepository extends RepositoryDAO<Localidade, Integer>{

	
}
