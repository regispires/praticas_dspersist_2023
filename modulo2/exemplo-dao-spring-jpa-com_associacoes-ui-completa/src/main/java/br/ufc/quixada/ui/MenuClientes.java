package br.ufc.quixada.ui;

import br.ufc.quixada.dao.ClienteDAO;
import br.ufc.quixada.entity.Cliente;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class MenuClientes {
	@Autowired
	private ClienteDAO baseClientes;

	public void obterCliente(Cliente cl) {
		String nome = JOptionPane.showInputDialog("Nome", cl.getNome());
		String cpf = JOptionPane.showInputDialog("CPF", cl.getCpf());
		String fone = JOptionPane.showInputDialog("Fone", cl.getFone());
		float renda = Float.valueOf(JOptionPane.showInputDialog("Renda", cl.getRenda()));
		cl.setNome(nome);
		cl.setCpf(cpf);
		cl.setFone(fone);
		cl.setRenda(renda);
	}

	public void listaClientes(List<Cliente> clientes) {
		StringBuilder listagem = new StringBuilder();
		for(Cliente cl : clientes) {
			listagem.append(cl.toStringCompleto()).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum cliente encontrado" : listagem);
	}

	public void listaCliente(Cliente cl) {
		JOptionPane.showMessageDialog(null, cl == null ? "Nenhum cliente encontrado" : cl.toStringCompleto());
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Clientes\n")
			.append("1 - Inserir\n")
			.append("2 - Atualizar por CPF\n")
			.append("3 - Remover por CPF\n")
			.append("4 - Exibir por CPF\n")
			.append("5 - Exibir por id\n")
			.append("6 - Exibir todos\n")
			.append("7 - Exibir todos que contém determinado nome\n")
			.append("8 - Menu anterior");
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
						cl = baseClientes.findFirstByCpf(cpf);
						if (cl != null) {
							obterCliente(cl);
							baseClientes.save(cl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi possível atualizar, pois o cliente não foi encontrado.");
						}
						break;
					case '3':     // Remover por CPF
						cpf = JOptionPane.showInputDialog("CPF");
						cl = baseClientes.findFirstByCpf(cpf);
						if (cl != null) {
							baseClientes.deleteById(cl.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o cliente não foi encontrado.");
						}
						break;
					case '4':     // Exibir por CPF
						cpf = JOptionPane.showInputDialog("CPF");
						// Temos 3 opções de implementação que fazem o mesmo:
						//cl = baseClientes.findFirstByCpf(cpf);
						//cl = baseClientes.buscaPrimeiroPorCpf(cpf);
						cl = baseClientes.buscaPorCpfViaConsultaNomeada(cpf);
						listaCliente(cl);
						break;
					case '5':     // Exibir por id
						int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
						cl = baseClientes.findById(id).orElse(null);
						listaCliente(cl);
						break;
					case '6':     // Exibir todos
						listaClientes(baseClientes.findAll());
						break;
					case '7':     // Exibir todos que contem determinado nome
						String nome = JOptionPane.showInputDialog("Nome");
						// Temos 2 opções de implementação que fazem o mesmo:
						// listaClientes(baseClientes.findByNomeStartingWith(nome));
						listaClientes(baseClientes.buscaPorNomeContendoString(nome));
						break;
					case '8':     // Sair
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while(opcao != '8');
	}
}
