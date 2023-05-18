package views;

import beans.BuscaBean;
import entities.Familiar;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@ViewScoped
public class FamiliarView extends View<Familiar> {
    
    public FamiliarView() {
        super(Familiar.class);
    }

    public void abrirCadastroFamiliares(Familiar familiar) {
        if (familiar != null) {
            BuscaBean.resultadoPesquisa(familiar);
        }
        
        JSFUtils.abreDialog("/cadastros/cadastroFamiliares.xhtml");
    }
}
