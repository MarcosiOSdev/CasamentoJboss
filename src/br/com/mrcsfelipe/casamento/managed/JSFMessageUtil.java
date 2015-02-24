package br.com.mrcsfelipe.casamento.managed;
 
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
 
public class JSFMessageUtil {
	
	public void deleteAdmin(String message){
		@SuppressWarnings("unused")
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_ERROR, message);
		
	}
	
	
    public void sendInfo(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
        addMessageToJsfContext(facesMessage);
    }
 
    public void sendError(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, message);
        addMessageToJsfContext(facesMessage);
    }
 
    private FacesMessage createMessage(Severity severity, String mensagemErro) {
        return new FacesMessage(severity, mensagemErro, mensagemErro);
    }
 
    private void addMessageToJsfContext(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}