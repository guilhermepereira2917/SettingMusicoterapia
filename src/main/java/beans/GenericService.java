package beans;

import jakarta.ejb.EJB;
import java.util.List;

public abstract class GenericService<T, S extends FiltrosService> {
    @EJB
    protected EntityManager entityManager;

    public List<T> retornaRegistrosFiltrados(S filtrosService) {
        String sql = getSelectComFiltros(filtrosService);

        return entityManager.getEntityManager().createQuery(sql).getResultList();
    }

    protected String getSelectComFiltros(S filtrosService) {
        return getSelect() + filtrosService.getSqlFiltrada();
    }

    protected abstract String getSelect();
}
