package br.ufc.quixada.entity;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compras")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Cliente cliente;

	@NonNull private LocalDateTime dataHora;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	private List<ItemCompra> itens;

	public float getValorTotal() {
		float total = 0;
		for (ItemCompra item: this.itens) {
			total += item.getValorTotal();
		}
		return total;
	}

}
