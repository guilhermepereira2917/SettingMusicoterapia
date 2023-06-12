package beans.services;

import beans.GenericService;
import beans.filtros.FiltrosPaciente;
import entities.Paciente;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class PacienteService extends GenericService<Paciente, FiltrosPaciente> {

    @Override
    protected String getSelect() {
        return "select p from Paciente p";
    }
}
