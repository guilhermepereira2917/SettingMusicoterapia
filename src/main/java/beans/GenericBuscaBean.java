package beans;

import beans.FiltrosService;
import beans.GenericService;
import beans.filtros.FiltrosProfissional;
import beans.services.ProfissionalService;
import entities.Profissional;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import utils.JSFUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericBuscaBean<T, S extends GenericService, F extends FiltrosService> implements Serializable {
    protected List<T> registros = new ArrayList<>();
    protected F filtros;

    private boolean multiplaSelecao;

    @Inject
    protected S service;

    @PostConstruct
    public void init() {
        setMultiplaSelecaoFromParametros();
        iniciaFiltros();
        pesquisar();
    }

    private void setMultiplaSelecaoFromParametros() {
        multiplaSelecao = Boolean.valueOf(JSFUtils.getParam("multiplaSelecao"));
    }

    public void pesquisar() {
        registros = service.retornaRegistrosFiltrados(filtros);
    }

    public void limparFiltros() {
        filtros.limparFiltros();
        pesquisar();
    }

    protected abstract void iniciaFiltros();

    public List<T> getRegistros() {
        return registros;
    }

    public void setRegistros(List<T> registros) {
        this.registros = registros;
    }

    public F getFiltros() {
        return filtros;
    }

    public void setFiltros(F filtros) {
        this.filtros = filtros;
    }

    public boolean isMultiplaSelecao() {
        return multiplaSelecao;
    }

    public void setMultiplaSelecao(boolean multiplaSelecao) {
        this.multiplaSelecao = multiplaSelecao;
    }
}
