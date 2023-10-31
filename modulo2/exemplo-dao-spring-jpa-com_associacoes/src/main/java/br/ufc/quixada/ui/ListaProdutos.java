package br.ufc.quixada.ui;

import br.ufc.quixada.dao.ProdutoDAO;
import br.ufc.quixada.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan("br.ufc.quixada")
public class ListaProdutos implements CommandLineRunner {
    @Autowired
    private ProdutoDAO produtoDAO;

    public void run(String[] args) {
        List<Produto> produtos = produtoDAO.findAll();
        for (Produto prod : produtos) {
            System.out.println(prod);
        }
    }
}
