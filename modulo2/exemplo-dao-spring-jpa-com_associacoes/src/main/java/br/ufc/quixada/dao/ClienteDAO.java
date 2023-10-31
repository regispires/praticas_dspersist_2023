package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

	// Os métodos findFirstByCpf, buscaPrimeiroPorCpf e buscaPorCpfViaConsultaNomeada fazem exatamente a mesma coisa
	public Cliente findFirstByCpf(String cpf);

	@Query("select c from Cliente c where c.cpf = :cpf")
	public Cliente buscaPrimeiroPorCpf(String cpf);

	// A consulta nomeada "clienteInciadoPorNome" está definida na classe Cliente
	@Query(name = "clientePorCpf")
	public Cliente buscaPorCpfViaConsultaNomeada(String cpf);


	// Os métodos findByNomeStartingWith e buscaPorNomeIniciadoPor fazem exatamente a mesma coisa
	public List<Cliente> findByNomeStartingWithIgnoreCase(String str);

	@Query("select c from Cliente c where c.nome ilike %:nome%")
	public List<Cliente> buscaPorNomeContendoString(String nome);

	@Query(value = "select count(*) from clientes c", nativeQuery=true)
	public int conta();

}
