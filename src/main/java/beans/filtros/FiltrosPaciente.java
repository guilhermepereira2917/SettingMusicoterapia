package beans.filtros;

import beans.FiltrosService;
import entities.Paciente;
import utils.SQLUtils;

import java.util.List;

public class FiltrosPaciente extends FiltrosService {

    private List<Paciente> pacientesSelecionados;
    private Character filtroInativos;
    private Character ordenacao;

    @Override
    protected void processarFiltros(String prefixo) {
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
