package model;

import java.util.Date;

public class Vendedor extends Pessoa {
	String cpf;
	double metaMensal;
		Vendedor(int codigo, String nome, String email, Date dataCad,String cpf,double limiteCredito) {
			super(codigo, nome, email, dataCad);
			this.cpf = cpf;
			this.metaMensal = limiteCredito;
		}
		@Override
		public String toString() {
			return super.toString()+" Vendedor [cpf=" + cpf + ", metaMensal=" + metaMensal + "]";
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public double getMetaMensal() {
			return metaMensal;
		}
		public void setMetaMensal(double metaMensal) {
			this.metaMensal = metaMensal;
		}
}
