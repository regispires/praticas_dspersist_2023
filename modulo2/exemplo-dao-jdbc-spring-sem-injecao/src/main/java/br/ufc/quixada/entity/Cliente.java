package br.ufc.quixada.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente {
	private Integer id;
	private String cpf;
	private String nome;
	private String fone;
	private double renda;	
}
