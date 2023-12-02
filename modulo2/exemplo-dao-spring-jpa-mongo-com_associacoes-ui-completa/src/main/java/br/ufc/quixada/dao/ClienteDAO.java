package br.ufc.quixada.dao;

import java.util.List;
import java.util.Optional;

import br.ufc.quixada.entity.Cliente;

public interface ClienteDAO {

	public Cliente findFirstByCpf(String cpf);

	public Cliente buscaPrimeiroPorCpf(String cpf);

	public List<Cliente> findByNomeStartingWithIgnoreCase(String str);

	public List<Cliente> buscaPorNomeContendoString(String nome);

	public int conta();

    public void save(Cliente cl);

    public void deleteById(String id);

    public Optional<Cliente> findById(String id);

    public List<Cliente> findAll();

}
