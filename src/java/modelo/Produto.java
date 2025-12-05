
package modelo;

import java.time.LocalDate;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private String categoria;
    private String fornecedor;
    private LocalDate dataValidade;  
    private double peso;              
    private String marca;

    public Produto() {}

    // Construtor sem id para inserir
    public Produto(String nome, String descricao, double preco, int quantidade, String categoria,
                   String fornecedor, LocalDate dataValidade, double peso, String marca) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.dataValidade = dataValidade;
        this.peso = peso;
        this.marca = marca;
    }

    // Construtor com id para atualizar, buscar
    public Produto(int id, String nome, String descricao, double preco, int quantidade, String categoria,
                   String fornecedor, LocalDate dataValidade, double peso, String marca) {
        this(nome, descricao, preco, quantidade, categoria, fornecedor, dataValidade, peso, marca);
        this.id = id;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
}
