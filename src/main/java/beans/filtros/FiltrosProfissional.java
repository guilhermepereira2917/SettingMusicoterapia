package beans.filtros;

import beans.FiltrosService;

public class FiltrosProfissional extends FiltrosService {

    Integer codigoProfissional;

    String descricaoProfissional;

    @Override
    protected void processarFiltros(String prefixo) {
        processaFiltroInteiro(prefixo, codigoProfissional, "id");
        processaFiltroLike(prefixo, descricaoProfissional, "nome");
    }

    @Override
    public void limparFiltros() {
        codigoProfissional = null;
        descricaoProfissional = null;
    }

    public Integer getCodigoProfissional() {
        return codigoProfissional;
    }

    public void setCodigoProfissional(Integer codigoProfissional) {
        this.codigoProfissional = codigoProfissional;
    }

    public String getDescricaoProfissional() {
        return descricaoProfissional;
    }

    public void setDescricaoProfissional(String descricaoProfissional) {
        this.descricaoProfissional = descricaoProfissional;
    }
}
