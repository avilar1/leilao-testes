package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author yagoa
 *Trata-se de uma aplicação de teste. Tudo que não for teste
 *deve estar em outra classe, como uma page do Selenium.
 *Corretamente, aqui só há tratativas de testes Junit
 */
public class LoginTeste {

	private LoginPage paginaDeLogin;
	
	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();
	}
	
	@AfterEach
	public void afiterEach() {
		this.paginaDeLogin.fechar();
	}
	
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		paginaDeLogin.submeterFormulario();
	
	Assert.assertFalse(paginaDeLogin.ePaginaDeLogin());
	Assert.assertEquals("fulano", paginaDeLogin.pegaNomeUsuarioLogado());
	
	
	}
	
	
	@Test
	public void NãoLogarComDadosInvalidos() {
		paginaDeLogin.preencheFormularioDeLogin("errado", "wololo");
		paginaDeLogin.submeterFormulario();
	
		Assert.assertTrue(paginaDeLogin.ePaginaDeLoginComDadosInvalidos());
		Assert.assertNull(paginaDeLogin.pegaNomeUsuarioLogado());
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos"));
		
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestrinaSemEstarLogado() {
		paginaDeLogin.navegaParaPaginaDeLances("http://localhost:8080/leiloes/2");

		
		Assert.assertTrue(paginaDeLogin.ePaginaDeLogin());
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}
}
