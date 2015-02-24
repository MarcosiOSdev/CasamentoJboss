package br.com.mrcsfelipe.casamento.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.com.mrcsfelipe.casamento.model.Convidados;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConvidadosDAO extends GenericDAO<Convidados, Integer> implements ConvidadosRepository {

	public ConvidadosDAO(){
		super(Convidados.class);
	}

	@Override
	public List<Convidados> buscarTodosDoUsuario(String login) {
		return super.em.createNamedQuery(Convidados.TRAZER_TODOS_DO_USUARIO, Convidados.class)
				       .setParameter("login", login)
				       .getResultList();
	}

	@Override
	public List<String> buscarTodosConvidadosDoCasal(String login) {
		Query query = 
				super.em.createNativeQuery("select * from vw_convidados_casal where login=:login order by convidados");
		query.setParameter("login", login);
		return query.getResultList();
	}


	

}
