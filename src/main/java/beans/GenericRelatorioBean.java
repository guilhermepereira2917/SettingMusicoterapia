package beans;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.persistence.Transient;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import utils.JSFUtils;
import utils.RelatorioUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import static utils.RelatorioUtils.*;

public abstract class GenericRelatorioBean<T> implements Serializable {

    @Transient
    @Inject
    private VisualizadorPDFBean visualizadorPDF;

    private PdfPTable tabela;
    private BaseColor corCelula;

    protected abstract float[] getLargurasColunasRelatorio();

    protected abstract void imprimirCabecalho();

    protected abstract void imprimirLinha(T registro);

    public abstract void tirarRelatorio();

    public abstract String getNomeRelatorio();

    public abstract String getNomeArquivo();

    protected void tirarRelatorio(List<T> registros) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        if (registros.isEmpty()) {
            JSFUtils.mensagemAviso("Nenhum registro encontrado para impressão do relatório!");
            return;
        }

        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
            pdfWriter.setPageEvent(new EventosCabecalhoRelatorio(dataHoraAtual));

            document.open();
            float[] larguras = getLargurasColunasRelatorio();
            tabela = new PdfPTable(larguras.length);

            tabela.setTotalWidth(PageSize.A4.getWidth() - document.leftMargin() * 2);
            tabela.setLockedWidth(true);
            tabela.setWidths(larguras);

            imprimirCabecalho();

            tabela.setHeaderRows(1);

            for (int i = 0; i < registros.size(); i++) {
                T registro = registros.get(i);
                corCelula = i % 2 == 0 ? corCelulaPar : corCelulaImpar;

                imprimirLinha(registro);
            }

            document.add(tabela);
            document.close();

            StreamedContent conteudo = DefaultStreamedContent.builder().stream(() -> new ByteArrayInputStream(out.toByteArray()))
                    .contentType("application/pdf").name(String.format("%s-%s.pdf", getNomeArquivo(), dataHoraAtual.format(formatadorDataHoraNomeArquivo))).build();

            visualizadorPDF.setConteudo(conteudo);

            JSFUtils.abreDialog("/visualizadorPDF.xhtml");
        } catch (DocumentException ex) {
            JSFUtils.mensagemErro("Erro ao gerar relatório!");
        }
    }

    protected void adicionarCelulaCabecalho(String texto, int alinhamento) {
        PdfPCell celula = RelatorioUtils.criarCelula(texto, alinhamento, fonteCabecalho, corCabecalho);
        tabela.addCell(celula);
    }

    protected void adicionarCelulaLinha(String texto, int alinhamento) {
        PdfPCell celula = RelatorioUtils.criarCelula(texto, alinhamento, fonteTexto, corCelula);
        tabela.addCell(celula);
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
                    new Phrase(getNomeRelatorio(), fonteCabecalho),
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
