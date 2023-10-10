package br.ufc.quixada.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.ufc.quixada.Config;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException {
		Properties props = Config.getConfig();
		System.out.println(props.getProperty("database.url"));
		System.out.println(props.getProperty("database.username"));
		System.out.println(props.getProperty("database.password"));
		return DriverManager.getConnection(props.getProperty("database.url"),
			props.getProperty("database.username"), 
			props.getProperty("database.password"));
	}
}
