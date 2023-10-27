package br.ufc.quixada;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties props = new Properties();
    
    public static Properties getConfig() {
        try {
            props.load(Config.class.getResourceAsStream("/config.properties"));
            System.out.println("Configurações carregadas com sucesso!");
            System.out.println(props.stringPropertyNames());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
