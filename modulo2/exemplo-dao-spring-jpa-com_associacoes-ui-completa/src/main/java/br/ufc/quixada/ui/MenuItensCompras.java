package br.ufc.quixada.ui;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.quixada.dao.ItemCompraDAO;
import br.ufc.quixada.dao.ProdutoDAO;
import br.ufc.quixada.entity.Compra;
import br.ufc.quixada.entity.ItemCompra;
import br.ufc.quixada.entity.Produto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MenuItensCompras {
	@Autowired
	private ItemCompraDAO baseItensCompras;

	@Autowired
	private ProdutoDAO baseProdutos;

	private Compra compra;

	public void obterItemCompra(ItemCompra item) {
		List<Produto> produtos = baseProdutos.findAll();
		Produto prod = (Produto)JOptionPane.showInputDialog(null, "Selecione um produto", 
			"Produtos", JOptionPane.PLAIN_MESSAGE, null, produtos.toArray(), item.getProduto());
		//String nome = JOptionPane.showInputDialog("Nome", compr.getNome());
		item.setProduto(prod);
		
		float qtd = Float.valueOf(JOptionPane.showInputDialog("Quantidade", item.getQuantidade()));
		item.setQuantidade(qtd);
		
		float valorUnitario = item.getValorUnitario();
		if (valorUnitario == 0) // se o item não tem valor unitário, usar o valor atual do produto
			valorUnitario = prod.getValorAtual();
		valorUnitario = Float.valueOf(JOptionPane.showInputDialog("Valor unitário", valorUnitario));
		item.setValorUnitario(valorUnitario);
	}


	public static void listaItemCompra(ItemCompra ic) {
		JOptionPane.showMessageDialog(null, ic == null ? "Nenhuma compra encontrada" : ic);
	}

	public StringBuilder getStringItensCompra() {
		List<ItemCompra> itens = baseItensCompras.findByCompraId(this.compra.getId());
		StringBuilder str = new StringBuilder();
		for(ItemCompra item : itens) {
			str.append(item).append("\n");
		}
		return str.length() == 0 ? new StringBuilder("Nenhum item de compra na compra (id=").append(compra.getId()).append(")\n") : str;
	}

	public void menu(Compra compra) {
		this.compra = compra;
		char opcao = '0';
		do {
			try {
				StringBuilder menu = new StringBuilder("Menu itens de compra (id=").append(compra.getId()).append(")\n")
					.append(getStringItensCompra())
					.append("1 - Inserir\n")
					.append("2 - Atualizar por id\n")
					.append("3 - Remover por id\n")
					.append("4 - Exibir por id\n")
					.append("5 - Exibir todos\n")
					.append("6 - Menu anterior");
				ItemCompra ic;
				Integer id;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
					case '1':     // Inserir
						ic = new ItemCompra();
						obterItemCompra(ic);
						ic.setCompra(compra);
						baseItensCompras.save(ic);
						break;
					case '2':     // Atualizar por id
						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do item de compra a ser alterado"));
						ic = baseItensCompras.findById(id).orElse(null);
						if (ic != null) {
							if (ic.getCompra().getId() != compra.getId()) {
								JOptionPane.showMessageDialog(null, "O item de compra " + ic.getId() + " não pertence à compra " + compra.getId());
							} else {
								obterItemCompra(ic);
								baseItensCompras.save(ic);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado item de compra com o id " + id);
						}
						break;
					case '3':     // Remover por id
						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id da compra a ser removida"));
						ic = baseItensCompras.findById(id).orElse(null);
						if (ic != null) {
							baseItensCompras.deleteById(ic.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado item de compra com o id " + id);
						}
						break;
					case '4':     // Exibir por id
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da compra a ser exibida"));
						ic = baseItensCompras.findById(id).orElse(null);
						if (ic != null) {
							listaItemCompra(ic);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado item de compra com o id " + id);
						}
						break;
					case '5':     // Exibir todos
							JOptionPane.showMessageDialog(null, getStringItensCompra());
						break;
					case '6':     // Sair
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while(opcao != '6');
	}
}
