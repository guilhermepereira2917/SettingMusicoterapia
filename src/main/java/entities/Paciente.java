package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import utils.DateUtils;

@Entity
@Table(name = "Paciente")
public class Paciente implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "inativo")
    private Boolean inativo = false;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "apelido", length = 50)
    private String apelido;

    @Column(name = "dataAdmissao")
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao = new Date();

    @Column(name = "dataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "sexo", length = 1, nullable = false)
    private Character sexo;

    @OneToOne
    @JoinColumn(name = "idPaisNacionalidade")
    private Pais paisNacionalidade;

    @OneToOne
    @JoinColumn(name = "idCidadeNaturalidade")
    private Cidade cidadeNaturalidade;

    @OneToOne
    @JoinColumn(name = "idProfissao")
    private Profissao profissao;

    @OneToOne
    @JoinColumn(name = "idReligiao")
    private Religiao religiao;

    @Column(name = "sempreMoraramNoBrasil")
    private Boolean sempreMoraramNoBrasil = false;

    @Column(name = "paisMoramJuntos")
    private Boolean paisMoramJuntos = false;

    @Column(name = "canta")
    private Boolean canta = false;

    @Column(name = "prematuro")
    private Boolean prematuro = false;

    @Column(name = "semanasPrematuro")
    private Integer semanasPrematuro;

    @Column(name = "nascimentoComComplicacoes")
    private Boolean nascimentoComComplicacoes = false;

    @Column(name = "observacoesNascimento")
    private String observacoesNascimento;

    @Column(name = "hospitalizado")
    private Boolean hospitalizado = false;

    @Column(name = "semanasHospitalizado")
    private Integer semanasHospitalizado;

    @Column(name = "idadeHospitalizado")
    private Integer idadeHospitalizado;

    @Column(name = "observacoesHospitalizado")
    private String observacoesHospitalizado;

    @Column(name = "observacoesHistoricoMedico")
    private String observacoesHistoricoMedico;

    @Column(name = "mesesComecouSentar")
    private Integer mesesComecouSentar;

    @Column(name = "mesesComecouEngatinhar")
    private Integer mesesComecouEngatinhar;

    @Column(name = "mesesComecouFalar")
    private Integer mesesComecouFalar;

    @Column(name = "idadeBanheiroSozinho")
    private Integer idadeBanheiroSozinho;

    @Column(name = "compreendeGestosInteracao")
    private Boolean compreendeGestosInteracao = false;

    @Column(name = "jaFezMusicoterapia")
    private Boolean jaFezMusicoterapia = false;

    @Column(name = "musicoterapiaLocal")
    private String musicoterapiaLocal;

    // musicoterapiaTempo
    @Column(name = "jaTeveAulasMusica")
    private Boolean jaTeveAulasMusica = false;

    @Column(name = "aulasMusicaLocal")
    private String aulasMusicaLocal;

    // aulasMusicaTempo
    @Column(name = "habitoOuvirMusica")
    private Boolean habitoOuvirMusica = false;

    @Column(name = "ouvirMusicaLocal")
    private String ouvirMusicaLocal;

    @Column(name = "ouvirMusicaQuando")
    private String ouvirMusicaQuando;

    @Column(name = "musicasPreferidas")
    private String musicasPreferidas;

    @Column(name = "musicasOdiadas")
    private String musicasOdiadas;

    @Column(name = "interageOuvindo")
    private Boolean interageOuvindo = false;

    @Column(name = "interageCantando")
    private Boolean interageCantando = false;

    @Column(name = "interageDancando")
    private Boolean interageDancando = false;

    @Column(name = "observacoesAmbienteFamiliar", length = 255)
    private String observacoesAmbienteFamiliar;

    @Column(name = "observacoesHistoricoDesenvolvimento", length = 255)
    private String observacoesHistoricoDesenvolvimento;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamiliarPaciente> familiares = new ArrayList();

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "enderecoRua", length = 50)
    private String enderecoRua;

    @Column(name = "enderecoComplemento", length = 50)
    private String enderecoComplemento;

    @Column(name = "enderecoBairro", length = 50)
    private String enderecoBairro;

    @Column(name = "enderecoNumero", length = 10)
    private String enderecoNumero;

    @Column(name = "enderecoCep", length = 10)
    private String enderecoCep;
    
    @Column(name = "comunicaVerbalmente", length = 1)
    private Character comunicaVerbalmente = 'N';

    @OneToOne
    @JoinColumn(name = "idEnderecoCidade")
    private Cidade enderecoCidade;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefonePaciente> telefones = new ArrayList();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        return Objects.equals(this.id, other.id);
    }

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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Pais getPaisNacionalidade() {
        return paisNacionalidade;
    }

    public void setPaisNacionalidade(Pais paisNacionalidade) {
        this.paisNacionalidade = paisNacionalidade;
    }

    public Boolean getSempreMoraramNoBrasil() {
        return sempreMoraramNoBrasil;
    }

    public void setSempreMoraramNoBrasil(Boolean sempreMoraramNoBrasil) {
        this.sempreMoraramNoBrasil = sempreMoraramNoBrasil;
    }

    public Boolean getPaisMoramJuntos() {
        return paisMoramJuntos;
    }

    public void setPaisMoramJuntos(Boolean paisMoramJuntos) {
        this.paisMoramJuntos = paisMoramJuntos;
    }

    public Boolean getCanta() {
        return canta;
    }

    public void setCanta(Boolean canta) {
        this.canta = canta;
    }

    public Boolean getPrematuro() {
        return prematuro;
    }

    public void setPrematuro(Boolean prematuro) {
        this.prematuro = prematuro;
    }

    public Integer getSemanasPrematuro() {
        return semanasPrematuro;
    }

    public void setSemanasPrematuro(Integer semanasPrematuro) {
        this.semanasPrematuro = semanasPrematuro;
    }

    public Boolean getNascimentoComComplicacoes() {
        return nascimentoComComplicacoes;
    }

    public void setNascimentoComComplicacoes(Boolean nascimentoComComplicacoes) {
        this.nascimentoComComplicacoes = nascimentoComComplicacoes;
    }

    public String getObservacoesNascimento() {
        return observacoesNascimento;
    }

    public void setObservacoesNascimento(String observacoesNascimento) {
        this.observacoesNascimento = observacoesNascimento;
    }

    public Boolean getHospitalizado() {
        return hospitalizado;
    }

    public void setHospitalizado(Boolean hospitalizado) {
        this.hospitalizado = hospitalizado;
    }

    public Integer getSemanasHospitalizado() {
        return semanasHospitalizado;
    }

    public void setSemanasHospitalizado(Integer semanasHospitalizado) {
        this.semanasHospitalizado = semanasHospitalizado;
    }

    public Integer getIdadeHospitalizado() {
        return idadeHospitalizado;
    }

    public void setIdadeHospitalizado(Integer idadeHospitalizado) {
        this.idadeHospitalizado = idadeHospitalizado;
    }

    public String getObservacoesHospitalizado() {
        return observacoesHospitalizado;
    }

    public void setObservacoesHospitalizado(String observacoesHospitalizado) {
        this.observacoesHospitalizado = observacoesHospitalizado;
    }

    public String getObservacoesHistoricoMedico() {
        return observacoesHistoricoMedico;
    }

    public void setObservacoesHistoricoMedico(String observacoesHistoricoMedico) {
        this.observacoesHistoricoMedico = observacoesHistoricoMedico;
    }

    public Integer getIdadeBanheiroSozinho() {
        return idadeBanheiroSozinho;
    }

    public void setIdadeBanheiroSozinho(Integer idadeBanheiroSozinho) {
        this.idadeBanheiroSozinho = idadeBanheiroSozinho;
    }

    public Boolean getCompreendeGestosInteracao() {
        return compreendeGestosInteracao;
    }

    public void setCompreendeGestosInteracao(Boolean compreendeGestosInteracao) {
        this.compreendeGestosInteracao = compreendeGestosInteracao;
    }

    public Boolean getJaFezMusicoterapia() {
        return jaFezMusicoterapia;
    }

    public void setJaFezMusicoterapia(Boolean jaFezMusicoterapia) {
        this.jaFezMusicoterapia = jaFezMusicoterapia;
    }

    public String getMusicoterapiaLocal() {
        return musicoterapiaLocal;
    }

    public void setMusicoterapiaLocal(String musicoterapiaLocal) {
        this.musicoterapiaLocal = musicoterapiaLocal;
    }

    public Boolean getJaTeveAulasMusica() {
        return jaTeveAulasMusica;
    }

    public void setJaTeveAulasMusica(Boolean jaTeveAulasMusica) {
        this.jaTeveAulasMusica = jaTeveAulasMusica;
    }

    public String getAulasMusicaLocal() {
        return aulasMusicaLocal;
    }

    public void setAulasMusicaLocal(String aulasMusicaLocal) {
        this.aulasMusicaLocal = aulasMusicaLocal;
    }

    public Boolean getHabitoOuvirMusica() {
        return habitoOuvirMusica;
    }

    public void setHabitoOuvirMusica(Boolean habitoOuvirMusica) {
        this.habitoOuvirMusica = habitoOuvirMusica;
    }

    public String getOuvirMusicaLocal() {
        return ouvirMusicaLocal;
    }

    public void setOuvirMusicaLocal(String ouvirMusicaLocal) {
        this.ouvirMusicaLocal = ouvirMusicaLocal;
    }

    public String getOuvirMusicaQuando() {
        return ouvirMusicaQuando;
    }

    public void setOuvirMusicaQuando(String ouvirMusicaQuando) {
        this.ouvirMusicaQuando = ouvirMusicaQuando;
    }

    public String getMusicasPreferidas() {
        return musicasPreferidas;
    }

    public void setMusicasPreferidas(String musicasPreferidas) {
        this.musicasPreferidas = musicasPreferidas;
    }

    public String getMusicasOdiadas() {
        return musicasOdiadas;
    }

    public void setMusicasOdiadas(String musicasOdiadas) {
        this.musicasOdiadas = musicasOdiadas;
    }

    public Boolean getInterageOuvindo() {
        return interageOuvindo;
    }

    public void setInterageOuvindo(Boolean interageOuvindo) {
        this.interageOuvindo = interageOuvindo;
    }

    public Boolean getInterageCantando() {
        return interageCantando;
    }

    public void setInterageCantando(Boolean interageCantando) {
        this.interageCantando = interageCantando;
    }

    public Boolean getInterageDancando() {
        return interageDancando;
    }

    public void setInterageDancando(Boolean interageDancando) {
        this.interageDancando = interageDancando;
    }

    public String getObservacoesAmbienteFamiliar() {
        return observacoesAmbienteFamiliar;
    }

    public void setObservacoesAmbienteFamiliar(String observacoesAmbienteFamiliar) {
        this.observacoesAmbienteFamiliar = observacoesAmbienteFamiliar;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return DateUtils.calculaIdade(dataNascimento);
    }

    public Cidade getCidadeNaturalidade() {
        return cidadeNaturalidade;
    }

    public void setCidadeNaturalidade(Cidade cidadeNaturalidade) {
        this.cidadeNaturalidade = cidadeNaturalidade;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public Religiao getReligiao() {
        return religiao;
    }

    public void setReligiao(Religiao religiao) {
        this.religiao = religiao;
    }

    public List<FamiliarPaciente> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<FamiliarPaciente> familiares) {
        this.familiares = familiares;
    }

    public String getObservacoesHistoricoDesenvolvimento() {
        return observacoesHistoricoDesenvolvimento;
    }

    public void setObservacoesHistoricoDesenvolvimento(String observacoesHistoricoDesenvolvimento) {
        this.observacoesHistoricoDesenvolvimento = observacoesHistoricoDesenvolvimento;
    }

    public Integer getMesesComecouSentar() {
        return mesesComecouSentar;
    }

    public void setMesesComecouSentar(Integer mesesComecouSentar) {
        this.mesesComecouSentar = mesesComecouSentar;
    }

    public Integer getMesesComecouEngatinhar() {
        return mesesComecouEngatinhar;
    }

    public void setMesesComecouEngatinhar(Integer mesesComecouEngatinhar) {
        this.mesesComecouEngatinhar = mesesComecouEngatinhar;
    }

    public Integer getMesesComecouFalar() {
        return mesesComecouFalar;
    }

    public void setMesesComecouFalar(Integer mesesComecouFalar) {
        this.mesesComecouFalar = mesesComecouFalar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnderecoRua() {
        return enderecoRua;
    }

    public void setEnderecoRua(String enderecoRua) {
        this.enderecoRua = enderecoRua;
    }

    public String getEnderecoComplemento() {
        return enderecoComplemento;
    }

    public void setEnderecoComplemento(String enderecoComplemento) {
        this.enderecoComplemento = enderecoComplemento;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public String getEnderecoNumero() {
        return enderecoNumero;
    }

    public void setEnderecoNumero(String enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }

    public String getEnderecoCep() {
        return enderecoCep;
    }

    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }

    public Cidade getEnderecoCidade() {
        return enderecoCidade;
    }

    public void setEnderecoCidade(Cidade enderecoCidade) {
        this.enderecoCidade = enderecoCidade;
    }

    public List<TelefonePaciente> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefonePaciente> telefones) {
        this.telefones = telefones;
    }

    public Character getComunicaVerbalmente() {
        return comunicaVerbalmente;
    }

    public void setComunicaVerbalmente(Character comunicaVerbalmente) {
        this.comunicaVerbalmente = comunicaVerbalmente;
    }

    public Boolean getInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

}
