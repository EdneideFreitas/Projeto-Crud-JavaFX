package br.com.projetoCrud.domain;

public class Pessoa {

	private int codigo;
	private String nome;
	private String sobreNome;
	private String email;

	public Pessoa(int codigo, String nome, String sobreNome, String email) {
		this.codigo = codigo;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.email = email;
	}

	public Pessoa() {

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
