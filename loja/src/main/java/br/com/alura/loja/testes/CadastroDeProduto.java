package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {

		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Iphone 13", "Otimas fotos", new BigDecimal("8000"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		//Iniciando a transação para poder inserir dados no banco de dados
		em.getTransaction().begin();
		
		//Cadastrando a categoria na tabela de categorias
		categoriaDao.cadastrar(celulares);
		
		//Cadastrando o produto na tabela produtos
		produtoDao.cadastrar(celular);
		
		//Comittando a alteração no banco, após todas as ações
		em.getTransaction().commit();
		
		//Após terminar de usar o Entity Manager, tem que fecha-lo
		em.close();
	}
}