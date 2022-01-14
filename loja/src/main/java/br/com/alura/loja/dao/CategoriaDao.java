package br.com.alura.loja.dao;

import javax.persistence.EntityManager;
import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Categoria categoria) {
		// Inserindo o objeto celular na entidade produto, a JPA, sabe qual tabela é para inserir pois 
		// celular é do tipo Produto, que é uma entidade, onde informei que ela é uma entidade, a qual 
		// tabela corresponde, e até mesmo a chave primaria, Atrvés de tudo isso a JPA conseguiu saber 
		// onde é para inserir o objeto
		this.em.persist(categoria);
	}

}