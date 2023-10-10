package serializa_jackson;

import java.io.File;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class DesserializaYAML {
    public static void main(String[] args) throws Exception {
        File file = new File("pessoas.yaml");
        YAMLMapper mapper = new YAMLMapper();
        Pessoas p = mapper.readValue(file, Pessoas.class);
        System.out.println(p);
    }

}

