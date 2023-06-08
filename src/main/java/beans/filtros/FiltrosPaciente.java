package beans.filtros;

import beans.FiltrosService;
import entities.Paciente;
import utils.SQLUtils;

import java.util.List;

public class FiltrosPaciente extends FiltrosService {

    private Integer codigoPaciente;
    private String descricaoPaciente;

    private List<Paciente> pacientesSelecionados;

    private Character filtroInativos;
    private Character ordenacao;

    @Override
    protected void processarFiltros(String prefixo) {
        processaFiltroInteiro(prefixo, codigoPaciente, "id");
        processaFiltroLike(prefixo, descricaoPaciente, "nome");
        processaFiltroIn(prefixo, SQLUtils.montarFiltroInPacientes(pacientesSelecionados), "id");

        if (filtroInativos != null) {
            processaFiltroBooleano(prefixo, filtroInativos == 'I', "inativo");
        }

        adicionaOrdenacao(prefixo, getCampoOrderBy());
    }

    @Override
    public void limparFiltros() {
        pacientesSelecionados = null;
        filtroInativos = null;
        ordenacao = 'C';
    }

    private String getCampoOrderBy() {
        if (ordenacao == null) {
            return null;
        }

        switch (ordenacao) {
            case 'C':
                return "id";
            case 'N':
                return "nome";
            default:
                return null;
        }
    }

    public Integer getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(Integer codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getDescricaoPaciente() {
        return descricaoPaciente;
    }

    public void setDescricaoPaciente(String descricaoPaciente) {
        this.descricaoPaciente = descricaoPaciente;
    }

    public List<Paciente> getPacientesSelecionados() {
        return pacientesSelecionados;
    }

    public void setPacientesSelecionados(List<Paciente> pacientesSelecionados) {
        this.pacientesSelecionados = pacientesSelecionados;
    }

    public Character getFiltroInativos() {
        return filtroInativos;
    }

    public void setFiltroInativos(Character filtroInativos) {
        this.filtroInativos = filtroInativos;
    }

    public Character getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(Character ordenacao) {
        this.ordenacao = ordenacao;
    }
}
