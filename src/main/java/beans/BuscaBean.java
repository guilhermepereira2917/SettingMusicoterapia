package beans;

import java.io.Serializable;
import java.util.List;
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
    
    private static void abrirBusca(BuscaEnum busca) {
        JSFUtils.abreDialog("/buscas/" + busca.getCaminhoMenu());
    }

    public static void abrirBuscaPaises() {
        abrirBusca(BuscaEnum.BUSCA_PAISES);
    }

    public static void abrirBuscaCidades() {
        abrirBusca(BuscaEnum.BUSCA_CIDADES);
    }

    public static void abrirBuscaProfissoes() {
        abrirBusca(BuscaEnum.BUSCA_PROFISSOES);
    }

    public static void abrirBuscaReligioes() {
        abrirBusca(BuscaEnum.BUSCA_RELIGIOES);
    }

    public static void abrirBuscaFamiliares() {
        abrirBusca(BuscaEnum.BUSCA_FAMILIARES);
    }

    public static void abrirBuscaPacientes() {
        abrirBusca(BuscaEnum.BUSCA_PACIENTES);
    }

}
