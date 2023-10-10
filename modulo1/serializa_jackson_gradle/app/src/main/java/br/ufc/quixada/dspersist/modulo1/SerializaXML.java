package br.ufc.quixada.dspersist.modulo1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class SerializaXML {
    public static void main(String[] args) throws Exception {
        Pessoa p1 = new Pessoa(1, "João");
        Pessoa p2 = new Pessoa(2, "Maria");
        List<Pessoa> lista = new ArrayList<Pessoa>();
        lista.add(p1);
        lista.add(p2);
        Pessoas p = new Pessoas(lista);
        File f = new File("pessoas.xml");

        XmlMapper xm = new XmlMapper();
        xm.enable(SerializationFeature.INDENT_OUTPUT);
        xm.writeValue(f, p);
        System.out.println("Arquivo serializado com sucesso!");
    }
}


