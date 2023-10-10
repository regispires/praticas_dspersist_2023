package br.ufc.quixada.dspersist.modulo1;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "contatos")
public class Pessoas {
    public Pessoas() {}
    
    public Pessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @JacksonXmlElementWrapper(localName = "pessoas")
    @JacksonXmlProperty(localName = "pessoa")
    private List<Pessoa> pessoas;

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoa(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public String toString() {
        return this.pessoas.toString();
    }

}
