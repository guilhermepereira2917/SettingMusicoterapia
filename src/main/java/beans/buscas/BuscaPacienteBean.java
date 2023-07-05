package beans.buscas;

import beans.GenericBuscaBean;
import beans.filtros.FiltrosPaciente;
import beans.services.PacienteService;
import entities.Paciente;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@ViewScoped
public class BuscaPacienteBean extends GenericBuscaBean<Paciente, PacienteService, FiltrosPaciente> {

    @Override
    protected void iniciaFiltros() {
        this.filtros = new FiltrosPaciente();
    }

    public List<Paciente> getPacientes() {
        return this.registros;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.registros = pacientes;
    }

    public FiltrosPaciente getFiltrosPaciente() {
        return this.filtros;
    }

    public void setFiltrosPaciente(FiltrosPaciente filtrosPaciente) {
        this.filtros = filtrosPaciente;
    }

}
