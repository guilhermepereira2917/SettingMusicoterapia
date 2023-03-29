package validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("NomeValidator")
public class NomeValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        if (t.toString().isBlank()) {
            FacesMessage message = new FacesMessage("Nome Inválido!", "Nome não pode ser vazio!");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
