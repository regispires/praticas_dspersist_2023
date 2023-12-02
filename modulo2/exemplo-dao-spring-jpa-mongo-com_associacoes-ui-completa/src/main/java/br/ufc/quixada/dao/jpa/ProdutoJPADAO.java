package br.ufc.quixada.dao.jpa;

import br.ufc.quixada.dao.ProdutoDAO;
import br.ufc.quixada.entity.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoJPADAO extends ProdutoDAO, JpaRepository<Produto, Integer> {

    public List<Produto> findByNomeContainingIgnoreCase(String str);

}
