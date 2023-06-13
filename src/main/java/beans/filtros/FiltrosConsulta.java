package beans.filtros;

import beans.FiltrosService;
import entities.Paciente;
import entities.Profissional;
import utils.DateUtils;

import java.util.Date;

public class FiltrosConsulta extends FiltrosService {

    private Integer codigo;
    private Date periodoInicial;
    private Date periodoFinal;

    private Character ordenacao;

    @Override
    protected void processarFiltros() {
        processaFiltroInteiro(codigo, "c.id");
        processaFiltroBetweenData(periodoInicial, periodoFinal, "c.data");

        adicionaOrdenacao(getCampoOrderBy());
    }

    public void processarFiltrosConsultaDisponivel(Date dataConsulta, Date inicioConsulta, Date finalConsulta, Profissional profissional, Paciente paciente, Integer codigoConsulta) {
        String dataFormatada = DateUtils.getDataFormatadaJPQL(dataConsulta);
        String horarioInicialFormatado = DateUtils.getHoraFormatadaJPQL(inicioConsulta);
        String horarioFinalFormatado = DateUtils.getHoraFormatadaJPQL(finalConsulta);

        String filtro = " " +
                "where c.data = " + dataFormatada + " " +
                "and (" +
                    "p.id = " + paciente.getId() + " or " +
                    "r.id = " + profissional.getId() +
                ") " +
                "and (" +
                    "(" + horarioInicialFormatado + " > c.horarioInicio and "  + horarioInicialFormatado + " < c.horarioTermino) or " +
                    "(" + horarioFinalFormatado + " > c.horarioInicio and "  + horarioFinalFormatado + " < c.horarioTermino) or " +
                    "(" + horarioInicialFormatado + " = c.horarioInicio and " + horarioFinalFormatado + " >= c.horarioTermino) or " +
                    "(" + horarioFinalFormatado + " = c.horarioTermino and " + horarioInicialFormatado + " <= c.horarioInicio) or " +
                    "(" + horarioInicialFormatado + " <= c.horarioInicio and " + horarioFinalFormatado + " >= c.horarioTermino)" +
                ")";

        if (codigoConsulta != null) {
            filtro += " " +
                    "and c.id <> " + codigoConsulta;
        }

        processarFiltro(filtro);
    }

    @Override
    public void limparFiltros() {
        codigo = null;
        periodoInicial = null;
        periodoFinal = null;
        ordenacao = null;
    }

    private String getCampoOrderBy() {
        if (ordenacao == null) {
            return null;
        }

        switch (ordenacao) {
            case 'D':
                return "c.data, c.horarioInicio";
            case 'C':
                return "p.id, c.data, c.horarioInicio";
            case 'N':
                return "p.nome, c.data, c.horarioInicio";
            default:
                return null;
        }
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public Character getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(Character ordenacao) {
        this.ordenacao = ordenacao;
    }
}
