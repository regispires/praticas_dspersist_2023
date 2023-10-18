package br.ufc.quixada.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Cliente;
import lombok.extern.slf4j.Slf4j;

@Repository
@Primary
@Slf4j
public class ClienteSpringJDBCDAO implements ClienteDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public ClienteSpringJDBCDAO() {
	}
	
	public void save(Cliente entity) {
		String insert_sql = "insert into clientes (cpf, nome, fone, renda) values (:cpf, :nome, :fone, :renda)";
		String update_sql = "update clientes set cpf = :cpf, nome = :nome, fone = :fone, renda = :renda where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("cpf", entity.getCpf())
			.addValue("nome", entity.getNome())
			.addValue("fone", entity.getFone())
			.addValue("renda", entity.getRenda());
		if (entity.getId() == null) {
			jdbcTemplate.update(insert_sql, params);
		} else {
			params.addValue("id", entity.getId());
			jdbcTemplate.update(update_sql, params);
		}
	}

	public void delete(int id) {
		String sql = "delete from clientes where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("id", id);
		jdbcTemplate.update(sql, params);
	}

	public Cliente find(int id) {
		Cliente cliente = null;
		try {
			String sql = "select id, cpf, nome, fone, renda from clientes where id = :id";
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);
			cliente = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> map(rs));
		} catch (EmptyResultDataAccessException e) {
			log.debug(e.getMessage());
		}
		return cliente;
	}

	private Cliente map(ResultSet rs) throws SQLException {
		Cliente cl = new Cliente();
		cl.setId(rs.getInt("id"));
		cl.setCpf(rs.getString("cpf"));
		cl.setNome(rs.getString("nome"));
		cl.setFone(rs.getString("fone"));
		cl.setRenda(rs.getBigDecimal("renda").doubleValue());
		return cl;
	}

	public List<Cliente> find() {
			String sql = "select id, cpf, nome, fone, renda from clientes";
			return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
	}

	public Cliente findByCpf(String cpf) {
		Cliente cliente = null;
		try {
			String sql = "select id, cpf, nome, fone, renda from clientes where cpf = :cpf";
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("cpf", cpf);
			cliente = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> map(rs));
		} catch (EmptyResultDataAccessException e) {
			log.debug(e.getMessage());
		}
		return cliente;
}

	public List<Cliente> findByNome(String str) {
		String sql = "select id, cpf, nome, fone, renda from clientes where upper(nome) like :nome";
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("nome", "%" + str.toUpperCase() + "%");
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> map(rs));
	}
	
}
