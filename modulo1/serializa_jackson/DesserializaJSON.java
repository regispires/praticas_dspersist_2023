package serializa_jackson;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DesserializaJSON {
    public static void main(String[] args) throws Exception {
        File file = new File("pessoas.json");
        ObjectMapper mapper = new ObjectMapper();
        Pessoas p = mapper.readValue(file, Pessoas.class);
        System.out.println(p);
    }

}


