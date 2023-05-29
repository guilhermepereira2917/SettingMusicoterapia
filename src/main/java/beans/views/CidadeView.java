package beans.views;

import entities.Cidade;
import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@SessionScoped
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
