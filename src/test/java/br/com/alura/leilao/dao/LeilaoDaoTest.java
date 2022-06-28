package br.com.alura.leilao.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.util.builder.UsuarioBuilder;


class LeilaoDaoTest {

	private LeilaoDao dao;
	private EntityManager em;
	
	@BeforeEach
	public void beforeEach() {
		this.em = JPAUtil.getEntityManager();
		this.dao = new LeilaoDao(em);
		
		em.getTransaction().begin();
	}
	
	@AfterEach
	public void afterEach() {
		em.getTransaction().rollback();
	}
	

	/**
	 * pense em testar apenas o que possa ter problemas 
	 * ou seja, numa DAO não faz sentido testar salvar, atualizar e deletar
	 * o certo é testar o que faz consulta apenas
	 * isso pq ela tem queries feitas por pessoas, então pode ter falhas
	**/
	
	@Test
	void deveriaCadastrarLeilao() {
		
		
		
		Usuario usuario = new UsuarioBuilder()
				.comNome("Fulano")
				.comEmail("fulano@email.com")
				.comSenha("1234")
				.criar();
		em.persist(usuario);
		
		Leilao leilao = new LeilaoBuilder()
				.comNome("Mochila")
				.comValorInicial("70")
				.comData(LocalDate.now())
				.comUsuario(usuario)
				.criar();
		leilao = dao.salvar(leilao);
		
		Leilao salvo = dao.buscarPorId(leilao.getId());
		Assert.assertNotNull(salvo);
	}
	
	@Test
	void deveriaAtualizarLeilao() {
		Usuario usuario = new UsuarioBuilder()
				.comNome("Fulano")
				.comEmail("fulano@email.com")
				.comSenha("1234")
				.criar();
		em.persist(usuario);
		
		Leilao leilao = new LeilaoBuilder()
				.comNome("Mochila")
				.comValorInicial("70")
				.comData(LocalDate.now())
				.comUsuario(usuario)
				.criar();
		leilao = dao.salvar(leilao);
		
		leilao.setNome("Cell");
		leilao.setValorInicial(new BigDecimal("400"));
		
		leilao = dao.salvar(leilao);		
		
		Leilao salvo = dao.buscarPorId(leilao.getId());
		Assert.assertEquals("Cell", salvo.getNome());
		Assert.assertEquals(new BigDecimal("400"), salvo.getValorInicial());
		
	}

}
