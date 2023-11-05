package br.ufc.quixada.ui;

import br.ufc.quixada.dao.ClienteDAO;
import br.ufc.quixada.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("br.ufc.quixada")
public class InsereClientes implements CommandLineRunner {
    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public void run(String... args) {
        Cliente cliente = new Cliente(null, "1111", "João", "1111", 1111.11, null);
        clienteDAO.save(cliente);

        cliente = new Cliente(null, "2222", "Maria", "2222", 2222.22, null);
        clienteDAO.save(cliente);

        cliente = new Cliente(null, "3333", "José", "3333", 3333.33, null);
        clienteDAO.save(cliente);
    }
}
