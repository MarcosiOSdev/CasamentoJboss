package br.com.mrcsfelipe.casamento.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

import br.com.mrcsfelipe.casamento.model.Usuario;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UsuarioDao extends GenericDAO<Usuario, Integer> implements UsuarioRespository{

	public UsuarioDao() {
		super(Usuario.class);
	}

	@Override
	public Usuario autenticar(Usuario u) {
		TypedQuery<Usuario> query = 
				super.em.createQuery("SELECT u FROM Usuario u where u.login=:param1 and u.senha=:param2", Usuario.class);
		query.setParameter("param1", u.getLogin());
		query.setParameter("param2", u.getSenha());
		
		
		return query.getSingleResult();
	}

	@Override
	public Usuario buscandoUsuarioPorOauth(String oauth) {
		return super.em.createNamedQuery(Usuario.VERIFICAR_OAUTH, Usuario.class)
						.setParameter("oauth", oauth)
						.getSingleResult();
	}

	@Override
	public Usuario buscandoUsuarioPorLogin(String login) {
		return super.em.createNamedQuery(Usuario.VERIFICAR_LOGIN, Usuario.class).setParameter("login", login).getSingleResult();
	}

	@Override
	public Usuario buscandoCasalDoUsuario(String login) {
		return super.em.createNamedQuery(Usuario.TRAZER_CASAL, Usuario.class).setParameter("login", login).getSingleResult();
	}

}
