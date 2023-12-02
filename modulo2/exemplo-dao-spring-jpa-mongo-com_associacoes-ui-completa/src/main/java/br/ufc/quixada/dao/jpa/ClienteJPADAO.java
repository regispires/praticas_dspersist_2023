package br.ufc.quixada.dao.jpa;

import java.util.List;

import br.ufc.quixada.dao.ClienteDAO;
import br.ufc.quixada.entity.Cliente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteJPADAO extends ClienteDAO, JpaRepository<Cliente, String> {

	// Os métodos findFirstByCpf, buscaPrimeiroPorCpf e buscaPorCpfViaConsultaNomeada fazem exatamente a mesma coisa
	public Cliente findFirstByCpf(String cpf);

	//@Query("select c from Cliente c where c.cpf = :cpf")
	@Query(name = "clientePorCpf")
	public Cliente buscaPrimeiroPorCpf(String cpf);

	// Os métodos findByNomeStartingWith e buscaPorNomeIniciadoPor fazem exatamente a mesma coisa
	public List<Cliente> findByNomeStartingWithIgnoreCase(String str);

	@Query("select c from Cliente c where c.nome ilike %:nome%")
	public List<Cliente> buscaPorNomeContendoString(String nome);

	@Query(value = "select count(*) from clientes c", nativeQuery=true)
	public int conta();

}
