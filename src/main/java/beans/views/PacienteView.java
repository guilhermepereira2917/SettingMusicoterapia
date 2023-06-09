package beans.views;

import beans.BuscaBean;
import entities.Paciente;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import utils.JSFUtils;

@Named
@ViewScoped
public class PacienteView extends View<Paciente> implements Serializable {

    @Inject
    private BuscaBean buscaBean;

    public PacienteView() {
        super(Paciente.class);
    }

    @Override
    public void onRowSelect(SelectEvent<Paciente> event) {
        Paciente paciente = event.getObject();
        buscaBean.resultadoPesquisa(paciente);

        JSFUtils.redirecionarPagina("cadastros/cadastroPacientes.xhtml");
    }

}
