package entities;

import jakarta.persistence.*;
import utils.DateUtils;

import java.util.Date;

@Entity
@Table(name = "Consulta")
public class Consulta {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idTratamento", referencedColumnName = "id", nullable = false)
    private Tratamento tratamento;

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "horarioInicio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioInicio;

    @Column(name = "horarioTermino", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioTermino;

    @Column(name = "paga", nullable = false)
    private Boolean paga = false;

    @Column(name = "cancelada", nullable = false)
    private Boolean cancelada = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDiaDaSemana() {
        return DateUtils.getDescricaoDiaDaSemana(data);
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(Date horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    public Boolean getPaga() {
        return paga;
    }

    public void setPaga(Boolean paga) {
        this.paga = paga;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }
}
