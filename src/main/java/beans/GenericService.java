package beans;

import jakarta.ejb.EJB;
import java.util.List;

public abstract class GenericService<T, S extends FiltrosService> {
    @EJB
    protected EntityManager entityManager;

    public List<T> retornaRegistrosFiltrados(S filtrosService) {
        String prefixo = getPrefixo();
        String filtros = filtrosService.getSqlFiltrada(prefixo);

        String sql = getSelect() + filtros;

        return entityManager.getEntityManager().createQuery(sql).getResultList();
    }

    protected abstract String getPrefixo();
    protected abstract String getSelect();
}
