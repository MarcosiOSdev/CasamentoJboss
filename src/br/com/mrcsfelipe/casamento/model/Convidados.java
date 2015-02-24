package br.com.mrcsfelipe.casamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="convidados")
@NamedQueries({
	@NamedQuery(name="Convidados.buscarTodosUsuario", 
			    query=" select c from Convidados c " +
			          "join fetch c.localidade " +
			          "join fetch c.usuario " +
			          "where c.usuario.login=:login " +
			          "order by c.nome")
	
})
public class Convidados implements Serializable, Comparable<Convidados>{
	
	public static final String TRAZER_TODOS_DO_USUARIO="Convidados.buscarTodosUsuario";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_convidados")
	private Integer idConvidado;
	
	@Column(length=60, unique=true)
	private String nome;
	
	@Column(length=1)
	private char padrinho;
	
	@ManyToOne
	@JoinColumn(name="id_localidade")
	private Localidade localidade;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@javax.persistence.Transient
	private String oauth;
	
	public Convidados() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdConvidado() {
		return idConvidado;
	}

	public void setIdConvidado(Integer idConvidado) {
		this.idConvidado = idConvidado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getPadrinho() {
		return padrinho;
	}

	public void setPadrinho(char padrinho) {
		this.padrinho = padrinho;
	}
	
	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	public String getOauth() {
		return oauth;
	}

	public void setOauth(String oauth) {
		this.oauth = oauth;
	}

	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idConvidado == null) ? 0 : idConvidado.hashCode());
		result = prime * result
				+ ((localidade == null) ? 0 : localidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((oauth == null) ? 0 : oauth.hashCode());
		result = prime * result + padrinho;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Convidados other = (Convidados) obj;
		if (idConvidado == null) {
			if (other.idConvidado != null)
				return false;
		} else if (!idConvidado.equals(other.idConvidado))
			return false;
		if (localidade == null) {
			if (other.localidade != null)
				return false;
		} else if (!localidade.equals(other.localidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (oauth == null) {
			if (other.oauth != null)
				return false;
		} else if (!oauth.equals(other.oauth))
			return false;
		if (padrinho != other.padrinho)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Convidados [idConvidado=" + idConvidado + ", nome=" + nome
				+ ", padrinho=" + padrinho + ", localidade=" + localidade
				+ ", usuario=" + usuario + ", oauth=" + oauth + "]";
	}

	@Override
	public int compareTo(Convidados o) {
	    return this.getNome().compareToIgnoreCase(o.getNome());
	}
	

	
	
	
	

}
