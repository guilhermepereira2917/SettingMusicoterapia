package beans.cadastros;

import beans.BuscaBean;
import beans.GenericBean;
import entities.Profissional;
import entities.Profissao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@ViewScoped
public class ProfissionalBean extends GenericBean<Profissional> {

    @Override
    public void novo() {
        objetoCrud = new Profissional();
    }

    @Override
    public void setObjetoCrudPesquisa() {
        objetoCrud = buscaBean.getResultadoPesquisa(Profissional.class);
    }

    @Override
    public boolean validar(Profissional profissional) {
        if (profissional.getProfissao() == null) {
            JSFUtils.mensagemErro("Informe a Profissão ocupada pelo Profissional!");
            return false;
        }

        return true;
    }

    public Profissional getProfissional() {
        return this.objetoCrud;
    }

    public void setProfissional(Profissional profissional) {
        this.objetoCrud = profissional;
    }

    public void selecionaProfissao() {
        objetoCrud.setProfissao(BuscaBean.getResultadoPesquisa(Profissao.class));
    }

    public void limpaProfissao() {
        objetoCrud.setProfissao(null);
    }

}
