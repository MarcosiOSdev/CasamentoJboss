package br.com.mrcsfelipe.casamento.dto;

import java.io.Serializable;

public class ConvidadosCasal implements Serializable{
	
	private String nome;
	private String nomeLocal;
	private String login;
	
	public ConvidadosCasal() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nomeLocal == null) ? 0 : nomeLocal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConvidadosCasal other = (ConvidadosCasal) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeLocal == null) {
			if (other.nomeLocal != null)
				return false;
		} else if (!nomeLocal.equals(other.nomeLocal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConvidadosCasal [nome=" + nome + ", nomeLocal=" + nomeLocal
				+ ", login=" + login + "]";
	}
	

}
