package beans.services;

import beans.GenericService;
import beans.filtros.FiltrosTratamento;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import entities.Tratamento;

@Stateless
@LocalBean
public class TratamentoService extends GenericService<Tratamento, FiltrosTratamento> {

    @Override
    protected String getSelect() {
        return "select t from Tratamento t";
    }
}