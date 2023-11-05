package br.ufc.quixada.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull private String nome;
	private float valorAtual;
}
