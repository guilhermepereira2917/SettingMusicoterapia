package converter;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@Named
@RequestScoped
@FacesConverter("somenteNumerosConverter")
public class SomenteNumerosConverter implements Converter, Serializable {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object objeto) {
        if (objeto == null) {
            return null;
        }
        
        return String.valueOf(objeto.toString());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valor) {
        if (valor == null) {
            return null;
        }

        valor = valor.replaceAll("[^0-9]", "");       
        
        if (valor.isBlank()) {
            return null;
        } else {
            return valor;
        }
    }
}
