package br.ufc.quixada.dao;

import br.ufc.quixada.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraDAO {

    public List<Compra> findComprasComValorTotalMaiorOuIgualA(float valor);

    public Optional<Compra> findById(String id);

    public void deleteById(String id);

    public List<Compra> findAll();

    public void save(Compra compr);

}
