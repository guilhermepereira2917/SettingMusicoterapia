package beans.relatorios;

import beans.BuscaBean;
import beans.EntityManager;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Paciente;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.Transient;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import utils.JSFUtils;
import utils.RelatorioUtils;
import static utils.RelatorioUtils.corCabecalho;
import static utils.RelatorioUtils.corCelulaImpar;
import static utils.RelatorioUtils.corCelulaPar;
import static utils.RelatorioUtils.fonteCabecalho;
import static utils.RelatorioUtils.fonteTexto;
import static utils.RelatorioUtils.formatadorDataHoraNomeArquivo;
import static utils.RelatorioUtils.formatadorDataHoraRelatorio;
import utils.SQLUtils;

@Named
@ViewScoped
public class RelatorioPacientesBean implements Serializable {

    private List<Paciente> pacientesSelecionados;
    private Character filtroInativos;
    private Character ordenacao;

    private Boolean usarAnd;

    @EJB
    private EntityManager entityManager;

    @Inject
    BuscaBean buscaBean;

    @Transient
    @Inject
    VisualizadorPDFBean visualizadorPDF;

    @PostConstruct
    public void init() {
        limparFiltros();
    }

    private String whereOuAnd() {
        String resultado = usarAnd ? "AND" : "WHERE";
        usarAnd = true;

        return resultado;
    }

    private String montarSelect() {
        usarAnd = false;

        String sql = "SELECT p FROM Paciente p";

        if (filtroInativos == 'I') {
            sql += " " + whereOuAnd() + " p.inativo = TRUE";
        } else if (filtroInativos == 'A') {
            sql += " " + whereOuAnd() + " p.inativo = FALSE";
        }

        if (pacientesSelecionados != null && !pacientesSelecionados.isEmpty()) {
            sql += " " + whereOuAnd() + " p.id IN " + SQLUtils.montarFiltroInPacientes(pacientesSelecionados);
        }

        if (ordenacao == 'C') {
            sql += " ORDER BY p.id";
        } else {
            sql += " ORDER BY p.nome";
        }

        return sql;
    }

    public void tirarRelatorio() {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        List<Paciente> pacientes = entityManager.getEntityManager().createQuery(montarSelect(), Paciente.class).getResultList();

        if (pacientes.isEmpty()) {
            JSFUtils.mensagemAviso("Nenhum paciente encontrado!");
            return;
        }

        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
            pdfWriter.setPageEvent(new EventosCabecalhoRelatorio(dataHoraAtual));

            document.open();
            PdfPTable tabela = new PdfPTable(4);
            float[] larguras = new float[]{0.16f, 0.60f, 0.14f, 0.2f};

            tabela.setTotalWidth(PageSize.A4.getWidth() - document.leftMargin() * 2);
            tabela.setLockedWidth(true);
            tabela.setWidths(larguras);              

            tabela.addCell(RelatorioUtils.criarCelula("Código", Element.ALIGN_RIGHT, fonteCabecalho, corCabecalho));
            tabela.addCell(RelatorioUtils.criarCelula("Nome", Element.ALIGN_LEFT, fonteCabecalho, corCabecalho));
            tabela.addCell(RelatorioUtils.criarCelula("Idade", Element.ALIGN_RIGHT, fonteCabecalho, corCabecalho));
            tabela.addCell(RelatorioUtils.criarCelula("Situação", Element.ALIGN_CENTER, fonteCabecalho, corCabecalho));

            tabela.setHeaderRows(1);                                   
            
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente paciente = pacientes.get(i);
                BaseColor corCelula = i % 2 == 0 ? corCelulaPar : corCelulaImpar;
                
                tabela.addCell(RelatorioUtils.criarCelula(paciente.getId().toString(), Element.ALIGN_RIGHT, fonteTexto, corCelula));
                tabela.addCell(RelatorioUtils.criarCelula(paciente.getNome(), Element.ALIGN_LEFT, fonteTexto, corCelula));

                String idadePaciente;
                if (paciente.getDataNascimento() != null) {
                    idadePaciente = paciente.getIdade().toString();
                } else {
                    idadePaciente = "";
                }

                tabela.addCell(RelatorioUtils.criarCelula(idadePaciente, Element.ALIGN_RIGHT, fonteTexto, corCelula));
                tabela.addCell(RelatorioUtils.criarCelula(paciente.getInativo() ? "Inativo" : "Ativo", Element.ALIGN_CENTER, fonteTexto, corCelula));
            }

            document.add(tabela);
            document.close();

            StreamedContent conteudo = DefaultStreamedContent.builder().stream(() -> new ByteArrayInputStream(out.toByteArray()))
                    .contentType("application/pdf").name(String.format("RelatorioPacientes-%s.pdf", dataHoraAtual.format(formatadorDataHoraNomeArquivo))).build();

            visualizadorPDF.setConteudo(conteudo);

            JSFUtils.abreDialog("/visualizadorPDF.xhtml");
        } catch (DocumentException ex) {
            JSFUtils.mensagemErro("Erro ao gerar relatório!");
        }
    }

    public void limparFiltros() {
        pacientesSelecionados = null;
        filtroInativos = 'T';
        ordenacao = 'N';
    }

    public Character getFiltroInativos() {
        return filtroInativos;
    }

    public void setFiltroInativos(Character filtroInativos) {
        this.filtroInativos = filtroInativos;
    }

    public Character getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(Character ordenacao) {
        this.ordenacao = ordenacao;
    }

    public List<Paciente> getPacientesSelecionados() {
        return pacientesSelecionados;
    }

    public void setPacientesSelecionados(List<Paciente> pacientesSelecionados) {
        this.pacientesSelecionados = pacientesSelecionados;
    }

    public void limpaPacientesSelecionados() {
        this.pacientesSelecionados = null;
    }

    public void selecionarPacientes() {
        this.pacientesSelecionados = buscaBean.getResultadosPesquisa(Paciente.class);
    }

    public String descricaoPacientesSelecionados() {
        if (pacientesSelecionados == null || pacientesSelecionados.isEmpty()) {
            return null;
        }

        if (pacientesSelecionados.size() == 1) {
            Paciente pacienteSelecionado = pacientesSelecionados.get(0);
            return pacienteSelecionado.getId().toString() + " - " + pacienteSelecionado.getNome();
        } else {
            return pacientesSelecionados.size() + " pacientes selecionados";
        }
    }

    private class EventosCabecalhoRelatorio extends PdfPageEventHelper implements Serializable {

        Image logo;
        LocalDateTime dataHoraAtual;

        public EventosCabecalhoRelatorio(LocalDateTime dataHoraAtual) {
            super();
            this.dataHoraAtual = dataHoraAtual;

            try {
                URL urlLogo = FacesContext.getCurrentInstance().getExternalContext().getResource("/resources/img/logo_musicoterapia.png");
                logo = Image.getInstance(urlLogo);
            } catch (BadElementException | IOException ex) {
                JSFUtils.mensagemErro("Erro ao carregar logo para impressão no relatório!");
            }
        }

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_LEFT,
                    new Phrase("Relatório de Pacientes", fonteCabecalho),
                    document.left(),
                    document.top(),
                    0);

            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_RIGHT,
                    new Phrase(dataHoraAtual.format(formatadorDataHoraRelatorio), fonteCabecalho),
                    document.right(),
                    document.top(),
                    0);

            try {
                logo.setAlignment(Element.ALIGN_CENTER);
                logo.setAbsolutePosition(document.right() / 2, document.top() - 20);
                logo.scalePercent(10, 10);
                writer.getDirectContent().addImage(logo);

                document.add(new Phrase("\n"));
            } catch (DocumentException ex) {
                JSFUtils.mensagemErro("Erro ao inserir imagem no relatório!");
            }

        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_RIGHT,
                    new Phrase("Página " + document.getPageNumber() + "\n", fonteCabecalho),
                    document.right(),
                    document.bottom() - 10,
                    0);
        }
    }

}
