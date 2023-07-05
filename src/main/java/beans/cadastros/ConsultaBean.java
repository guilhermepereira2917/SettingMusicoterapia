package beans.cadastros;

import beans.GenericBean;
import beans.services.ConsultaService;
import entities.Consulta;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import utils.JSFUtils;

@Named
@ViewScoped
public class ConsultaBean extends GenericBean<Consulta> {

    @Inject
    ConsultaService consultaService;

    @Override
    public void novo() {
        objetoCrud = new Consulta();
    }

    @Override
    public boolean validar(Consulta consulta) {
        if (consulta.getHorarioTermino().compareTo(consulta.getHorarioInicio()) <= 0) {
            JSFUtils.mensagemErro("Horário de término da consulta deve ser maior que horário inicial da consulta!");
            return false;
        }

        if (consulta.getPaga() && consulta.getCancelada()) {
            JSFUtils.mensagemErro("Consulta não pode estar cancelada e paga ao mesmo tempo!");
            return false;
        }

        boolean horarioDisponivel = consultaService.verificaHorarioConsultaDisponivel(
                consulta.getData(), consulta.getHorarioInicio(), consulta.getHorarioTermino(),
                consulta.getTratamento().getProfissional(),  consulta.getTratamento().getPaciente(), consulta.getId());
        if (!horarioDisponivel) {
            JSFUtils.mensagemErro("O horário selecionado para a consulta não está disponível para este profissional ou paciente!");
            return false;
        }

        return true;
    }

    @Override
    public void setObjetoCrudPesquisa() {
        objetoCrud = buscaBean.getResultadoPesquisa(Consulta.class);
    }

    public Consulta getConsulta() {
        return objetoCrud;
    }

    public void setConsulta(Consulta consulta) {
        objetoCrud = consulta;
    }

}
