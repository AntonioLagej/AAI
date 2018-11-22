package model;

import java.util.Date;

public abstract class Pessoa implements Comparable<Pessoa> {
	private int codigo;
	private String nome;
	private String email;
	private Date dataCad;

	Pessoa(int codigo, String nome, String email, Date dataCad) {
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.dataCad = dataCad;
	}

	@Override
	public int compareTo(Pessoa p) {

		if (p.getNome().length() > this.getNome().length()) {
			return -1;
		} else if (p.getNome().length() < this.getNome().length()) {
			return 1;
		} else
			return 0;

	}

	@Override
	public String toString() {
		return "Pessoa [codigo=" + codigo + ", nome=" + nome + ", email=" + email + ", dataCad=" + dataCad + "]";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}
}
