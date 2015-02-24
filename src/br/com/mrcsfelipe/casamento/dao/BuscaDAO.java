package br.com.mrcsfelipe.casamento.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.mrcsfelipe.casamento.model.Busca;

@Stateless
public class BuscaDAO extends GenericDAO<Busca, String> implements BuscaRepository{

	public BuscaDAO(){
		super(Busca.class);
	}

	@Override
	public List<Busca> autoComplete(String sql) {
		
		Query query = em.createQuery("from Busca p where p.nome like :name");
        query.setParameter("name", "%" + sql + "%");
        return query.getResultList();
		
	 
	}

}
