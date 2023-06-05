package converter;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Named
@RequestScoped
@FacesConverter("bigDecimalConverter")
public class BigDecimalConverter implements Converter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object objeto) {
        if (objeto == null) {
            return null;
        }

        BigDecimal bigDecimal = (BigDecimal) objeto;
        bigDecimal = bigDecimal.setScale(2);

        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);

        return numberFormat.format(bigDecimal);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valor) {
        if (valor == null) {
            return null;
        }

        valor = valor.replaceAll("[^0-9]", "");
        if (valor.isBlank()) {
            return null;
        }

        return new BigDecimal(valor).divide(BigDecimal.valueOf(100));
    }
}
