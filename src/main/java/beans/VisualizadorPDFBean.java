/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class VisualizadorPDFBean implements Serializable {

    private StreamedContent conteudo;

    public StreamedContent getConteudo() {
        return conteudo;
    }

    public void setConteudo(StreamedContent conteudo) {
        this.conteudo = conteudo;
    }

}
