package br.ufc.quixada;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Config {

    private static Properties props = new Properties();
    
    public static Properties getConfig() {
        try {
            log.info("Carregando configurações...");
            props.load(Config.class.getResourceAsStream("/config.properties"));
            log.info("Configurações carregadas com sucesso!");
            log.info("propriedades: {}", props.stringPropertyNames());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return props;
    }
}
