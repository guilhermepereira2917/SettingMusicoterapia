package beans.views;

import entities.Religiao;
import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@SessionScoped
public class ReligiaoView extends View<Religiao> implements Serializable {

    public ReligiaoView() {
        super(Religiao.class);
    }
    
    @Override
    protected boolean validar(Religiao religiao) {
        if (religiao.getNome().isBlank()) {
            JSFUtils.mensagemErro("Nome da Religião deve ser preenchida!");
            return false;
        }
        
        return true;
    }

}
