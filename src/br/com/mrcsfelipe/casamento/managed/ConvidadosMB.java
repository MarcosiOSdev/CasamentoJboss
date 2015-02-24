package br.com.mrcsfelipe.casamento.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.mrcsfelipe.casamento.business.ConvidadosBusiness;
import br.com.mrcsfelipe.casamento.business.LocalidadeBusiness;
import br.com.mrcsfelipe.casamento.business.UsuarioBusiness;
import br.com.mrcsfelipe.casamento.model.Convidados;
import br.com.mrcsfelipe.casamento.model.Localidade;
import br.com.mrcsfelipe.casamento.model.Usuario;

@ManagedBean(name="convidadosMB")
@RequestScoped
public class ConvidadosMB extends AbstractMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ConvidadosBusiness convidadosBusiness;
	
	@Inject
	private LocalidadeBusiness localidadeBusiness;
	
	@Inject
	private UsuarioBusiness usuarioBusiness;
	
	private Convidados convidados;
	private List<Localidade> localidades;
	private List<Convidados> lstMeusConvidados;
	private List<Convidados> lstConvidados;
	private String localidadeSelecionada;
	private Usuario usuario;
	
	private Integer qtdConvidados;
	private Integer qtdMeusConvidados;
	
	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	
	@PostConstruct
	public void init(){
		this.usuario = this.usuarioSessionMB.getUsuario();
		convidados = new Convidados();
		localidades = new ArrayList<Localidade>();
		lstConvidados = new ArrayList<Convidados>();
	}
	
	
	public void salvar(){
		try {
			
			
		
			Localidade localidade = localidadeBusiness.trazerPeloId(new Integer(localidadeSelecionada));
			
			convidados.setLocalidade(localidade);
			convidados.setUsuario(usuario);
			
			convidados.setNome(convidados.getNome().trim());
			
			convidadosBusiness.salvar(convidados);
			
			convidados = new Convidados();
			super.menssagemInfo("Convidado cadastro com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			super.menssagemErro("Convidado ja existe");
		}
	}
	
	public void deletar(Convidados convidados){
		try {
			System.out.println(convidados.getNome());
			convidadosBusiness.exclui(convidados);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

	public List<Convidados> getLstConvidados() {
		lstConvidados = convidadosBusiness.buscarTodosDoCasal(usuario.getLogin());
		lstConvidados.addAll(convidadosBusiness.buscarTodosDoCasal(usuarioBusiness.buscarCasalDoUsuario(usuario).getLogin()));
		
		return lstConvidados;
	}
	
	public List<Localidade> getLocalidades() {
		localidades = localidadeBusiness.trazerTodos();
		return localidades;
	}
	public Integer getQtdConvidados() {
		qtdConvidados = getLstConvidados().size();
		return qtdConvidados;
	}
	
	
	
	
	public List<Convidados> getLstMeusConvidados() {
		lstMeusConvidados = convidadosBusiness.buscarTodosDoCasal(usuario.getLogin());
		return lstMeusConvidados;
	}


	public Integer getQtdMeusConvidados() {
		return qtdMeusConvidados = getLstMeusConvidados().size();
	}


	/*********
	 * 
	 * GETTER e SETTER
	 */

	public Convidados getConvidados() {
		return convidados;
	}

	public void setConvidados(Convidados convidados) {
		this.convidados = convidados;
	}

	


	public String getLocalidadeSelecionada() {
		return localidadeSelecionada;
	}


	public void setLocalidadeSelecionada(String localidadeSelecionada) {
		this.localidadeSelecionada = localidadeSelecionada;
	}


	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}


	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}


	


	
	
	
}
