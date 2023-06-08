package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public abstract class JSFUtils {

    public static void mensagemErro(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void mensagemAviso(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public static void mensagemSucesso(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void redirecionarPagina(String pagina) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (IOException ex) {
            mensagemErro("Erro ao redirecionar para página " + pagina + ". " + ex.getMessage());
        }
    }

    public static void abreDialog(String dialog) {
        abreDialog(dialog, null);
    }

    public static void abreDialog(String dialog, Map<String, List<String>> parametros) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("resizable", true);
        options.put("width", "90vw");
        options.put("contentWidth", "100%");

        PrimeFaces.current().dialog().openDynamic(dialog, options, parametros);
    }

    public static String getParam(String param) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("multiplaSelecao");
    }
    
}
