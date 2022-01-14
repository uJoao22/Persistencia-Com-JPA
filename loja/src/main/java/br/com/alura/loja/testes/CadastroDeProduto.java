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
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Iphone 13", "Otimas fotos", new BigDecimal("8000"), celulares); 
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}
}