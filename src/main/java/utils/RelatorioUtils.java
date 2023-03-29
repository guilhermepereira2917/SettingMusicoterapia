package utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import java.time.format.DateTimeFormatter;

public abstract class RelatorioUtils {

    public final static BaseColor corCabecalho = new BaseColor(133, 197, 91);
    public final static BaseColor corCelulaPar = new BaseColor(246, 255, 240);
    public final static BaseColor corCelulaImpar = new BaseColor(223, 247, 207);

    public final static Font fonteTexto = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
    public final static Font fonteCabecalho = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);

    public final static DateTimeFormatter formatadorDataHoraRelatorio = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public final static DateTimeFormatter formatadorDataHoraNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");

    public static PdfPCell criarCelula(String texto, int alinhamento, Font fonte, BaseColor cor) {
        PdfPCell celula = new PdfPCell(new Phrase(texto, fonte));       
        
        celula.setHorizontalAlignment(alinhamento);
        celula.setBackgroundColor(cor);
        celula.setBorder(0);
        celula.setPaddingTop(4f);
        celula.setPaddingLeft(6f);
        celula.setPaddingRight(6f);
        celula.setPaddingBottom(4f);

        return celula;
    }
}
