package br.com.mrcsfelipe.casamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<T, ID extends Serializable> implements
		RepositoryDAO<T, ID> {

	private final static String UNIT_NAME = "CasamentoPU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	private Class<T> entityClass;

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(T entity) {
		em.persist(entity);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Object id, Class<T> classe) {
		T entityToBeRemoved = em.getReference(classe, id);
		em.remove(entityToBeRemoved);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T update(T entity) {
		em.getTransaction().begin();
		
		entity = em.merge(entity);
		
		em.getTransaction().commit();
		return entity;
	}

	public T findByID(ID id) {
		return em.find(entityClass, id);
	}

	// Using the unchecked because JPA does not have a
	// em.getCriteriaBuilder().createQuery()<T> method
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAllPag(long begin, long end) {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).setFirstResult((int) begin).setMaxResults((int) end).getResultList();
	}
	
	

}
