package br.ufc.quixada;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.ufc.quixada.dao.ClienteDAO;
import br.ufc.quixada.entity.Cliente;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Principal implements CommandLineRunner {

	@Autowired
	private ClienteDAO baseClientes;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Principal.class);
        builder.headless(false).run(args);
	}

	@Override
	public void run(String... args) {
		String menu = "Escolha uma opção:\n1 - Inserir\n2 - Atualizar por CPF\n3 - Remover por CPF\n4 - Exibir por CPF\n5 - Exibir por id\n6 - Exibir todos\n7 - Exibir todos que contem determinado nome\n8 - Sair";
		char opcao = '0';
		do {
			try {
				Cliente cl;
				String cpf;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
				case '1':     // Inserir
					cl = new Cliente();
					obterCliente(cl);
					baseClientes.save(cl);
					break;
				case '2':     // Atualizar por CPF
					cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a ser alterado");
					cl = baseClientes.findByCpf(cpf);
					if (cl != null) {
						obterCliente(cl);
						baseClientes.save(cl);
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível atualizar, pois o cliente não foi encontrado.");
					}
					break;
				case '3':     // Remover por CPF
					cpf = JOptionPane.showInputDialog("CPF");
					cl = baseClientes.findByCpf(cpf);
					if (cl != null) {
						baseClientes.delete(cl.getId());
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o cliente não foi encontrado.");
					}
					break;
				case '4':     // Exibir por CPF
					cpf = JOptionPane.showInputDialog("CPF");
					cl = baseClientes.findByCpf(cpf);
					listaCliente(cl);
					break;
				case '5':     // Exibir por id
					int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
					cl = baseClientes.find(id);
					listaCliente(cl);
					break;
				case '6':     // Exibir todos
					listaClientes(baseClientes.find());
					break;
				case '7':     // Exibir todos que contem determinado nome
					String nome = JOptionPane.showInputDialog("Nome");
					listaClientes(baseClientes.findByNome(nome));
					break;
				case '8':     // Sair
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção Inválida");
					break;
				}
			} catch (NumberFormatException e) {
				log.error("Erro: {}", e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Entrada inválida: " + e.getMessage());

			} catch (Exception e) {
				log.error("Erro: {}", e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}
		} while(opcao != '8');	
	}
	
	public static void obterCliente(Cliente cl) {
		String nome = JOptionPane.showInputDialog("Nome", cl.getNome());
		String cpf = JOptionPane.showInputDialog("CPF", cl.getCpf());
		String fone = JOptionPane.showInputDialog("Fone", cl.getFone());
		double renda = Double.parseDouble(JOptionPane.showInputDialog("Renda", cl.getRenda()));
		cl.setNome(nome);
		cl.setCpf(cpf);
		cl.setFone(fone);
		cl.setRenda(renda);
	}

	public static void listaClientes(List<Cliente> clientes) {
		StringBuilder listagem = new StringBuilder();
		for(Cliente cl : clientes) {
			listagem.append(cl).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum cliente encontrado" : listagem);
	}

	public static void listaCliente(Cliente cl) {
		JOptionPane.showMessageDialog(null, cl == null ? "Nenhum cliente encontrado" : cl);
	}
	
}
