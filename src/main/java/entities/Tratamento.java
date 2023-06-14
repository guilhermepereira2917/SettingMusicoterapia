package entities;

import jakarta.persistence.*;
import utils.BigDecimalUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Tratamento")
public class Tratamento implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idPaciente", referencedColumnName = "id", nullable = false)
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "idProfissional", nullable = false)
    private Profissional profissional;

    @Column(name = "inicioVigencia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inicioVigencia;

    @Column(name = "finalVigencia")
    @Temporal(TemporalType.DATE)
    private Date finalVigencia;

    @Column(name = "horarioSessao")
    @Temporal(TemporalType.TIME)
    private Date horarioSessao;

    @Column(name = "duracaoMinutosSessao", nullable = false)
    private Integer duracaoMinutosSessao;

    @Column(name = "frequenciaSessaoDias", nullable = false)
    private Integer frequenciaSessaoDias;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "observacoes")
    private String observacoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFinalVigencia() {
        return finalVigencia;
    }

    public void setFinalVigencia(Date finalVigencia) {
        this.finalVigencia = finalVigencia;
    }

    public Date getHorarioSessao() {
        return horarioSessao;
    }

    public void setHorarioSessao(Date horarioSessao) {
        this.horarioSessao = horarioSessao;
    }

    public Integer getDuracaoMinutosSessao() {
        return duracaoMinutosSessao;
    }

    public void setDuracaoMinutosSessao(Integer duracaoMinutosSessao) {
        this.duracaoMinutosSessao = duracaoMinutosSessao;
    }

    public Integer getFrequenciaSessaoDias() {
        return frequenciaSessaoDias;
    }

    public void setFrequenciaSessaoDias(Integer frequenciaSessaoDias) {
        this.frequenciaSessaoDias = frequenciaSessaoDias;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getValorFormatado() {
        return BigDecimalUtils.getValorFormatado(valor);
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
