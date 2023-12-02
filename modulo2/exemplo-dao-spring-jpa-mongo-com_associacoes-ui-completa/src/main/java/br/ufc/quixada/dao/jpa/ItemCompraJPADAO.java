package br.ufc.quixada.dao.jpa;

import br.ufc.quixada.dao.ItemCompraDAO;
import br.ufc.quixada.entity.ItemCompra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraJPADAO extends ItemCompraDAO, JpaRepository<ItemCompra, Integer> {

    @Query("SELECT ic FROM ItemCompra ic WHERE ic.compra.id = :id")
    public List<ItemCompra> findByCompraId(String id);

}
