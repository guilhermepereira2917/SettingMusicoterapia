package beans.relatorios;

import beans.GenericRelatorioBean;
import beans.filtros.FiltrosConsulta;
import beans.services.ConsultaService;
import com.itextpdf.text.Element;
import entities.Consulta;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import utils.BigDecimalUtils;
import utils.DateUtils;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RelatorioConsultasBean extends GenericRelatorioBean<Consulta> implements Serializable {

    @Inject
    private ConsultaService consultaService;
    private FiltrosConsulta filtrosConsulta = new FiltrosConsulta();

    @Override
    protected float[] getLargurasColunasRelatorio() {
        return new float[]{0.12f, 0.16f, 0.12f, 0.12f, 0.28f, 0.16f};
    }

    @Override
    protected void imprimirCabecalho() {
        adicionarCelulaCabecalho("Código", Element.ALIGN_RIGHT);
        adicionarCelulaCabecalho("Data", Element.ALIGN_CENTER);
        adicionarCelulaCabecalho("Horário Inicial", Element.ALIGN_CENTER);
        adicionarCelulaCabecalho("Horário Final", Element.ALIGN_CENTER);
        adicionarCelulaCabecalho("Paciente", Element.ALIGN_LEFT);
        adicionarCelulaCabecalho("Valor", Element.ALIGN_RIGHT);
    }

    @Override
    protected void imprimirLinha(Consulta consulta) {
        adicionarCelulaLinha(consulta.getId().toString(), Element.ALIGN_RIGHT);
        adicionarCelulaLinha(DateUtils.getDataFormatada(consulta.getData()), Element.ALIGN_CENTER);
        adicionarCelulaLinha(DateUtils.getHorarioFormatado(consulta.getHorarioInicio()), Element.ALIGN_CENTER);
        adicionarCelulaLinha(DateUtils.getHorarioFormatado(consulta.getHorarioTermino()), Element.ALIGN_CENTER);
        adicionarCelulaLinha(consulta.getTratamento().getPaciente().getCodigoNomeFormatado(), Element.ALIGN_LEFT);
        adicionarCelulaLinha(BigDecimalUtils.getValorFormatado(consulta.getTratamento().getValor()), Element.ALIGN_RIGHT);
    }

    @Override
    public void tirarRelatorio() {
        List<Consulta> consultas = consultaService.retornaRegistrosFiltrados(filtrosConsulta);
        tirarRelatorio(consultas);
    }

    @Override
    public String getNomeRelatorio() {
        return "Relatório de Consultas";
    }

    @Override
    public String getNomeArquivo() {
        return "RelatorioConsultas";
    }

    public FiltrosConsulta getFiltrosConsulta() {
        return filtrosConsulta;
    }

    public void setFiltrosConsulta(FiltrosConsulta filtrosConsulta) {
        this.filtrosConsulta = filtrosConsulta;
    }
}
