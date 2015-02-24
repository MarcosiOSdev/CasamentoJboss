package br.com.mrcsfelipe.casamento.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="usuario", schema="casamento")
@NamedQueries({
	@NamedQuery(name="Usuario.verificarOauth", query="SELECT u FROM Usuario u WHERE u.oauth=:oauth"),
	@NamedQuery(name="Usuario.verificarLogin", query="SELECT u FROM Usuario u WHERE u.login=:login"),
	@NamedQuery(name="Usuario.trazerCasal", query="SELECT u.casal FROM Usuario u WHERE u.login=:login"),
})
public class Usuario implements Serializable{

	public final static String VERIFICAR_OAUTH = "Usuario.verificarOauth";
	public final static String VERIFICAR_LOGIN = "Usuario.verificarLogin";
	public final static String TRAZER_CASAL = "Usuario.trazerCasal";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario")
	private Integer idUsuario;
	
	@Column(nullable=false, unique=true)
	private String login;
	
	@Column(nullable=false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Permissoes permissao;
	
	private String oauth;
	
	@OneToOne
	@JoinColumn(name="casal")
	private Usuario casal;
	
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
	private Collection<Convidados> convidado;
	
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Permissoes getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissoes permissao) {
		this.permissao = permissao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getOauth() {
		return oauth;
	}

	public void setOauth(String oauth) {
		this.oauth = oauth;
	}


	public Usuario getCasal() {
		return casal;
	}

	public void setCasal(Usuario casal) {
		this.casal = casal;
	}
	
	


	public Collection<Convidados> getConvidado() {
		return convidado;
	}

	public void setConvidado(Collection<Convidados> convidado) {
		this.convidado = convidado;
	}

	
	
	
}
