package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.entity.Cliente;

public interface ClienteDAO {
	
	public void save(Cliente entity);
	
	public void delete(int id);
	
	public Cliente find(int id);
	
	public List<Cliente> find();
	
	public Cliente findByCpf(String cpf);
	
	public List<Cliente> findByNome(String str);
}
