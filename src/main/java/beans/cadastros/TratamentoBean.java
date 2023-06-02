package beans.cadastros;

import beans.BuscaBean;
import beans.GenericBean;
import entities.Profissional;
import entities.Tratamento;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import utils.JSFUtils;

import java.math.BigDecimal;

@Named
@ViewScoped
public class TratamentoBean extends GenericBean<Tratamento> {

    @Override
    public void novo() {
        objetoCrud = new Tratamento();
    }

    @Override
    public boolean validar(Tratamento tratamento) {
        if (tratamento.getProfissional() == null) {
            JSFUtils.mensagemErro("Informe o profissional responsável pelo tratamento!");
            return false;
        }

        if (tratamento.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            JSFUtils.mensagemErro("Valor do Tratamento deve ser maior ou igual a zero!");
            return false;
        }

        if (tratamento.getFinalVigencia() != null && tratamento.getInicioVigencia().compareTo(tratamento.getFinalVigencia()) > 0) {
            JSFUtils.mensagemErro("Data de Vigência final do tratamento deve ser maior do que Data de Vigência Inicial do tratamento!");
            return false;
        }

        return true;
    }

    public Tratamento getTratamento() {
        return this.objetoCrud;
    }

    public void setTratamento(Tratamento tratamento) {
        this.objetoCrud = tratamento;
    }

    public void selecionaProfissionalTratamento() {
        this.objetoCrud.setProfissional(BuscaBean.getResultadoPesquisa(Profissional.class));
    }

    public void limpaProfissionalTratamento() {
        this.objetoCrud.setProfissional(null);
    }
}
