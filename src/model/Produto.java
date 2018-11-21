package model;

import java.util.Date;
import controller.SisComException;
public class Produto implements Comparable<Produto> {
int codigo;
String nome;
double precoUnitario;
int estoque;
int estoqueMinimo;
Date dataCad;
public Produto(int codigo, String nome, double precoUnitario, int estoque, int estoqueMinimo, Date dataCad) {

	this.codigo = codigo;
	this.nome = nome;
	this.precoUnitario = precoUnitario;
	this.estoque = estoque;
	this.estoqueMinimo = estoqueMinimo;
	this.dataCad = dataCad;
}
void diminuirEstoque(int quant)throws SisComException {
	if(estoque<quant) {
		throw new SisComException(this.nome,estoque,"Estoque Insuficiente. ");
	}else
		this.estoque++;
}
void AumentarEstoque(int quant) {
	this.estoque++;
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
public double getPrecoUnitario() {
	return precoUnitario;
}
public void setPrecoUnitario(double precoUnitario) {
	this.precoUnitario = precoUnitario;
}
public int getEstoque() {
	return estoque;
}
public void setEstoque(int estoque) {
	this.estoque = estoque;
}
public int getEstoqueMinimo() {
	return estoqueMinimo;
}
public void setEstoqueMinimo(int estoqueMinimo) {
	this.estoqueMinimo = estoqueMinimo;
}
public Date getDataCad() {
	return dataCad;
}
public void setDataCad(Date dataCad) {
	this.dataCad = dataCad;
}
@Override
public String toString() {
	return "Produto [codigo=" + codigo + ", nome=" + nome + ", precoUnitario=" + precoUnitario + ", estoque=" + estoque
			+ ", estoqueMinimo=" + estoqueMinimo + ", dataCad=" + dataCad + "]";
}
@Override
public int compareTo(Produto p) {
int r=0;
	if(this.nome.length()<p.nome.length()) {
	r=1;
}else if(this.nome.length()>p.nome.length()) {
	r=-1;
}
	return r;
}
}
