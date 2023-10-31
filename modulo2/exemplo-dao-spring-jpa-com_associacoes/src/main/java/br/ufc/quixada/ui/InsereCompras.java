package br.ufc.quixada.ui;

import br.ufc.quixada.dao.CompraDAO;
import br.ufc.quixada.dao.ProdutoDAO;
import br.ufc.quixada.entity.Cliente;
import br.ufc.quixada.entity.Compra;
import br.ufc.quixada.entity.ItemCompra;
import br.ufc.quixada.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("br.ufc.quixada")
public class InsereCompras implements CommandLineRunner {
    @Autowired
    private CompraDAO compraDAO;

    public void run(String[] args) {
        Compra compra = new Compra();
        compra.setDataHora(LocalDateTime.now());
        Cliente cliente = new Cliente();
        cliente.setId(1);
        compra.setCliente(cliente);

        List<ItemCompra> itens = new ArrayList<ItemCompra>();

        ItemCompra ic = new ItemCompra();
        ic.setCompra(compra);
        Produto p1 = new Produto();
        p1.setId(1);
        ic.setProduto(p1);
        ic.setValorUnitario(1);
        ic.setQuantidade(1);
        itens.add(ic);

        ItemCompra ic2 = new ItemCompra();
        ic2.setCompra(compra);
        Produto p2 = new Produto();
        p2.setId(2);
        ic2.setProduto(p2);
        ic2.setValorUnitario(2);
        ic2.setQuantidade(2);
        itens.add(ic2);

        compra.setItens(itens);
        compraDAO.save(compra);
    }
}
