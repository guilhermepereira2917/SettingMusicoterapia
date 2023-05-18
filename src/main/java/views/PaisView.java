package views;

import entities.Pais;
import java.io.Serializable;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@ViewScoped
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
