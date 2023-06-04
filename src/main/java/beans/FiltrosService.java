package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FiltrosService {

    protected StringBuilder stringBuilder = new StringBuilder();
    protected boolean usarWhere;

    public FiltrosService() {
        limparFiltros();
    }

    public String getSqlFiltrada(String prefixo) {
        stringBuilder.setLength(0);
        usarWhere = true;

        processarFiltros(prefixo);

        return stringBuilder.toString();
    }

    protected abstract void processarFiltros(String prefixo);

    public abstract void limparFiltros();

    protected String getWhereOuAnd() {
        if (!usarWhere) {
            return " and ";
        }

        usarWhere = false;
        return " where ";
    }

    protected void processaFiltroInteiro(String prefixo, Integer inteiro, String campo) {
        if (inteiro == null) {
            return;
        }

        stringBuilder.append(getWhereOuAnd());
        stringBuilder.append(prefixo);
        stringBuilder.append(".");
        stringBuilder.append(campo);
        stringBuilder.append(" = ");
        stringBuilder.append(inteiro);
    }

    protected void processaFiltroLike(String prefixo, String string, String campo) {
        if (string == null) {
            return;
        }

        stringBuilder.append(getWhereOuAnd());
        stringBuilder.append("upper(");
        stringBuilder.append(prefixo);
        stringBuilder.append(".");
        stringBuilder.append(campo);
        stringBuilder.append(") like concat(");
        stringBuilder.append("'%', upper('");
        stringBuilder.append(string);
        stringBuilder.append("'), '%')");
    }

    protected void processaFiltroVigencia(String prefixo, Date periodoInicial, Date periodoFinal, String primeiroCampo, String segundoCampo) {
        if (periodoInicial == null || periodoFinal == null) {
            return;
        }

        String periodoInicialFormatado = getDataJPQL(periodoInicial);
        String periodoFinalFormatado = getDataJPQL(periodoFinal);

        stringBuilder.append(getWhereOuAnd());
        stringBuilder.append(" (");

        processaClausulaVigencia(prefixo, periodoInicialFormatado, primeiroCampo, segundoCampo);
        stringBuilder.append(" or ");
        processaClausulaVigencia(prefixo, periodoFinalFormatado, primeiroCampo, segundoCampo);
        stringBuilder.append(" or ");
        processaClausulaVigencia(prefixo, periodoInicialFormatado, periodoFinalFormatado, primeiroCampo, segundoCampo);

        stringBuilder.append(")");
    }

    private String getDataJPQL(Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("{d '%s'}", simpleDateFormat.format(data));
    }

    private void processaClausulaVigencia(String prefixo, String dataFormatada, String primeiroCampo, String segundoCampo) {
        stringBuilder.append("(");
        stringBuilder.append(prefixo);
        stringBuilder.append(".");
        stringBuilder.append(primeiroCampo);
        stringBuilder.append(" >= ");
        stringBuilder.append(dataFormatada);
        stringBuilder.append(" and (");
        stringBuilder.append(prefixo);
        stringBuilder.append(".");
        stringBuilder.append(segundoCampo);
        stringBuilder.append(" is null or ");
        stringBuilder.append(dataFormatada);
        stringBuilder.append(" <= ");
        stringBuilder.append(prefixo);
        stringBuilder.append(".");
        stringBuilder.append(segundoCampo);
        stringBuilder.append(")");
        stringBuilder.append(")");
    }

    private void processaClausulaVigencia(String prefixo, String primeiraDataFormatada, String segundaDataFormatada, String primeiroCampo, String segundoCampo) {
        stringBuilder.append("(");
        stringBuilder.append(primeiraDataFormatada);
        stringBuilder.append(" <= ");
        stringBuilder.append(prefixo);
        stringBuilder.append(".");
        stringBuilder.append(primeiroCampo);
        stringBuilder.append(" and ");
        stringBuilder.append(segundaDataFormatada);
        stringBuilder.append(" >= ");
        stringBuilder.append(prefixo);
        stringBuilder.append(".");
        stringBuilder.append(segundoCampo);
        stringBuilder.append(")");
    }
}
