package br.com.mrcsfelipe.casamento.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.mrcsfelipe.casamento.model.Localidade;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LocalidadeDAO extends GenericDAO<Localidade, Integer> implements LocalidadeRepository {

	public LocalidadeDAO() {
		super(Localidade.class);
		 
	}

}
