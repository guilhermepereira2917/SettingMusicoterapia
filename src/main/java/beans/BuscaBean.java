package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import utils.JSFUtils;

@Named
@SessionScoped
public class BuscaBean implements Serializable {

    private static Object objetoSelecionado;
    private List<Object> objetosSelecionados;

    public static <T> T getResultadoPesquisa(Class<T> clazz) {
        T retorno = (T) objetoSelecionado;
        objetoSelecionado = null;

        return retorno;
    }

    public <T> List<T> getResultadosPesquisa(Class<T> clazz) {
        List<T> retornos = (List<T>)(List<?>) objetosSelecionados;
        objetosSelecionados = null;

        return retornos;
    }
    
    public static <T> void resultadoPesquisa(Object object) {
        objetoSelecionado = object;
    }

    public static <T> void resultadoPesquisaInputConverter(SelectEvent<T> event) {
        objetoSelecionado = event.getObject();
    }

    public List<Object> getObjetosSelecionados() {
        return objetosSelecionados;
    }

    public void setObjetosSelecionados(List<Object> aObjetosSelecionados) {
        objetosSelecionados = aObjetosSelecionados;
    }

    public void finalizarBusca() {
        PrimeFaces.current().dialog().closeDynamic(null);
    }

    public void finalizarBuscaSelectEvent(SelectEvent event) {
        PrimeFaces.current().dialog().closeDynamic(event.getObject());
    }

    private void abrirBusca(BuscaEnum busca, boolean multiplaSelecao) {
        Map<String, List<String>> parametros = new HashMap<>();
        if (multiplaSelecao) {
            parametros.put("multiplaSelecao", List.of("true"));
        }

        JSFUtils.abreDialog("/buscas/" + busca.getCaminhoMenu(), parametros);
    }

    public void abrirBuscaPaises() {
        abrirBusca(BuscaEnum.BUSCA_PAISES, false);
    }

    public void abrirBuscaCidades() {
        abrirBusca(BuscaEnum.BUSCA_CIDADES, false);
    }

    public void abrirBuscaProfissoes() {
        abrirBusca(BuscaEnum.BUSCA_PROFISSOES, false);
    }

    public void abrirBuscaReligioes() {
        abrirBusca(BuscaEnum.BUSCA_RELIGIOES, false);
    }

    public void abrirBuscaFamiliares() {
        abrirBusca(BuscaEnum.BUSCA_FAMILIARES, false);
    }

    public void abrirBuscaPacientes() {
        abrirBusca(BuscaEnum.BUSCA_PACIENTES, false);
    }

    public void abrirBuscaProfissionais(boolean multiplaSelecao) {
        abrirBusca(BuscaEnum.BUSCA_PROFISSIONAIS, multiplaSelecao);
    }

}
