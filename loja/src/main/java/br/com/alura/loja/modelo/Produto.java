package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Mapeando a entidade

@Entity //Definindo que esta classe é uma entidade de um banco de dados
@Table(name="produtos") //Dizendo para a JPA que embora o nome da class seja Produto, o nome da entidade que ela corresponde é produtos, possuem nomes distintos
public class Produto {

	//@Column(name="nomeDaColuna") - Caso o nome da coluna e dos atributos não sejam os mesmos, é dessa forma que informa a JPA a qual coluna tal atributo se refere, e colocando esta anotaçãoo acima do atributo
	@Id //Informando que o atributo abaixo da anotação é a chave primaria da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Dizendo para o atributo como o valor é gerado pelo banco de dados
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();
	@Enumerated(EnumType.STRING) //Definindo que o ENUM abaixo será usado como uma string
	private Categoria  categoria;
	
	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}