package beans;

public enum BuscaEnum {
    BUSCA_PAISES("buscaPaises"),
    BUSCA_CIDADES("buscaCidades"),
    BUSCA_PROFISSOES("buscaProfissoes"),
    BUSCA_RELIGIOES("buscaReligioes"),
    BUSCA_FAMILIARES("buscaFamiliares"),
    BUSCA_PACIENTES("buscaPacientes"),
    BUSCA_PROFISSIONAIS("buscaProfissionais.xhtml");

    private final String caminhoMenu;
    
    BuscaEnum(String caminhoMenu) {
        this.caminhoMenu = caminhoMenu;
    }
    
    public String getCaminhoMenu() {
        return this.caminhoMenu;
    }
}
