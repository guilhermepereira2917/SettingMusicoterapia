package beans.buscas;

import beans.GenericBuscaBean;
import beans.filtros.FiltrosProfissional;
import beans.services.ProfissionalService;
import entities.Profissional;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@ViewScoped
public class BuscaProfissionalBean extends GenericBuscaBean<Profissional, ProfissionalService, FiltrosProfissional> {

    @Override
    protected void iniciaFiltros() {
        this.filtros = new FiltrosProfissional();
    }

    public List<Profissional> getProfissionais() {
        return this.registros;
    }

    public void setProfissionais(List<Profissional> profissionais) {
        this.registros = profissionais;
    }

    public FiltrosProfissional getFiltrosProfissional() {
        return this.filtros;
    }

    public void setFiltrosProfissional(FiltrosProfissional filtrosProfissional) {
        this.filtros = filtrosProfissional;
    }

}
