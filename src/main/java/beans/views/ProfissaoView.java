package beans.views;

import entities.Profissao;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@SessionScoped
public class ProfissaoView extends View<Profissao> implements Serializable {

    public ProfissaoView() {
        super(Profissao.class);
    }

    @Override
    protected boolean validar(Profissao profissao) {
        if (profissao.getNome().isBlank()) {
            JSFUtils.mensagemErro("Nome da Profissão deve ser preenchida!");
            return false;
        }
        
        return true;
    }   
    
}
