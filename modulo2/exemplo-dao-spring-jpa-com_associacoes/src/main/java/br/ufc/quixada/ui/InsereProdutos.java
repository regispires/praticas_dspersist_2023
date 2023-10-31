package br.ufc.quixada.ui;

import br.ufc.quixada.dao.ProdutoDAO;
import br.ufc.quixada.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("br.ufc.quixada")
public class InsereProdutos implements CommandLineRunner {
    @Autowired
    private ProdutoDAO produtoDAO;

    public void run(String[] args) {
        Produto p = new Produto(null, "ovo", 1);
        produtoDAO.save(p);

        p = new Produto(null, "macarrão", 3);
        produtoDAO.save(p);

    }
}
