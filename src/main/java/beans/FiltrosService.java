package beans;

import utils.DateUtils;

import java.util.Date;

public abstract class FiltrosService {

    protected StringBuilder stringBuilder = new StringBuilder();
    protected boolean usarWhere;

    public FiltrosService() {
        limparFiltros();
    }

    public String getSqlFiltrada() {
        usarWhere = true;

        processarFiltros();

        String filtros = stringBuilder.toString();
        stringBuilder.setLength(0);

        return filtros;
    }

    protected abstract void processarFiltros();

    public abstract void limparFiltros();

    protected String getWhereOuAnd() {
        if (!usarWhere) {
            return " and ";
        }

        usarWhere = false;
        return " where ";
    }

    protected void adicionarWhereOuAnd() {
        stringBuilder.append(getWhereOuAnd());
    }

    protected void adicionarOr() {
        stringBuilder.append(" or ");
    }

    protected void abrirParenteses() {
        stringBuilder.append("(");
    }

    protected void fecharParenteses() {
        stringBuilder.append(")");
    }

    protected void processarFiltro(String filtro) {
        stringBuilder.append(filtro);
    }

    protected void processaFiltroInteiro(Integer inteiro, String campo) {
        if (inteiro == null) {
            return;
        }

        adicionarWhereOuAnd();
        stringBuilder.append(campo);
        stringBuilder.append(" = ");
        stringBuilder.append(inteiro);
    }

    protected void processaFiltroString(String string, String campo) {
        if (string == null) {
            return;
        }

        adicionarWhereOuAnd();
        stringBuilder.append(campo);
        stringBuilder.append(" = '");
        stringBuilder.append(string);
        stringBuilder.append("'");
    }

    protected void processaFiltroLike(String string, String campo) {
        if (string == null) {
            return;
        }

        adicionarWhereOuAnd();
        stringBuilder.append("upper(");
        stringBuilder.append(campo);
        stringBuilder.append(") like concat(");
        stringBuilder.append("'%', upper('");
        stringBuilder.append(string);
        stringBuilder.append("'), '%')");
    }

    protected void processaFiltroBooleano(Boolean booleano, String campo) {
        if (booleano == null) {
            return;
        }

        adicionarWhereOuAnd();
        stringBuilder.append(campo);
        stringBuilder.append(" = ");
        stringBuilder.append(booleano ? "TRUE" : "FALSE");
    }

    protected void processaFiltroData(Date data, String campo) {
        if (data == null) {
            return;
        }

        adicionarWhereOuAnd();
        stringBuilder.append(campo);
        stringBuilder.append(" = ");
        stringBuilder.append(DateUtils.getDataFormatadaJPQL(data));
    }

    protected void processaFiltroHorario(Date horario, String campo, boolean colocarAnd) {
        if (horario == null) {
            return;
        }

        if (colocarAnd) {
            adicionarWhereOuAnd();
        }

        stringBuilder.append(campo);
        stringBuilder.append(" = ");
        stringBuilder.append(DateUtils.getHoraFormatadaJPQL(horario));
    }

    protected <T> void processaFiltroIn(String camposIn, String campo) {
        if (camposIn == null || camposIn.isBlank()) {
            return;
        }

        adicionarWhereOuAnd();
        stringBuilder.append(campo);
        stringBuilder.append(" IN ");
        stringBuilder.append(camposIn);
    }

    protected void processaFiltroBetweenData(Date periodoInicial, Date periodoFinal, String campo) {
        if (periodoInicial == null || periodoFinal == null) {
            return;
        }

        String periodoInicialFormatado = DateUtils.getDataFormatadaJPQL(periodoInicial);
        String periodoFinalFormatado = DateUtils.getDataFormatadaJPQL(periodoFinal);

        adicionarWhereOuAnd();
        stringBuilder.append(campo);
        stringBuilder.append(" between ");
        stringBuilder.append(periodoInicialFormatado);
        stringBuilder.append(" and ");
        stringBuilder.append(periodoFinalFormatado);
    }

    protected void processaFiltroBetweenHorario(Date horario, String primeiroCampo, String segundoCampo) {
        if (horario == null) {
            return;
        }

        String horarioFormatado = DateUtils.getHoraFormatadaJPQL(horario);

        processaFiltroBetween(horarioFormatado, primeiroCampo, segundoCampo);
    }

    protected void processaFiltroBetween(String periodoInicialFormatado, String primeiroCampo, String segundoCampo) {
        adicionarWhereOuAnd();
        stringBuilder.append(periodoInicialFormatado);
        stringBuilder.append(" between ");
        stringBuilder.append(primeiroCampo);
        stringBuilder.append(" and ");
        stringBuilder.append(segundoCampo);
    }

    protected void processaFiltroVigenciaData(Date periodoInicial, Date periodoFinal, String primeiroCampo, String segundoCampo) {
        if (periodoInicial == null || periodoFinal == null) {
            return;
        }

        String periodoInicialFormatado = DateUtils.getDataFormatadaJPQL(periodoInicial);
        String periodoFinalFormatado = DateUtils.getDataFormatadaJPQL(periodoFinal);

        processaFiltroVigencia(periodoInicialFormatado, periodoFinalFormatado, primeiroCampo, segundoCampo);
    }
    
    protected void processaFiltroVigencia(String periodoInicialFormatado, String periodoFinalFormatado, String primeiroCampo, String segundoCampo) {
        adicionarWhereOuAnd();
        stringBuilder.append(" (");

        processaClausulaVigencia(periodoInicialFormatado, primeiroCampo, segundoCampo);
        stringBuilder.append(" or ");
        processaClausulaVigencia(periodoFinalFormatado, primeiroCampo, segundoCampo);
        stringBuilder.append(" or ");
        processaClausulaVigencia(periodoInicialFormatado, periodoFinalFormatado, primeiroCampo, segundoCampo);

        stringBuilder.append(")");
    }

    private void processaClausulaVigencia(String dataFormatada, String primeiroCampo, String segundoCampo) {
        stringBuilder.append("(");
        stringBuilder.append(dataFormatada);
        stringBuilder.append(" >= ");
        stringBuilder.append(primeiroCampo);
        stringBuilder.append(" and (");
        stringBuilder.append(segundoCampo);
        stringBuilder.append(" is null or ");
        stringBuilder.append(dataFormatada);
        stringBuilder.append(" <= ");
        stringBuilder.append(segundoCampo);
        stringBuilder.append(")");
        stringBuilder.append(")");
    }

    private void processaClausulaVigencia(String primeiraDataFormatada, String segundaDataFormatada, String primeiroCampo, String segundoCampo) {
        stringBuilder.append("(");
        stringBuilder.append(primeiraDataFormatada);
        stringBuilder.append(" <= ");
        stringBuilder.append(primeiroCampo);
        stringBuilder.append(" and ");
        stringBuilder.append(segundaDataFormatada);
        stringBuilder.append(" >= ");
        stringBuilder.append(segundoCampo);
        stringBuilder.append(")");
    }

    protected void adicionaOrdenacao(String campos) {
        if (campos == null || campos.isBlank()) {
            return;
        }

        stringBuilder.append(" order by ");
        stringBuilder.append(campos);
    }
}
