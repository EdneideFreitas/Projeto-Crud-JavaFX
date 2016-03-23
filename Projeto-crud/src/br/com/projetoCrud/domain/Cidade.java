package br.com.projetoCrud.domain;

public class Cidade {

	private Long codigo;
	private String uf;
	
	public Cidade(Long codigo, String uf) {
		super();
		this.codigo = codigo;
		this.uf = uf;
	}
	
	public Cidade() {
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	
	@Override
	public String toString() {
		
		return "UF: " + uf;
	}
	
}
