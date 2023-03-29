package validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        FacesMessage message = null;
        String email = t.toString();
        
        if (email.isBlank()) {
            message = new FacesMessage("Email Inválido!", "Email não pode ser vazio!");
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            message = new FacesMessage("Email Inválido!", "Formato de email inválido!");
        }
        
        if (message != null) {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
