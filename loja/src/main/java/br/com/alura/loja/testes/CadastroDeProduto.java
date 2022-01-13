package br.com.alura.loja.testes;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {
	public static void main(String[] args) {

		Produto celular = new Produto();
		celular.setNome("Iphone 13");
		celular.setDescricao("Otimas fotos");
		celular.setPreco(new BigDecimal("8000"));
		
		//Fazendo a conexão com o banco de dados pela JPA, através do EntityManager, o gerente de entidade
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		
		//Iniciando a transação para poder inserir dados no banco de dados
		em.getTransaction().begin();
		
		//Inserindo o objeto celular na entidade produto, a JPA, sabe qual tabela é para inserir pois celular é do tipo Produto,
		//que é uma entidade, onde informei que ela é uma entidade, a qual tabela corresponde, e até mesmo a chave primaria,
		//Atrvés de tudo isso a JPA consegu saber onde é para inserir o objeto
		em.persist(celular);
		
		//Comittando a alteração no banco, após todas as ações
		em.getTransaction().commit();
		
		//Após terminar de usar o Entity Manager, tem que fecha-lo
		em.close();
	}
}