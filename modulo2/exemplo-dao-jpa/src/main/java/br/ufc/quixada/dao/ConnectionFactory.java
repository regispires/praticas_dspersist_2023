package br.ufc.quixada.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.ufc.quixada.Config;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionFactory {

	public static Properties props;
	public static DataSource ds;

	static {
		props = Config.getConfig();
		log.info("database.url: {}", props.getProperty("database.url"));
		log.info("database.username: {}", props.getProperty("database.username"));
		log.info("database.password: {}", props.getProperty("database.password"));
		log.info("persistence.unit: {}", props.getProperty("persistence.unit"));
	}

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	public static DataSource getDataSource() throws SQLException {
		if (ds == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(props.getProperty("database.url"));
			config.setUsername(props.getProperty("database.username"));
			config.setPassword(props.getProperty("database.password"));
			String prop = props.getProperty("database.max_poll_size");
			if (prop != null)
				config.setMaximumPoolSize(Integer.parseInt(prop));
			prop = props.getProperty("database.min_idle");
			if (prop != null)
				config.setMinimumIdle(Integer.parseInt(props.getProperty("database.min_idle")));
			ds = new HikariDataSource(config);
		}
		return ds;
	}

}
