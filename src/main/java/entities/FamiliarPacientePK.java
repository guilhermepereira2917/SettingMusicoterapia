package entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FamiliarPacientePK implements Serializable {

    @Column(name = "idPaciente", insertable = false, updatable = false)
    private Integer idPaciente;

    @Column(name = "idFamiliar", insertable = false, updatable = false)
    private Integer idFamiliar;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(Integer idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

}
