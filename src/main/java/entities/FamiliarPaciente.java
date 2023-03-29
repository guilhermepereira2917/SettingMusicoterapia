package entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FamiliarPaciente")
public class FamiliarPaciente implements Serializable {
    @EmbeddedId
    private FamiliarPacientePK id;
    
    @ManyToOne
    @MapsId("idPaciente")
    @JoinColumn(name = "idPaciente", referencedColumnName = "id")
    private Paciente paciente;
    
    @ManyToOne
    @MapsId("idFamiliar")
    @JoinColumn(name = "idFamiliar", referencedColumnName = "id")
    private Familiar familiar;
    
    @OneToOne
    @JoinColumn(name = "idGrauParentesco")
    private GrauParentesco grauParentesco;
    
    @Column(name = "moraComPaciente")
    private Boolean moraComPaciente = false;
    
    @Column(name = "respondePelaMusicoterapia")
    private Boolean respondePelaMusicoterapia = false;

    public FamiliarPacientePK getId() {
        return id;
    }

    public void setId(FamiliarPacientePK id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Familiar getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }

    public GrauParentesco getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(GrauParentesco grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public Boolean getMoraComPaciente() {
        return moraComPaciente;
    }

    public void setMoraComPaciente(Boolean moraComPaciente) {
        this.moraComPaciente = moraComPaciente;
    }

    public Boolean getRespondePelaMusicoterapia() {
        return respondePelaMusicoterapia;
    }

    public void setRespondePelaMusicoterapia(Boolean respondePelaMusicoterapia) {
        this.respondePelaMusicoterapia = respondePelaMusicoterapia;
    }
    
    
}
