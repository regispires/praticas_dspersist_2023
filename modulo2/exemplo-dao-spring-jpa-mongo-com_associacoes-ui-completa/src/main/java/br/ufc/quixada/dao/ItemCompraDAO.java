package br.ufc.quixada.dao;

import java.util.List;
import java.util.Optional;

import br.ufc.quixada.entity.ItemCompra;

public interface ItemCompraDAO {

    public List<ItemCompra> findByCompraId(String id);

    public void save(ItemCompra ic);

    public void deleteById(String id);

    public Optional<ItemCompra> findById(String id);

}
