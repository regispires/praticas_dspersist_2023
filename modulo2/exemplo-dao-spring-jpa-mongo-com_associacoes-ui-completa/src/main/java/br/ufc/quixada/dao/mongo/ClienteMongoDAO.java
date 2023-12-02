package br.ufc.quixada.dao.mongo;

import java.util.List;

import br.ufc.quixada.dao.ClienteDAO;
import br.ufc.quixada.entity.Cliente;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteMongoDAO extends ClienteDAO, MongoRepository<Cliente, String> {

	// Os métodos findFirstByCpf, buscaPrimeiroPorCpf e buscaPorCpfViaConsultaNomeada fazem exatamente a mesma coisa
	public Cliente findFirstByCpf(String cpf);

	//@Query("select c from Cliente c where c.cpf = :cpf")
	@Query(value = "{cpf: :#{#cpf}}")
	public Cliente buscaPrimeiroPorCpf(String cpf);

	// Os métodos findByNomeStartingWith e buscaPorNomeIniciadoPor fazem exatamente a mesma coisa
	public List<Cliente> findByNomeStartingWithIgnoreCase(String str);

	//@Query("select c from Cliente c where c.nome ilike %:nome%")
	@Query(value = "{nome: {$regex: :#{#nome}, $options: 'i'} }")
	public List<Cliente> buscaPorNomeContendoString(String nome);

	@Query(value = "{}", count = true)
	public int conta();

}
