package controller;

import java.util.ArrayList;
import java.util.Collections;

import model.*;

public class Comercial {

	ArrayList<Pessoa> pessoas;
	ArrayList<Produto> produtos;
	ArrayList<Compra> compras;
	ArrayList<Venda> vendas;

	void InserirPessoa(Pessoa p) throws SisComException {
		for (Pessoa pessoa : pessoas) {
			if (p instanceof Cliente) {
				if (((Cliente) (pessoa)).getCpf().equals(((Cliente) p).getCpf())) {
					throw new SisComException("Erro, cliente ja cadastrado");
				}
			} else if (p instanceof Fornecedor) {
				if (((Fornecedor) pessoa).getCnpj().equals(((Fornecedor) p).getCnpj())) {
					throw new SisComException("Erro, Fornecedor ja cadastrado");
				}
			} else if (p instanceof Vendedor) {
				if (((Vendedor) (pessoa)).getCpf().equals(((Vendedor) p).getCpf())) {
					throw new SisComException("Erro, Vendedor ja cadastrado");
				} else if (((Vendedor) (pessoa)).getMetaMensal() <= 0) {
					throw new SisComException("Erro, meta negativa ou nula");
				}
			}
		}
		pessoas.add(p);
	}

	void ExcluirPessoa(Pessoa p) throws SisComException {
		if (p instanceof Fornecedor) {

			for (Compra C : compras) {
				if (C.getFornecedor().compareTo((Fornecedor) p) == 0) {
					throw new SisComException("Ja compramos desse fornecedor, Apague a compra primeiro.");
				}
			}
		} else if (p instanceof Vendedor) {
			for (Venda venda : vendas) {
				if (venda.getVendedor().compareTo((Vendedor) p) == 0) {
					throw new SisComException("Ja existe uma venda desse vendedor, Apague a venda primeiro.");
				}
			}
		} else if (p instanceof Cliente) {
			for (Venda venda : vendas) {
				if (venda.getCliente().compareTo((Cliente) p) == 0) {
					throw new SisComException("Ja existe uma venda para esse cliente, Apague a venda primeiro.");
				}
			}

		}
	}

	Produto BuscarProdutoPorCodigo(int codigo) throws SisComException {
		for (Produto p : produtos) {
			if (p.getCodigo() == codigo) {
				return p;
			}
		}
		throw new SisComException("Produto nao encontrado!");

	}

	Cliente BuscarClientePorCpf(String cpf) throws SisComException {
		for (Pessoa p : pessoas) {
			if (((Cliente) p).getCpf().equals(cpf)) {
				return (Cliente) p;
			}
		}

		throw new SisComException("Cliente nao encontrado!");
	}

	Vendedor BuscarVendedorPorCpf(String cpf) throws SisComException {
		for (Pessoa p : pessoas) {
			if (((Vendedor) p).getCpf().equals(cpf)) {
				return (Vendedor) p;
			}
		}

		throw new SisComException("Vendedor nao encontrado!");
	}

	Fornecedor BuscarFornecedorPorCnpj(String cnpj) throws SisComException {

		for (Pessoa p : pessoas) {
			if (((Fornecedor) p).getCnpj().equals(cnpj)) {
				return (Fornecedor) p;
			}
		}
		throw new SisComException("Vendedor nao encontrado!");
	}

	void FazerCompraFornecedor(ArrayList<ItemCompra> itemcompra, Fornecedor fornecedor) throws SisComException {
		ArrayList<ItemCompra> tmp = itemcompra;

		for (ItemCompra itemCompra2 : itemcompra) {
			for (ItemCompra i : tmp) {
				if (i.getProduto().compareTo(itemCompra2.getProduto()) == 0) {

					throw new SisComException("Produto repitido");

				}

			}
		}

		for (ItemCompra itemCompra2 : itemcompra) {
			int i = 0;
			for (Produto p : produtos) {
				if (itemCompra2.getProduto().compareTo(p) == 0) {
					p.AumentarEstoque(itemCompra2.getQuantCompra());
					i++;
				}
			}
			if (i == 0) {
				itemCompra2.getProduto().AumentarEstoque(itemCompra2.getQuantCompra());
				InserirProduto(itemCompra2.getProduto());
			}
		}
	}

	void InserirProduto(Produto produto) throws SisComException {

		for (Produto p : produtos) {
			if (p.getCodigo() == produto.getCodigo()) {
				throw new SisComException("Produto ja cadastrado!");
			}
		}
		produtos.add(produto);
	}

	void FazerVendaCliente(ArrayList<ItemVenda> itemVenda) throws SisComException {
		ArrayList<ItemVenda> tmp = itemVenda;

		for (ItemVenda itemVenda2 : itemVenda) {
			for (ItemVenda i : tmp) {
				if (i.getProduto().getCodigo() == itemVenda2.getProduto().getCodigo()) {

					throw new SisComException("Produto repitido");

				}

			}
		}

		for (ItemVenda itemVenda2 : itemVenda) {
			int i = 0;
			for (Produto p : produtos) {
				if (itemVenda2.getProduto().getCodigo() == p.getCodigo()) {
					p.AumentarEstoque(itemVenda2.getQuantVenda());
					i++;
				}
			}
			if (i == 0) {
				throw new SisComException("Produto comprado nao esta no estoque");
			}

		}
	}

	ArrayList<Produto> ListarProdutosPorOrdem(String nome) throws SisComException {
		ArrayList<Produto> tmp = new ArrayList<Produto>();
		for (Produto p : produtos) {
			if (p.getNome().contains(nome)) {
				tmp.add(p);
			}
		}
		if (tmp.size() > 0) {
			Collections.sort(tmp);
		} else {
			throw new SisComException("Nenhum produto com esse nome.");
		}
		return tmp;

	}

	void ExcluirProduto(int codigo) throws SisComException {
		Produto produto = BuscarProdutoPorCodigo(codigo);
		for (Venda iv : vendas) {
			for (VendaItens i : iv.getVendaItens()) {
				if (i.getProduto().getCodigo() == produto.getCodigo()) {
					throw new SisComException("Produto cadastrado em uma venda");
				}
			}
		}
		for (Compra compra : compras) {
			for (ItemCompra ci : compra.getCompraItens()) {
				if (ci.getProduto().getCodigo() == produto.getCodigo()) {
					throw new SisComException("Produto cadastrado em uma compra!");
				}

			}
		}
		if (!produtos.remove(produto)) {
			throw new SisComException("Produto inexistente");
		}
	}

	ArrayList<Produto> ListarProdutosEstoqueBaixo() throws SisComException {
		ArrayList<Produto> tmp = new ArrayList<Produto>();
		for (Produto p : produtos) {
			if (p.getEstoque()<p.getEstoqueMinimo()) {
				tmp.add(p);
			}
		}
		if (tmp.size() > 0) {
			Collections.sort(tmp);
		} else {
			throw new SisComException("Nenhum produto com estoque baixo");
		}
		return tmp;

	}

}