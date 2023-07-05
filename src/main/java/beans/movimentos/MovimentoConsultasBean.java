package beans.movimentos;

import beans.EntityManager;
import beans.filtros.FiltrosTratamento;
import beans.services.ConsultaService;
import beans.services.TratamentoService;
import entities.Consulta;
import entities.Paciente;
import entities.Profissional;
import entities.Tratamento;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import utils.JSFUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class MovimentoConsultasBean implements Serializable {

    @EJB
    EntityManager entityManager;

    @Inject
    TratamentoService tratamentoService;
	
    @Inject
    ConsultaService consultaService;

    FiltrosTratamento filtrosTratamento = new FiltrosTratamento();

    public void processarTratamentos() {
        List<Tratamento> tratamentos = tratamentoService.retornaRegistrosFiltrados(filtrosTratamento);
        if (tratamentos.isEmpty()) {
            JSFUtils.mensagemAviso("Nenhum tratamento encontrado com os filtros selecionados para processar!");
            return;
        }

        for (Tratamento tratamento : tratamentos) {
            gerarConsultas(tratamento);
        }

        JSFUtils.mensagemSucesso("Processamento dos tratamentos concluído com sucesso!");
    }

    private void gerarConsultas(Tratamento tratamento) {
        Calendar dataLimiteConsultas = Calendar.getInstance();
        dataLimiteConsultas.setTime(getDataLimiteConsultas(tratamento));

        Calendar dataConsulta = Calendar.getInstance();
        dataConsulta.setTime(tratamento.getInicioVigencia());

        Calendar horarioConsulta = Calendar.getInstance();
        horarioConsulta.setTime(tratamento.getHorarioSessao());

        Profissional profissionalTratamento = tratamento.getProfissional();
        Paciente pacienteTratamento = tratamento.getPaciente();

        final int horaConsulta = horarioConsulta.get(Calendar.HOUR_OF_DAY);
        final int minutoConsulta = horarioConsulta.get(Calendar.MINUTE);

        final int duracaoConsulta = tratamento.getDuracaoMinutosSessao();

        while (dataConsulta.compareTo(dataLimiteConsultas) <= 0) {
            Calendar inicioConsulta = Calendar.getInstance();
            inicioConsulta.setTime(dataConsulta.getTime());
            inicioConsulta.set(Calendar.HOUR_OF_DAY, horaConsulta);
            inicioConsulta.set(Calendar.MINUTE, minutoConsulta);

            Calendar finalConsulta = Calendar.getInstance();
            finalConsulta.setTime(inicioConsulta.getTime());
            finalConsulta.add(Calendar.MINUTE, duracaoConsulta);

            boolean horarioDisponivel = consultaService.verificaHorarioConsultaDisponivel(
                    dataConsulta.getTime(), inicioConsulta.getTime(), finalConsulta.getTime(),
                    profissionalTratamento, pacienteTratamento);

            if (horarioDisponivel) {
                Consulta consulta = new Consulta();
                consulta.setTratamento(tratamento);
                consulta.setData(dataConsulta.getTime());
                consulta.setHorarioInicio(inicioConsulta.getTime());
                consulta.setHorarioTermino(finalConsulta.getTime());
                consulta.setPaga(false);
                consulta.setCancelada(false);

                entityManager.salvar(consulta);
            }

            dataConsulta.add(Calendar.DAY_OF_MONTH, tratamento.getFrequenciaSessaoDias());
        }
    }

    private Date getDataLimiteConsultas(Tratamento tratamento) {
        if (tratamento.getFinalVigencia() == null) {
            return filtrosTratamento.getPeriodoFinal();
        }

        if (tratamento.getFinalVigencia().before(filtrosTratamento.getPeriodoFinal())) {
            return tratamento.getFinalVigencia();
        } else {
            return filtrosTratamento.getPeriodoFinal();
        }
    }

    public FiltrosTratamento getFiltrosTratamento() {
        return filtrosTratamento;
    }

    public void setFiltrosTratamento(FiltrosTratamento filtrosTratamento) {
        this.filtrosTratamento = filtrosTratamento;
    }
}
