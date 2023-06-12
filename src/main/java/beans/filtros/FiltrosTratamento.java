package beans.filtros;

import beans.FiltrosService;

import java.util.Date;

public class FiltrosTratamento extends FiltrosService {

    private Date periodoInicial;
    private Date periodoFinal;

    @Override
    protected void processarFiltros() {
        processaFiltroVigenciaData(periodoInicial, periodoFinal, "t.inicioVigencia", "t.finalVigencia");
    }

    @Override
    public void limparFiltros() {
        periodoInicial = new Date();
        periodoFinal = null;
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
}
