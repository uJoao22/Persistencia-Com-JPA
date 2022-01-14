package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		// Inserindo o objeto celular na entidade produto, a JPA, sabe qual tabela é para inserir pois 
		// celular é do tipo Produto, que é uma entidade, onde informei que ela é uma entidade, a qual 
		// tabela corresponde, e até mesmo a chave primaria, Atrvés de tudo isso a JPA conseguiu saber 
		// onde é para inserir o objeto
		this.em.persist(produto);
	}
	
	//Vai me retornar um objeto do tipo Produto
	public Produto buscarPorId(Long id) {
		//Usando o método find para fazer a consulta no banco de dados, porém ele só busca por uma entidade
		//EntityManager.find(entidade que quero buscar, id da entidade);
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		//No JPQL quando crio uma consulta, inves de informar o nome da tabela, devo informar o nome da 
		//entidade, e ao invés de usar o * para selecionar tudo, eu renomeio a entidade e peço pra me
		//retornar o apelido dela, ou seja, retornar tudo dessa entidade
		String jpql = "SELECT p FROM Produto p";

		//Retornando uma lista de objetos Produto, montando a query com o método 
		//createQuery(query, tipo da entidade que será devolvida) e executando a query com o método 
		//getResultList()
		return em.createQuery(jpql, Produto.class).getResultList();
	}

}