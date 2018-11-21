package controller;

import java.util.ArrayList;

import model.Cliente;
import model.Fornecedor;
import model.ItemCompra;
import model.Pessoa;
import model.Produto;
import model.Vendedor;

public class Comercial {

	ArrayList<Pessoa> pessoas;
	ArrayList<Produto> produtos;
	ArrayList<ItemCompra> itensCompra;

	void InserirPessoa(Pessoa p) throws Exception {

		for (Pessoa pessoa : pessoas) {
			if (p instanceof Cliente) {
				if (((Cliente) (pessoa)).getCpf().equals(((Cliente) p).getCpf())) {
					throw new Exception("Erro, cliente ja cadastrado");
				}
			} else if (p instanceof Fornecedor) {
				if (((Fornecedor) pessoa).getCnpj().equals(((Fornecedor) p).getCnpj())) {
					throw new Exception("Erro, Fornecedor ja cadastrado");
				}
			} else if (p instanceof Vendedor) {
				if (((Vendedor) (pessoa)).getCpf().equals(((Vendedor) p).getCpf())) {
					throw new Exception("Erro, Vendedor ja cadastrado");
				} else if (((Vendedor) (pessoa)).getMetaMensal() <= 0) {
					throw new Exception("Erro, meta negativa ou nula");
				}
			}
		}
		pessoas.add(p);
	}

	Cliente BuscarClientePorCpf(String cpf) throws Exception {
		for (Pessoa p : pessoas) {
			if (((Cliente) p).getCpf().equals(cpf)) {
				return (Cliente) p;
			}
		}

		throw new Exception("Cliente nao encontrado!");
	}

	Vendedor BuscarVendedorPorCpf(String cpf) throws Exception {
		for (Pessoa p : pessoas) {
			if (((Vendedor) p).getCpf().equals(cpf)) {
				return (Vendedor) p;
			}
		}

		throw new Exception("Vendedor nao encontrado!");
	}

	void FazeCompraFornecedor(ArrayList<ItemCompra> itemcompra, Fornecedor fornecedor) throws Exception {
		ArrayList<ItemCompra> tmp = itemcompra;

		for (ItemCompra itemCompra2 : itemcompra) {
			for (ItemCompra i : tmp) {
				if (i.getProduto().compareTo(itemCompra2.getProduto()) == 0) {

					throw new Exception("Produto repitido");

				}

			}
		}

		for (ItemCompra itemCompra2 : itemcompra) {
			for (Produto p : produtos) {
				if (itemCompra2.getProduto().compareTo(p) == 0) {
					int c = p.getEstoque();
					p.setEstoque(++c);
				}
			}

		}
	}
}
