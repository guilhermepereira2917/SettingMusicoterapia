package views;

import beans.EntityManager;
import java.io.Serializable;
import java.util.List;
import jakarta.annotation.PostConstruct;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import utils.JSFUtils;

public class View<T> implements Serializable {

    private final Class<T> tipo;
    private List<T> lista;

    public View(Class<T> tipo) {
        this.tipo = tipo;
    }

    @PostConstruct
    public void init() {
        lista = EntityManager.entityManager.createQuery(String.format("SELECT p FROM %s p", tipo.getName())).getResultList();
    }

    public List<T> getLista() {
        return lista;
    }

    public void onRowSelect(SelectEvent<T> event) {
        PrimeFaces.current().dialog().closeDynamic(event.getObject());
    }

    public void novo() {
        try {
            lista.add(tipo.newInstance());
        } catch (Exception ex) {
            JSFUtils.mensagemErro("Erro ao criar novo objeto do tipo " + tipo.getName());
        }
    }

    public void salva(RowEditEvent<T> event) {
        T objeto = event.getObject();
        
        if (!validar(objeto)) {
            return;
        }

        Integer indiceObjeto = null;
        for (int i = 0; i < lista.size(); i++) {
            if (objeto.equals(lista.get(i))) {
                indiceObjeto = i;
                break;
            }
        }
        
        objeto = EntityManager.salvar(event.getObject());
        if (objeto == null) {
            JSFUtils.mensagemErro("Erro ao salvar registro!");
            return;
        }
        
        if (indiceObjeto != null) {           
            lista.set(indiceObjeto, objeto);
        } else {
            lista.add(objeto);
        }
                
        JSFUtils.mensagemSucesso("Registro salvo com sucesso!");
    }

    protected boolean validar(T objeto) {
        return true;
    }

    public void apaga(T objeto) {
        lista.remove(objeto);
        EntityManager.excluir(objeto);
        
        JSFUtils.mensagemSucesso("Registro excluído com sucesso!");
    }

}
