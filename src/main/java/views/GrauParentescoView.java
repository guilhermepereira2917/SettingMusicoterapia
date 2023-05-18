package views;

import entities.GrauParentesco;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class GrauParentescoView extends View<GrauParentesco> {
    
    public GrauParentescoView() {
        super(GrauParentesco.class);
    }
    
}
