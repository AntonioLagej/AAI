package controller;

public class SisComException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4065884825830827493L;
	private String nomeProduto;
	private int estoque;
	private String mensagemErro;
	public SisComException(String nomeProduto, int estoque, String mensagemErro) {
		super(mensagemErro);
		this.nomeProduto = nomeProduto;
		this.estoque = estoque;
		this.mensagemErro = mensagemErro;
	}
	public SisComException(String mensagemErro) {
		super(mensagemErro);
		this.mensagemErro = mensagemErro;
		
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public int getEstoque() {
		return estoque;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	
}
