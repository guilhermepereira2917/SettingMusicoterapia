package beans;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

@Stateless
@LocalBean
public class EntityManager implements Serializable {

    @PersistenceContext(unitName = "MainPersistenceUnit")
    private jakarta.persistence.EntityManager entityManager;

    public <T> T salvar(T objeto) {
        return entityManager.merge(objeto);
    }

    public void cancelar(Object objeto) {
        entityManager.refresh(objeto);
    }

    public boolean excluir(Object objeto) {
        if (!entityManager.contains(objeto)) {
            objeto = entityManager.merge(objeto);
        }

        entityManager.remove(objeto);
        return true;
    }

    public <T> T procurar(Integer id, Class<T> clazz) {
        try {            
            T objeto = entityManager.createQuery(String.format("SELECT c FROM %s c WHERE c.id = %d", clazz.getName(), id), clazz).getSingleResult();           

            return objeto;
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
            
            return null;
        }
    }

    public jakarta.persistence.EntityManager getEntityManager() {
        return entityManager;
    }
}
