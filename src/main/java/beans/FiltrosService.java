package beans;

public abstract class FiltrosService {

    protected StringBuilder stringBuilder = new StringBuilder();
    protected boolean usarWhere;

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
}
