package beans.services;

import beans.GenericService;
import beans.filtros.FiltrosProfissional;
import entities.Profissional;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class ProfissionalService extends GenericService<Profissional, FiltrosProfissional> {

    @Override
    protected String getPrefixo() {
        return "p";
    }

    @Override
    protected String getSelect() {
        return "select p from Profissional p";
    }
}
