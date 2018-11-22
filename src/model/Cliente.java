package model;

import java.util.Date;

public class Cliente extends Pessoa {
	String cpf;
	double limiteCredito;

	Cliente(int codigo, String nome, String email, Date dataCad, String cpf, double limiteCredito) {
		super(codigo, nome, email, dataCad);
		this.cpf = cpf;
		this.limiteCredito = limiteCredito;
	}

	@Override
	public String toString() {
		return super.toString() + " Cliente [cpf=" + cpf + ", limiteCredito=" + limiteCredito + "]";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

}
