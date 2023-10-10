package serializa_jackson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class SerializaYAML {
    public static void main(String[] args) throws Exception {
        Pessoa p1 = new Pessoa(1, "João");
        Pessoa p2 = new Pessoa(2, "Maria");
        List<Pessoa> lista = new ArrayList<Pessoa>();
        lista.add(p1);
        lista.add(p2);
        Pessoas p = new Pessoas(lista);
        File f = new File("pessoas.yaml");

        YAMLMapper xm = new YAMLMapper();
        xm.enable(SerializationFeature.INDENT_OUTPUT);
        xm.writeValue(f, p);
        System.out.println("Arquivo serializado com sucesso!");
    }
}


