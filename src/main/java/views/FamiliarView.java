package views;

import beans.BuscaBean;
import beans.cadastros.FamiliarBean;
import entities.Familiar;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import utils.JSFUtils;

@ManagedBean
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
