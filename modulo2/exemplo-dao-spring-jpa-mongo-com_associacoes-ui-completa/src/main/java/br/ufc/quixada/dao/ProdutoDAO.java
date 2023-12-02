package br.ufc.quixada.dao;

import br.ufc.quixada.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoDAO {

    public List<Produto> findByNomeContainingIgnoreCase(String str);

    public List<Produto> findAll();

    public Optional<Produto> findById(String id);

    public void save(Produto prod);

    public void deleteById(String id);

}
