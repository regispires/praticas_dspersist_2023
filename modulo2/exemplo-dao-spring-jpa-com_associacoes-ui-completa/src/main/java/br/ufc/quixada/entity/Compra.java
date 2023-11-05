package br.ufc.quixada.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "compras")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Compra {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Cliente cliente;

	@NonNull private LocalDateTime dataHora;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCompra> itens;

	public float getValorTotal() {
		float total = 0;
		for (ItemCompra item: this.itens) {
			total += item.getValorTotal();
		}
		return total;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", cliente=" + cliente + ", dataHora=" + dataHora.format(formatter) + "]";
	}

}
