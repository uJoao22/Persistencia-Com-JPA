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
	
	public List<Produto> buscarPorNome(String nome){
		//Fazendo a consulta com filtro, basta adicionar o where e colocar a entidade renomeada, no caso, 
		//p.nomeDoAtributo que deseja filtrar e colocar a condição, para inserir variaveis na string,
		//basta adicionar : e dar um nome para a variavel
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		
		//Usando o método setParameter para dizer que aquela variavel depois dos : na string da query
		//é equivalente a tal variavel, que é colocada no segundo parametro
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}
	
	public List<Produto> buscarPorCategoria(String categoria){
		//Fazendo o relacionamento na jpql, colocando a entidade original, a entidade que irá se
		//relacionar e o atributo dessa entidade que é desejado pegar
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
		return em.createQuery(jpql, Produto.class).setParameter(1, categoria).getResultList();
	}

}