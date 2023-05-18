package views;

import entities.Cidade;
import java.io.Serializable;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@ViewScoped
public class CidadeView extends View<Cidade> implements Serializable {

    public CidadeView() {
        super(Cidade.class);
    }
    
    @Override
    protected boolean validar(Cidade cidade) {
        if (cidade.getNome().isBlank()) {
            JSFUtils.mensagemErro("Nome da Cidade deve ser preenchido!");
            return false;
        }

        if (cidade.getEstado() == null) {
            JSFUtils.mensagemErro("UF da Cidade deve ser preenchido!");
            return false;
        }
        
        return true;
    }
}
