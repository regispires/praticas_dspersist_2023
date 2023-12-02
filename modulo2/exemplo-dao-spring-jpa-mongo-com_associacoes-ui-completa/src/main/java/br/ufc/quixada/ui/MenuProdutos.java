package br.ufc.quixada.ui;

import br.ufc.quixada.dao.ProdutoDAO;
import br.ufc.quixada.entity.Produto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class MenuProdutos {
	@Autowired
	private ProdutoDAO baseProdutos;

	public void obterProduto(Produto prod) {
		String nome = JOptionPane.showInputDialog("Nome", prod.getNome());
		float valorAtual = Float.parseFloat(JOptionPane.showInputDialog("Valor Atual", prod.getValorAtual()));
		prod.setNome(nome);
		prod.setValorAtual(valorAtual);
	}

	public void listaProdutos(List<Produto> produtos) {
		StringBuilder listagem = new StringBuilder();
		for(Produto prod : produtos) {
			listagem.append(prod).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum produto encontrado" : listagem);
	}

	public void listaProduto(Produto prod) {
		JOptionPane.showMessageDialog(null, prod == null ? "Nenhum produto encontrado" : prod);
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Produtos\n")
			.append("1 - Inserir\n")
			.append("2 - Atualizar por id\n")
			.append("3 - Remover por id\n")
			.append("4 - Exibir por id\n")
			.append("5 - Exibir todos\n")
			.append("6 - Exibir todos que contém determinado nome\n")
			.append("7 - Menu anterior");
		char opcao = '0';
		do {
			try {
				Produto prod;
				String id;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
					case '1':     // Inserir
						prod = new Produto();
						obterProduto(prod);
						baseProdutos.save(prod);
						break;
					case '2':     // Atualizar por id
						id = JOptionPane.showInputDialog("Digite o id do produto a ser alterado");
						prod = baseProdutos.findById(id).orElse(null);
						if (prod != null) {
							obterProduto(prod);
							baseProdutos.save(prod);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado produto com o id " + id);
						}
						break;
					case '3':     // Remover por id
						id = JOptionPane.showInputDialog("Digite o id do produto a ser removido");
						prod = baseProdutos.findById(id).orElse(null);
						if (prod != null) {
							baseProdutos.deleteById(prod.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado produto com o id " + id);
						}
						break;
					case '4':     // Exibir por id
						id = JOptionPane.showInputDialog("Digite o id do produto a ser exibido");
						prod = baseProdutos.findById(id).orElse(null);
						if (prod != null) {
							listaProduto(prod);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado produto com o id " + id);
						}
						break;
					case '5':     // Exibir todos
						listaProdutos(baseProdutos.findAll());
						break;
					case '6':     // Exibir todos que contem determinado nome
						String nome = JOptionPane.showInputDialog("Nome");
						listaProdutos(baseProdutos.findByNomeContainingIgnoreCase(nome));
						break;
					case '7':     // Sair
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
