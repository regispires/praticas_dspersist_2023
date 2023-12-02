package br.ufc.quixada.dao.mongo;

import br.ufc.quixada.dao.CompraDAO;
import br.ufc.quixada.entity.Compra;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraMongoDAO extends CompraDAO, MongoRepository<Compra, Integer> {

    // Consulta JQPL que traz o objeto compra com suas associações cliente, itens e produto em uma única consulta SQL ao banco
    // @Query("SELECT c FROM Compra c " +
    //         "left join fetch c.cliente " + 
    //         "left join fetch c.itens i " +
    //         "left join fetch i.produto " +
    //         "where c.id in " +
    //         "(SELECT c.id FROM Compra c join c.itens i group by c having sum(i.quantidade * i.valorUnitario) >= :valor)")
    @Query(value = "{}")
    public List<Compra> findComprasComValorTotalMaiorOuIgualA(float valor);

}
