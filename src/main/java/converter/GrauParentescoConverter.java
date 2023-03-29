package converter;

import beans.EntityManager;
import entities.GrauParentesco;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("grauParentescoConverter")
public class GrauParentescoConverter implements Converter, Serializable {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object objeto) {
        if (objeto == null) {
            return null;
        }

        GrauParentesco grauParentesco = (GrauParentesco) objeto;
        return String.valueOf(grauParentesco.getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }

        try {
            return EntityManager.procurar(Integer.parseInt(valor), GrauParentesco.class);
        } catch (NumberFormatException ex) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Grau de Parentesco Inválido!"));
        }
    }
}
