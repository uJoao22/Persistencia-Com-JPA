package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		//Um objeto do tipo produto recebe o resultado do método que busca informações no banco de dados
		//através do id passado por parametro
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println("O celular "+p.getNome()+", possui "+p.getDescricao()+" e está custando: R$"+p.getPreco());

		//Executando o método buscarTodos e retornando uma Lista de objetos Produto
		List<Produto> todos = produtoDao.buscarTodos();

		//Fazendo um forEach com lambda para imprimir os resultados da lista
		todos.forEach(p2 -> System.out.println(p2.getNome()));
		
		List<Produto> nomes = produtoDao.buscarPorNome("Sansung A31");
		nomes.forEach(n -> System.out.println("Celular correspondente a pesquisa: "+n.getNome()+" - R$"+n.getPreco()));
		
		List<Produto> categorias = produtoDao.buscarPorCategoria("CELULARES");
		categorias.forEach(c -> System.out.println("O produto "+c.getNome()+" pertence a categoria de CELULARES"));
		
		BigDecimal precoProduto = produtoDao.buscarPrecoProdutoComNome("MacBook Pro");
		System.out.println("O preço do MacBook Pro é de: R$"+precoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria notebooks = new Categoria("NOTEBOOKS");
		Produto celular = new Produto("Iphone 13", "Otimas fotos", new BigDecimal("8000"), celulares);
		Produto celular2 = new Produto("Sansung A31", "Custo beneficio", new BigDecimal("1200"), celulares);
		Produto notebook = new Produto("MacBook Pro", "Excelente desempenho", new BigDecimal("10000"), notebooks);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(notebooks);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(celular2);
		produtoDao.cadastrar(notebook);
		
		em.getTransaction().commit();
		em.close();
	}
}