package br.com.mrcsfelipe.casamento.dao;

import java.util.List;

import br.com.mrcsfelipe.casamento.dto.ConvidadosCasal;
import br.com.mrcsfelipe.casamento.model.Convidados;

public interface ConvidadosRepository extends RepositoryDAO<Convidados, Integer>{
	
	public abstract List<Convidados> buscarTodosDoUsuario(String login);
	public abstract List<String> buscarTodosConvidadosDoCasal(String login);

}
