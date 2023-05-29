package beans.views;

import entities.Pais;
import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@SessionScoped
public class PaisView extends View<Pais> implements Serializable {

    public PaisView() {
        super(Pais.class);
    }
    
    @Override
    protected boolean validar(Pais pais) {
        if (pais.getNome().isBlank()) {
            JSFUtils.mensagemErro("Nome do País deve ser preenchido!");
            return false;
        }
        
        return true;
    }   

}
