package beans.filtros;

import beans.FiltrosService;

import java.util.Date;

public class FiltrosConsulta extends FiltrosService {

    private Date periodoInicial;
    private Date periodoFinal;

    private Character ordenacao;

    @Override
    protected void processarFiltros(String prefixo) {
        processaFiltroBetween(prefixo, periodoInicial, periodoFinal, "data");
        adicionaOrdenacao(getCampoOrderBy());
    }

    @Override
    public void limparFiltros() {
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
                return "c.data";
            case 'C':
                return "p.id, c.data";
            case 'N':
                return "p.nome, c.data";
            default:
                return null;
        }
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
