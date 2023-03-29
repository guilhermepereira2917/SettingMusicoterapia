package views;

import entities.Profissao;
import java.io.Serializable;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import utils.JSFUtils;

@ManagedBean
@ViewScoped
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
