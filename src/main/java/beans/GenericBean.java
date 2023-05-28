package beans;

import java.io.Serializable;

import jakarta.ejb.EJB;
import utils.JSFUtils;

public abstract class GenericBean<T> implements Serializable {

    @EJB
    protected EntityManager entityManager;

    protected T objetoCrud;

    public abstract void novo();

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
            JSFUtils.mensagemSucesso("Registro deletado com sucesso!");
        } else {
            JSFUtils.mensagemErro("Erro ao deletar registro!");
        }
    }

}
