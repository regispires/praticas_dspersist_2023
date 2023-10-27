package br.ufc.quixada.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.entity.Cliente;

public class ClienteListDAO implements ClienteDAO {

	private List<Cliente> clientes;
	
	private static int idProximo = 1;
	
	
	public ClienteListDAO() {
		this.clientes = new ArrayList<Cliente>();
	}
	
	public void save(Cliente entity) {
		// Inserir um cliente se o id do objeto for 0.
		if (entity.getId() == 0) {
			entity.setId(idProximo++);
			clientes.add(entity);
		// Alterar um cliente se o id não for 0.
		} else {
			int posicaoNaLista = findIndex(entity.getId());
			clientes.set(posicaoNaLista, entity);
		}
	}

	public void delete(int id) {
		clientes.remove(find(id));
	}

	public Cliente find(int id) {
		for (Cliente cl : this.clientes) {
			if (cl.getId() == id) {
				return cl;
			}
		}
		return null;
	}

	private int findIndex(int id) {
		for (int i=0; i < clientes.size(); i++) {
			if (clientes.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public List<Cliente> find() {
		return this.clientes;
	}

	public Cliente findByCpf(String cpf) {
		for (Cliente cl : this.clientes) {
			if (cl.getCpf().equals(cpf)) {
				return cl;
			}
		}
		return null;
	}

	public List<Cliente> findByNome(String str) {
		List<Cliente> resultado = new ArrayList<Cliente>();
		for (Cliente cl : this.clientes) {
			if (cl.getNome().toUpperCase().contains(str.toUpperCase())) {
				resultado.add(cl);
			}
		}
		return resultado;
	}
}
