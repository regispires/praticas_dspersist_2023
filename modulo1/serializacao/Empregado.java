package serializacao;

import java.io.Serializable;

public class Empregado implements Serializable {
	private String nome;
	private String endereco;
	//private transient String cpf;
	private String cpf;
	private int numero;

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Empregado [nome=" + nome + ", endereco=" + endereco + ", cpf=" + cpf + ", numero=" + numero + "]";
	}	
	
}
