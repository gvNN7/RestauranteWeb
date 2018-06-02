package usjt.br.projeto.crud;

public class Produto {
	public int id;
	public String nome;
	public String descricao;
	public double preco;
	
	public Produto(int id, String nome, String descricao, double preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	private int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	private String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	private String getDescricao() {
		return descricao;
	}

	private void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private double getPreco() {
		return preco;
	}

	private void setPreco(double preco) {
		this.preco = preco;
	}
}
