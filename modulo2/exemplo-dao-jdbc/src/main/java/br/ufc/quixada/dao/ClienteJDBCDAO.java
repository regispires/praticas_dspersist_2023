package br.ufc.quixada.dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.entity.Cliente;

public class ClienteJDBCDAO implements ClienteDAO {

	public ClienteJDBCDAO() { }
	
	public void save(Cliente entity) {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String insert_sql = "insert into clientes (cpf, nome, fone, renda) values (?, ?, ?, ?)";
			String update_sql = "update clientes set cpf = ?, nome = ?, fone = ?, renda = ? where id = ?";
			PreparedStatement pst;
			if (entity.getId() == 0) {
				pst = con.prepareStatement(insert_sql);
			} else {
				pst = con.prepareStatement(update_sql);
				pst.setInt(5, entity.getId());
			}
			pst.setString(1, entity.getCpf());
			pst.setString(2, entity.getNome());
			pst.setString(3, entity.getFone());
			pst.setBigDecimal(4, new BigDecimal(entity.getRenda()));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
	}

	public void delete(int id) {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "delete from clientes where id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
	}

	public Cliente find(int id) {
		Connection con = null;
		Cliente cl = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select id, cpf, nome, fone, renda from clientes where id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cl = map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return cl;
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
		Connection con = null;
		List<Cliente> result = null;
		try {
			con = ConnectionFactory.getConnection();
			PreparedStatement pst;
			String sql = "select id, cpf, nome, fone, renda from clientes";
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			result = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente cl = map(rs);
				result.add(cl);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return result;
	}

	public Cliente findByCpf(String cpf) {
		Connection con = null;
		Cliente cl = null;
		try {
			con = ConnectionFactory.getConnection();
			PreparedStatement pst;
			String sql = "select id, cpf, nome, fone, renda from clientes where cpf = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cl = map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return cl;
	}

	public List<Cliente> findByNome(String str) {
		Connection con = null;
		List<Cliente> result = null;
		try {
			con = ConnectionFactory.getConnection();
			PreparedStatement pst;
			String sql = "select id, cpf, nome, fone, renda from clientes where upper(nome) like ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + str.toUpperCase() + "%");
			ResultSet rs = pst.executeQuery();
			result = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente cl = map(rs);
				result.add(cl);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return result;
	}
	
}
