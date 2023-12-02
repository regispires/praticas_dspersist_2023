package br.ufc.quixada.ui;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.quixada.dao.ClienteDAO;
import br.ufc.quixada.dao.CompraDAO;
import br.ufc.quixada.entity.Cliente;
import br.ufc.quixada.entity.Compra;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MenuCompras {
	@Autowired
	private CompraDAO baseCompras;

	@Autowired
	private ClienteDAO baseClientes;

	@Autowired
	private MenuItensCompras menuItensCompras;


	public void obterESalvarCompra(Compra compr) {
		List<Cliente> clientes = baseClientes.findAll();
		Cliente cl = (Cliente)JOptionPane.showInputDialog(null, "Selecione um cliente", 
			"Clientes", JOptionPane.PLAIN_MESSAGE, null, clientes.toArray(), compr.getCliente());
		compr.setCliente(cl);
		if (compr.getDataHora() == null)
			compr.setDataHora(LocalDateTime.now());
		baseCompras.save(compr);
		menuItensCompras.menu(compr);
	}

	public void listaCompras(List<Compra> compras) {
		StringBuilder listagem = new StringBuilder();
		for(Compra compr : compras) {
			listagem.append(compr);
			try {
				float valorTotal = compr.getValorTotal();
				listagem.append(" Valor total: ");
				listagem.append(valorTotal);
			} catch (LazyInitializationException e) {
				log.debug(e.getMessage());
			}
			listagem.append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma compra encontrada" : listagem);
	}

	public static void listaCompra(Compra compr) {
		JOptionPane.showMessageDialog(null, compr == null ? "Nenhuma compra encontrada" : compr);
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Compras\n")
			.append("1 - Inserir\n")
			.append("2 - Atualizar por id\n")
			.append("3 - Remover por id\n")
			.append("4 - Exibir por id\n")
			.append("5 - Exibir todos\n")
			.append("6 - Exibir compras com valor total maior ou igual a um determinado valor\n")
			.append("7 - Menu anterior");
		char opcao = '0';
		do {
			try {
				Compra compr;
				String id;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
					case '1':     // Inserir
						compr = new Compra();
						obterESalvarCompra(compr);
						break;
					case '2':     // Atualizar por id
						id = JOptionPane.showInputDialog("Digite o id da compra a ser alterada");
						compr = baseCompras.findById(id).orElse(null);
						if (compr != null) {
							obterESalvarCompra(compr);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrada compra com o id " + id);
						}
						break;
					case '3':     // Remover por id
						id = JOptionPane.showInputDialog("Digite o id da compra a ser removida");
						compr = baseCompras.findById(id).orElse(null);
						if (compr != null) {
							baseCompras.deleteById(compr.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrada compra com o id " + id);
						}
						break;
					case '4':     // Exibir por id
						id = JOptionPane.showInputDialog("Digite o id da compra a ser exibida");
						compr = baseCompras.findById(id).orElse(null);
						if (compr != null) {
							listaCompra(compr);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrada compra com o id " + id);
						}
						break;
					case '5':     // Exibir todos
						listaCompras(baseCompras.findAll());
						break;
					case '6':     // Exibir todos
						float valor = Float.valueOf(JOptionPane.showInputDialog("Digite o valor"));
						listaCompras(baseCompras.findComprasComValorTotalMaiorOuIgualA(valor));
						break;
						case '7':     // Menu anterior
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while(opcao != '7');
	}
}
