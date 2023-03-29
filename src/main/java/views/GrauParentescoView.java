package views;

import entities.GrauParentesco;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GrauParentescoView extends View<GrauParentesco> {
    
    public GrauParentescoView() {
        super(GrauParentesco.class);
    }
    
}
