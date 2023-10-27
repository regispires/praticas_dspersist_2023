package br.ufc.quixada.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

	public Cliente(int id) {
		this.id = id;
	}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(unique=true)
	private String cpf;

	private String nome;
	private String fone;
	private double renda;	
}
