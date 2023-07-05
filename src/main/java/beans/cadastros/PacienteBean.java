package beans.cadastros;

import beans.BuscaBean;
import beans.GenericBean;
import entities.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import utils.JSFUtils;

@Named
@ViewScoped
public class PacienteBean extends GenericBean<Paciente> {

    @Inject
    private BuscaBean buscaBean;

    @Inject
    private TratamentoBean tratamentoBean;

    @PostConstruct
    @Override
    public void init() {
        Paciente pacienteSelecionado = buscaBean.getResultadoPesquisa(Paciente.class);

        if (pacienteSelecionado == null) {
            objetoCrud = new Paciente();
        } else {
            objetoCrud = pacienteSelecionado;
        }

        atualizaTratamentoBeanComUltimoTratamento();
    }

    private void atualizaTratamentoBeanComUltimoTratamento() {
        if (objetoCrud.getTratamentos().isEmpty()) {
            tratamentoBean.setTratamento(null);
        } else {
            Tratamento ultimoTratamentoPaciente = objetoCrud.getTratamentos().get(objetoCrud.getTratamentos().size() - 1);
            tratamentoBean.setTratamento(ultimoTratamentoPaciente);
        }
    }

    @Override
    public void novo() {
        objetoCrud = new Paciente();
    }

    @Override
    public void setObjetoCrudPesquisa() {
        objetoCrud = buscaBean.getResultadoPesquisa(Paciente.class);

        atualizaTratamentoBeanComUltimoTratamento();
    }

    @Override
    public void salvar() {
        super.salvar();

        atualizaTratamentoBeanComUltimoTratamento();
    }

    @Override
    public boolean validar(Paciente paciente) {
        if (paciente.getPrematuro() && (paciente.getSemanasPrematuro() == null || paciente.getSemanasPrematuro() <= 0)) {
            JSFUtils.mensagemErro("Paciente prematuro sem quantidade de semanas prematuro informado!");
            return false;
        }

        if (tratamentoBean.getTratamento() != null && !tratamentoBean.validar(tratamentoBean.getTratamento())) {
            return false;
        }

        return true;
    }

    @Override
    public void excluir() {
        super.excluir();

        JSFUtils.redirecionarPagina("../index.xhtml");
    }

    public void limpaSemanasPrematuro() {
        if (objetoCrud != null && !objetoCrud.getPrematuro()) {
            objetoCrud.setSemanasPrematuro(null);
        }
    }

    public void selecionaNacionalidade() {
        objetoCrud.setPaisNacionalidade(BuscaBean.getResultadoPesquisa(Pais.class));
    }

    public void limpaNacionalidade() {
        objetoCrud.setPaisNacionalidade(null);
    }

    public void selecionaNaturalidade() {
        objetoCrud.setCidadeNaturalidade(BuscaBean.getResultadoPesquisa(Cidade.class));       
    }

    public void limpaNaturalidade() {
        objetoCrud.setCidadeNaturalidade(null);
    }

    public void selecionaProfissao() {
        objetoCrud.setProfissao(BuscaBean.getResultadoPesquisa(Profissao.class));               
    }
    
    public void limpaProfissao() {
        objetoCrud.setProfissao(null);               
    }

    public void selecionaReligiao() {
        objetoCrud.setReligiao(BuscaBean.getResultadoPesquisa(Religiao.class));         
    }

    public void limpaReligiao() {
        objetoCrud.setReligiao(null);         
    }
    
    public void selecionaEnderecoCidade() {
        Cidade cidade = BuscaBean.getResultadoPesquisa(Cidade.class);

        objetoCrud.setEnderecoCidade(cidade);

        if (objetoCrud.getEnderecoCep() == null || objetoCrud.getEnderecoCep().isBlank()) {
            objetoCrud.setEnderecoCep(cidade.getCep());
        }
    }
    
    public void limpaEnderecoCidade() {
        objetoCrud.setEnderecoCidade(null);
    }

    public void adicionaFamiliar(SelectEvent<Familiar> event) {

        for (FamiliarPaciente familiar : objetoCrud.getFamiliares()) {
            if (familiar.getFamiliar().getId().equals(event.getObject().getId())) {
                JSFUtils.mensagemAviso("Familiar já adicionado para este paciente!");
                return;
            }
        }

        FamiliarPaciente familiarPaciente = new FamiliarPaciente();
        familiarPaciente.setFamiliar(event.getObject());
        familiarPaciente.setPaciente(objetoCrud);

        objetoCrud.getFamiliares().add(familiarPaciente);

        JSFUtils.mensagemSucesso("Familiar adicionado com sucesso!");
    }

    public void removeFamiliar(FamiliarPaciente familiarPaciente) {
        objetoCrud.getFamiliares().remove(familiarPaciente);
        JSFUtils.mensagemSucesso("Familiar removido com sucesso!");
    }

    public void adicionaTelefone() {
        TelefonePaciente telefone = new TelefonePaciente();
        telefone.setPaciente(objetoCrud);

        objetoCrud.getTelefones().add(telefone);
    }

    public void removeTelefone(TelefonePaciente telefone) {
        objetoCrud.getTelefones().remove(telefone);
        JSFUtils.mensagemSucesso("Telefone removido com sucesso!");
    }

    public void adicionaTratamento() {
        if (!objetoCrud.getTratamentos().isEmpty()) {
            JSFUtils.mensagemErro("Paciente já possui um tratamento!");
            return;
        }

        this.tratamentoBean.novo();
        this.tratamentoBean.getTratamento().setPaciente(this.objetoCrud);
        this.objetoCrud.getTratamentos().add(tratamentoBean.getTratamento());
    }

    public void selecionarTratamentoEdicao(SelectEvent<Tratamento> event) {
        this.tratamentoBean.setTratamento(event.getObject());
    }

    public Paciente getPaciente() {
        return objetoCrud;
    }

    public void setPaciente(Paciente paciente) {
        objetoCrud = paciente;
    }

    public TratamentoBean getTratamentoBean() {
        return tratamentoBean;
    }

    public void setTratamentoBean(TratamentoBean tratamentoBean) {
        this.tratamentoBean = tratamentoBean;
    }
}
