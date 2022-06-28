package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;


/**
 * 
 * @author yagoa
 *trata-se de um PageObject. Aqui não deve ter 
 *nada de Junit ou algo relacionado a testes.
 *Trata-se exclusivamente de uma página do Selenium
 */
public class LoginPage extends PageObject {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	
	
	public LoginPage() {
		super(null);
		browser.navigate().to(URL_LOGIN);
	}



	public void preencheFormularioDeLogin(String username, String password) {
		browser.findElement(By.id("username"))
		.sendKeys(username);
	browser.findElement(By.id("password"))
		.sendKeys(password);
		
	}


	public LeiloesPage submeterFormulario() {
		browser.findElement(By.id("login-form"))
		.submit(); /*ou poderia ser o click*/
		return new LeiloesPage(browser);
	}


	public boolean ePaginaDeLogin() {
		
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}


	public Object pegaNomeUsuarioLogado() {
	
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
			
		} catch (NoSuchElementException e) {
			return null;
		}
	}


	public void navegaParaPaginaDeLances(String url) {
		this.browser.navigate().to(url);
			
	}


	public boolean contemTexto(String texto) {
		
		return browser.getPageSource().contains(texto);
	}


	public boolean ePaginaDeLoginComDadosInvalidos() {
		
		return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
	}
	
		
		
	
}
