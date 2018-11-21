package model;

import java.util.Date;

public class Fornecedor extends Pessoa {
	String cnpj;
	String nomeContato;
		public Fornecedor(int codigo, String nome, String email, Date dataCad,String cnpj,String nomeContato) {
			super(codigo, nome, email, dataCad);
			this.cnpj = cnpj;
			this.nomeContato = nomeContato;
		}
		@Override
		public String toString() {
			return super.toString()+" Fornecedor [cnpj=" + cnpj + ", nomeContato=" + nomeContato + "]";
		}
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public String getNomeContato() {
			return nomeContato;
		}
		public void setNomeContato(String nomeContato) {
			this.nomeContato = nomeContato;
		}
	
		
}
