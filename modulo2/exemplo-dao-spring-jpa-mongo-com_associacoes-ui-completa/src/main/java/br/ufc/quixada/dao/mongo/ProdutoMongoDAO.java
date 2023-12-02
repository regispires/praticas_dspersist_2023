package br.ufc.quixada.dao.mongo;

import br.ufc.quixada.dao.ProdutoDAO;
import br.ufc.quixada.entity.Produto;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoMongoDAO extends ProdutoDAO, MongoRepository<Produto, Integer> {

    public List<Produto> findByNomeContainingIgnoreCase(String str);

}
