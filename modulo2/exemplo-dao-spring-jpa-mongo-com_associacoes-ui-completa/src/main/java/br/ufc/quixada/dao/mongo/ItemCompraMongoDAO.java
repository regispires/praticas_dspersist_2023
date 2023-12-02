package br.ufc.quixada.dao.mongo;

import br.ufc.quixada.dao.ItemCompraDAO;
import br.ufc.quixada.entity.ItemCompra;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraMongoDAO extends ItemCompraDAO, MongoRepository<ItemCompra, Integer> {

    //@Query("SELECT ic FROM ItemCompra ic WHERE ic.compra.id = :id")
    @Query(value = "{compra.id: :id}")
    public List<ItemCompra> findByCompraId(String id);

}
