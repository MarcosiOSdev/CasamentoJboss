package br.com.mrcsfelipe.casamento.dao;

import java.io.Serializable;
import java.util.List;


public interface RepositoryDAO <T, ID extends Serializable> {
	
	public abstract void save(T obj) throws Exception;
	public abstract T update(T obj);
	public abstract void delete(Object id, Class<T> classe);
	public abstract T findByID(ID id);
	public abstract List<T> findAll();
	public abstract List<T> findAllPag(long begin, long end);

}
