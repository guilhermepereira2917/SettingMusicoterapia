package beans.cadastros;

import beans.BuscaBean;
import beans.GenericBean;
import entities.Familiar;
import entities.Profissao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class FamiliarBean extends GenericBean<Familiar> {     
    
    @PostConstruct
    public void init() {
        objetoCrud = BuscaBean.getResultadoPesquisa(Familiar.class);
        
        if (objetoCrud == null) {
            objetoCrud = new Familiar();
        }
    }
    
    @Override
    public void novo() {
        objetoCrud = new Familiar();
    }
    
    public Familiar getFamiliar() {
        return this.objetoCrud;
    }
    
    public void setFamiliar(Familiar familiar) {
        this.objetoCrud = familiar;
    }   
    
    public void selecionaProfissao() {
        objetoCrud.setProfissao(BuscaBean.getResultadoPesquisa(Profissao.class));
    }
    
    public void limpaProfissao() {
        objetoCrud.setProfissao(null);
    }
    
}
