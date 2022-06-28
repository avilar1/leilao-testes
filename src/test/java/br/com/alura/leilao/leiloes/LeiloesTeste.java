package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;


public class LeiloesTeste {

	private LeiloesPage paginaDeLeiloes;
	
	private CadastroLeilaoPage paginaDeCadastro;
	
	@BeforeEach
	public void beforeEach() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		this.paginaDeLeiloes = paginaDeLogin.submeterFormulario();
		this.paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
		
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLeiloes.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {
		
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do dia" + hoje;
		String valor = "500.00";
		
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
	
		Assert.assertTrue(paginaDeLeiloes.eLeilaoCadastrado(nome, valor, hoje));
	
	}
	
	@Test
	public void deveriaValidarCadastroDeLeilao() {
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", ""); /*não envie null*/
		
		Assert.assertFalse(this.paginaDeCadastro.ePaginaAtual());
		Assert.assertTrue(this.paginaDeLeiloes.ePaginaAtual());
		
		Assert.assertTrue(this.paginaDeCadastro.MensagemDeValidacaoAparecem());
		
	}
	
}
