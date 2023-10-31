package br.ufc.quixada.ui;

import br.ufc.quixada.dao.ClienteDAO;
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
public class InsereClientes implements CommandLineRunner {
    @Autowired
    private ClienteDAO clienteDAO;

    public void run(String[] args) {
        Cliente cliente = new Cliente(null, "João", "1111", "1111", 1111.11, null);
        clienteDAO.save(cliente);

        cliente = new Cliente(null, "Maria", "2222", "2222", 2222.22, null);
        clienteDAO.save(cliente);

        cliente = new Cliente(null, "José", "3333", "3333", 3333.33, null);
        clienteDAO.save(cliente);
        
    }
}
