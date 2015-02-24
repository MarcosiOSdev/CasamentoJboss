package br.com.mrcsfelipe.casamento.managed;
 
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class AbstractMB {
    private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
    private static final String PACKAGE_MESSAGES_LANGUAGE= "com.marcosFelipe.language";
    public AbstractMB() {
        super();
    }
    
    public static String messageBundle(String property) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
		return bundle.getString(property);
	}
    public static String messageBundle(String property, Object...parameters) {
		String mensagem = messageBundle(property);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parameters);
	}
    public static String messageBundle(Locale locale, String property) {
		ResourceBundle bundle = ResourceBundle.getBundle(AbstractMB.PACKAGE_MESSAGES_LANGUAGE, locale);
		return bundle.getString(property);
	}

	public static String messageBundle(Locale locale, String property, Object... parameters) {
		String message = messageBundle(locale, property);
		MessageFormat formatter = new MessageFormat(message);
		return formatter.format(parameters);
	}
 
    protected void menssagemErro(String message) {
        JSFMessageUtil messageUtil = new JSFMessageUtil();
        messageUtil.sendError(message);
    }
 
    protected void menssagemInfo(String message) {
        JSFMessageUtil messageUtil = new JSFMessageUtil();
        messageUtil.sendInfo(message);
    }
    protected void menssagemGrave(String message){
    	JSFMessageUtil messageUtil = new JSFMessageUtil();
    	messageUtil.deleteAdmin(message);
    }
 
    protected void closeDialog(){
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
    }
 
    protected void keepDialogOpen(){
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
    }
 
    protected RequestContext getRequestContext(){
        return RequestContext.getCurrentInstance();
    }
}