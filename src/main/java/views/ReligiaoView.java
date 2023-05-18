package views;

import entities.Religiao;
import java.io.Serializable;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@ViewScoped
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
