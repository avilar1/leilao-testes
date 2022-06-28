package br.com.alura.leilao.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.UsuarioBuilder;


class UsuarioDaoTest {

	private UsuarioDao dao;
	private EntityManager em;
	
	@BeforeEach
	public void beforeEach() {
		this.em = JPAUtil.getEntityManager();
		this.dao = new UsuarioDao(em);
		
		em.getTransaction().begin();
	}
	
	@AfterEach
	public void afterEach() {
		em.getTransaction().rollback();
	}
	
	
	
	/**
	 * injeção de dependência por construtor 
	 * auxiliam os testes
	**/
	
	
	@Test
	void deveriaBuscaUsuarioPeloUser() {		
		Usuario usuario = new UsuarioBuilder()
				.comNome("Fulano")
				.comEmail("fulano@email.com")
				.comSenha("1234")
				.criar();
		
		em.persist(usuario);
			
		Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
			Assert.assertNotNull(encontrado);
	}
	
	@Test
	void deveriaRemoverUsuario() {
		Usuario usuario = new UsuarioBuilder()
				.comNome("Fulano")
				.comEmail("fulano@email.com")
				.comSenha("1234")
				.criar();
		
		em.persist(usuario);
		
		dao.deletar(usuario);
		
		Assert.assertThrows(NoResultException.class, 
				()-> this.dao.buscarPorUsername(usuario.getNome()));
		
	}
	
	@Test
	void naoDeveriaBuscaUsuarioPeloUser() {
		Usuario usuario = new UsuarioBuilder()
				.comNome("Fulano")
				.comEmail("fulano@email.com")
				.comSenha("1234")
				.criar();
		
		em.persist(usuario);
		
			Assert.assertThrows(NoResultException.class, 
					()-> this.dao.buscarPorUsername("betty"));
			
	}

}
