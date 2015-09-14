package br.com.caelum.xstream;

public class Produto {

	public Produto(String nome, double preco, String descricao, int codigo) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.codigo = codigo;
	}

	private String nome;
	private double preco;
	private String descricao;
	private int codigo;

}
