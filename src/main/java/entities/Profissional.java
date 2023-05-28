package entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Profissional")
public class Profissional implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "idProfissao")
    private Profissao profissao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

}