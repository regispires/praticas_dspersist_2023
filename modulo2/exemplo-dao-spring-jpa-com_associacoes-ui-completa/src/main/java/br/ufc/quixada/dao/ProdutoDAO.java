package br.ufc.quixada.dao;

import br.ufc.quixada.entity.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Integer> {

    public List<Produto> findByNomeContainingIgnoreCase(String str);

}
