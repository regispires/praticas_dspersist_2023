package br.ufc.quixada.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "itens_compras")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ItemCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@NonNull private Compra compra;

	@ManyToOne
	@NonNull private Produto produto;

	private float quantidade;

	private float valorUnitario;

	public float getValorTotal() {
		return this.quantidade * this.valorUnitario;
	}

	@Override
	public String toString() {
		return "ItemCompra [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", valorUnitario="
				+ valorUnitario + ", valorTotal=" + getValorTotal() +"]";
	}


	
}
