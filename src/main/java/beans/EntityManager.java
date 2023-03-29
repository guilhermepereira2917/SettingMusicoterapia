package beans;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Persistence;

@Dependent
public class EntityManager {

    public static jakarta.persistence.EntityManager entityManager = Persistence.createEntityManagerFactory("MainPersistenceUnit").createEntityManager();

    public static <T> T salvar(T objeto) {
        return entityManager.merge(objeto);
    }

    public static void cancelar(Object objeto) {
        entityManager.refresh(objeto);
    }

    public static boolean excluir(Object objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();

            return true;
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();

            return false;
        }
    }

    public static <T> T procurar(Integer id, Class<T> clazz) {
        try {            
            T objeto = entityManager.createQuery(String.format("SELECT c FROM %s c WHERE c.id = %d", clazz.getName(), id), clazz).getSingleResult();           

            return objeto;
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
            
            return null;
        }
    }

}
