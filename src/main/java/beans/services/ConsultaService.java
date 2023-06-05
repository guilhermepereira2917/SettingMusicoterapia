package beans.services;

import beans.GenericService;
import beans.filtros.FiltrosConsulta;
import entities.Consulta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class ConsultaService extends GenericService<Consulta, FiltrosConsulta> {
    @Override
    protected String getPrefixo() {
        return "c";
    }

    @Override
    protected String getSelect() {
        return "select c from Consulta c join c.tratamento t join t.paciente p";
    }
}
