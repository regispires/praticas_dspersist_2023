package br.ufc.quixada;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufc.quixada.dao.ClienteDAO;
import br.ufc.quixada.dao.ClienteJDBCDAO;
import br.ufc.quixada.dao.ClienteJPADAO;
import br.ufc.quixada.dao.ClienteListDAO;
import br.ufc.quixada.dao.ClienteSpringJDBCDAO;
import br.ufc.quixada.entity.Cliente;

public class Principal {

	public static void main(String[] args) {
		ClienteDAO baseClientes = new ClienteJPADAO();
		StringBuilder menu = new StringBuilder("Escolha uma opção:\n")
			.append("1 - Inserir\n")
			.append("2 - Atualizar por CPF\n")
			.append("3 - Remover por CPF\n")
			.append("4 - Exibir por CPF\n")
			.append("5 - Exibir por id\n")
			.append("6 - Exibir todos\n")
			.append("7 - Exibir todos que contem determinado nome\n")
			.append("8 - Sair");
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
					obterCliente(cl);
					baseClientes.save(cl);
					break;
				case '3':     // Remover por CPF
					cpf = JOptionPane.showInputDialog("CPF");
					cl = baseClientes.findByCpf(cpf);
					if (cl != null) {
						baseClientes.delete(cl.getId());
						JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
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
			} catch (Exception e) {
				e.printStackTrace();
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
