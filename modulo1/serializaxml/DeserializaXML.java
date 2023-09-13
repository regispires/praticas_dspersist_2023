package serializaxml;

import java.io.File;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class DeserializaXML {
    public static void main(String[] args) throws Exception {
        File file = new File("pessoas.xml");
        XmlMapper xmlMapper = new XmlMapper();
        Pessoas p = xmlMapper.readValue(file, Pessoas.class);
        System.out.println(p);
    }

}


