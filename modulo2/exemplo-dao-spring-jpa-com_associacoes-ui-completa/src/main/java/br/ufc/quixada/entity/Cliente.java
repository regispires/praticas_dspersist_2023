package br.ufc.quixada.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@NamedQueries({
		@NamedQuery(name = "clientePorCpf", query = "select c from Cliente c where c.cpf = :cpf")
})

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String cpf;
	
	private String nome;
	private String fone;
	private float renda;

	@OneToMany(mappedBy = "cliente")
	private List<Compra> compras;

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + "]";
	}

	public String toStringCompleto() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", fone=" + fone + ", renda=" + renda + "]";
	}

	

}
