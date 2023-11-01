package br.ufc.quixada.ui;

import br.ufc.quixada.dao.ClienteDAO;
import br.ufc.quixada.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan("br.ufc.quixada")
public class ListaClientes implements CommandLineRunner {
    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public void run(String... args) {
        //List<Cliente> clientes = clienteDAO.findAll();
        //List<Cliente> clientes = clienteDAO.buscaPorNomeContendoString("j");
        List<Cliente> clientes = clienteDAO.findByNomeStartingWithIgnoreCase("j");
        
        for (Cliente cli : clientes) {
            System.out.println(cli);
        }
        int contagem = clienteDAO.conta();
        System.out.println("Contagem: " + contagem);
        System.out.println("Fim");

    }
}
