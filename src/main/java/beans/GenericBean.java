package beans;

import java.io.Serializable;
import utils.JSFUtils;

public abstract class GenericBean<T> implements Serializable {

    protected T objetoCrud;

    public abstract void novo();

    public void salvar() {
        if (!validar(objetoCrud)) {
            return;
        }

        T objetoPersistido = EntityManager.salvar(objetoCrud);                
        if (objetoPersistido != null) {
            objetoCrud = objetoPersistido;
            JSFUtils.mensagemSucesso("Registro salvo com sucesso!");
        } else {
            JSFUtils.mensagemErro("Erro ao salvar registro!");
        }
    }

    protected boolean validar(T objeto) {
        return true;
    }

    public void cancelar() {
        EntityManager.cancelar(objetoCrud);

        JSFUtils.mensagemSucesso("Alterações canceladas no registro com sucesso!");
    }

    public void excluir() {
        if (EntityManager.excluir(objetoCrud)) {
            JSFUtils.mensagemSucesso("Registro deletado com sucesso!");
        } else {
            JSFUtils.mensagemErro("Erro ao deletar registro!");
        }
    }

}
