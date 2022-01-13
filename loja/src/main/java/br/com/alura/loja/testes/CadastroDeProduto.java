package br.com.alura.loja.testes;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {

		Produto celular = new Produto();
		celular.setNome("Iphone 13");
		celular.setDescricao("Otimas fotos");
		celular.setPreco(new BigDecimal("8000"));
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		//Iniciando a transação para poder inserir dados no banco de dados
		em.getTransaction().begin();
		
		dao.cadastrar(celular);
		
		//Comittando a alteração no banco, após todas as ações
		em.getTransaction().commit();
		
		//Após terminar de usar o Entity Manager, tem que fecha-lo
		em.close();
	}
}