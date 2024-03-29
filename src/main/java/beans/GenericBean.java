package beans;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Init;
import jakarta.inject.Inject;
import utils.JSFUtils;

public abstract class GenericBean<T> implements Serializable {

    @EJB
    protected EntityManager entityManager;

    @Inject
    protected BuscaBean buscaBean;

    protected T objetoCrud;

    @PostConstruct
    public void init() {
        novo();
    }

    public abstract void novo();

    public abstract void setObjetoCrudPesquisa();

    public void salvar() {
        if (!validar(objetoCrud)) {
            return;
        }

        try {
            objetoCrud = entityManager.salvar(objetoCrud);

            JSFUtils.mensagemSucesso("Registro salvo com sucesso!");
        } catch (Exception ex) {
            String mensagemErro = String.format("Erro '%s' ao salvar registro!", ex.getMessage());
            JSFUtils.mensagemErro(mensagemErro);
        }
    }

    protected boolean validar(T objeto) {
        return true;
    }

    public void cancelar() {
        entityManager.cancelar(objetoCrud);

        JSFUtils.mensagemSucesso("Alterações canceladas no registro com sucesso!");
    }

    public void excluir() {
        if (entityManager.excluir(objetoCrud)) {
            novo();

            JSFUtils.mensagemSucesso("Registro deletado com sucesso!");
        } else {
            JSFUtils.mensagemErro("Erro ao deletar registro!");
        }
    }

}
