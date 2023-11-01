package br.ufc.quixada.dao;

import br.ufc.quixada.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDAO extends JpaRepository<Compra, Integer> {

}
