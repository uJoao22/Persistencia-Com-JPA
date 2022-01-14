package br.com.alura.loja.testes;

//import java.math.BigDecimal;

import javax.persistence.EntityManager;

//import br.com.alura.loja.dao.CategoriaDao;
//import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
//import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {

		Categoria celulares = new Categoria("CELULARES");
		
		EntityManager em = JPAUtil.getEntityManager();

		//Iniciando a transação para poder inserir dados no banco de dados
		em.getTransaction().begin();

		//Persistindo a categoria no banco
		em.persist(celulares);
		
		//Alterando um dado apos a persistencia dele no banco
		celulares.setNome("XPTO");
		
		//Dando um flush nas alteraçoes acima, atualizando o banco com elas
		em.flush();
		
		//Limpando as entidades
		em.clear();
		
		//Fazendo um merge, voltando o EntityManager para o estado antes de ser fechado
		celulares = em.merge(celulares);
		
		//Fazendo uma alteração, apó ter fechado o EntityManager - Não deve ser alterada
		celulares.setNome("1234");
		
		em.flush();
	}
}