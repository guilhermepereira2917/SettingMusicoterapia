package beans.buscas;

import beans.GenericBuscaBean;
import beans.filtros.FiltrosConsulta;
import beans.services.ConsultaService;
import entities.Consulta;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@ViewScoped
public class BuscaConsultaBean extends GenericBuscaBean<Consulta, ConsultaService, FiltrosConsulta> {

    @Override
    protected void iniciaFiltros() {
        filtros = new FiltrosConsulta();
    }

    public List<Consulta> getConsultas() {
        return registros;
    }

    public void setConsultas(List<Consulta> consultas) {
        registros = consultas;
    }

    public FiltrosConsulta getFiltrosConsulta() {
        return filtros;
    }

    public void setFiltrosConsulta(FiltrosConsulta filtrosConsulta) {
        filtros = filtrosConsulta;
    }
}
