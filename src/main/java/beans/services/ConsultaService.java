package beans.services;

import beans.GenericService;
import beans.filtros.FiltrosConsulta;
import entities.Consulta;
import entities.Paciente;
import entities.Profissional;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class ConsultaService extends GenericService<Consulta, FiltrosConsulta> {

    @Override
    protected String getSelect() {
        return "select c from Consulta c join c.tratamento t join t.paciente p join t.profissional r";
    }

    public boolean verificaHorarioConsultaDisponivel(Date dataConsulta, Date inicioConsulta, Date finalConsulta, Profissional profissional, Paciente paciente) {
        return verificaHorarioConsultaDisponivel(dataConsulta, inicioConsulta, finalConsulta, profissional, paciente, null);
    }

    public boolean verificaHorarioConsultaDisponivel(Date dataConsulta, Date inicioConsulta, Date finalConsulta, Profissional profissional, Paciente paciente, Integer codigoConsulta) {
        FiltrosConsulta filtrosConsulta = new FiltrosConsulta();
        filtrosConsulta.processarFiltrosConsultaDisponivel(dataConsulta, inicioConsulta, finalConsulta, profissional, paciente, codigoConsulta);

        String sql = getSelectComFiltros(filtrosConsulta);

        return entityManager.getEntityManager().createQuery(sql).getResultList().isEmpty();
    }
}
