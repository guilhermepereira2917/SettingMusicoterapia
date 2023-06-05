package beans.relatorios;

import beans.BuscaBean;
import beans.GenericRelatorioBean;
import beans.filtros.FiltrosPaciente;
import beans.services.PacienteService;
import com.itextpdf.text.Element;
import entities.Paciente;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RelatorioPacientesBean extends GenericRelatorioBean<Paciente> implements Serializable {

    @Inject
    private BuscaBean buscaBean;

    @Inject
    private PacienteService pacienteService;
    private FiltrosPaciente filtrosPaciente = new FiltrosPaciente();

    @Override
    protected float[] getLargurasColunasRelatorio() {
        return new float[]{0.16f, 0.60f, 0.14f, 0.2f};
    }

    @Override
    protected void imprimirCabecalho() {
        adicionarCelulaCabecalho("Código", Element.ALIGN_RIGHT);
        adicionarCelulaCabecalho("Nome", Element.ALIGN_LEFT);
        adicionarCelulaCabecalho("Idade", Element.ALIGN_RIGHT);
        adicionarCelulaCabecalho("Situação", Element.ALIGN_CENTER);
    }

    @Override
    protected void imprimirLinha(Paciente paciente) {
        adicionarCelulaLinha(paciente.getId().toString(), Element.ALIGN_RIGHT);
        adicionarCelulaLinha(paciente.getNome(), Element.ALIGN_LEFT);

        String idadePaciente;
        if (paciente.getDataNascimento() != null) {
            idadePaciente = paciente.getIdade().toString();
        } else {
            idadePaciente = "";
        }

        adicionarCelulaLinha(idadePaciente, Element.ALIGN_RIGHT);
        adicionarCelulaLinha(paciente.getInativo() ? "Inativo" : "Ativo", Element.ALIGN_CENTER);
    }

    @Override
    public void tirarRelatorio() {
        List<Paciente> pacientes = pacienteService.retornaRegistrosFiltrados(filtrosPaciente);
        tirarRelatorio(pacientes);
    }

    @Override
    public String getNomeRelatorio() {
        return "Relatório de Pacientes";
    }

    @Override
    public String getNomeArquivo() {
        return "RelatorioPacientes";
    }

    public String descricaoPacientesSelecionados() {
        if (filtrosPaciente.getPacientesSelecionados() == null || filtrosPaciente.getPacientesSelecionados().isEmpty()) {
            return null;
        }

        if (filtrosPaciente.getPacientesSelecionados().size() == 1) {
            Paciente pacienteSelecionado = filtrosPaciente.getPacientesSelecionados().get(0);
            return pacienteSelecionado.getId().toString() + " - " + pacienteSelecionado.getNome();
        } else {
            return filtrosPaciente.getPacientesSelecionados().size() + " pacientes selecionados";
        }
    }

    public void selecionarPacientes() {
        filtrosPaciente.setPacientesSelecionados(buscaBean.getResultadosPesquisa(Paciente.class));
    }

    public void limpaPacientesSelecionados() {
        filtrosPaciente.setPacientesSelecionados(null);
    }

    public FiltrosPaciente getFiltrosPaciente() {
        return filtrosPaciente;
    }

    public void setFiltrosPaciente(FiltrosPaciente filtrosPaciente) {
        this.filtrosPaciente = filtrosPaciente;
    }
}
