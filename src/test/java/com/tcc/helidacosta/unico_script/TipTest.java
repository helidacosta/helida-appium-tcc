package com.tcc.helidacosta.unico_script;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import com.tcc.helidacosta.exception.PlatformException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tcc.helidacosta.po.MainScreenPageObject;

import io.appium.java_client.AppiumDriver;

import static com.tcc.helidacosta.support.Utils.*;

/**
 * Teste para calcular o vaor total da conta, incluindo a gorjeta
 *
 * Este script é único, ou seja, não tem informações da plataforma.
 * A plataforma é informada no arquivo config.properties, sendo ela a responsável por enviar o teste para iOS ou Android
 */
public class TipTest {

	private AppiumDriver<?> driver;

    /*
     * O método retornaDriver é o responsável por retornar o driver correto (iOS ou Android) que é determinado
     * pela propriedade plataforma.execucao
     */
	@Before
	public void setUp() throws MalformedURLException, PlatformException {
		driver = retornaDriver(lerPropriedade("plataforma.execucao"));
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testCalculateDefaultTip() {
		MainScreenPageObject mainScreen = new MainScreenPageObject(driver);
				
		//calcula a porcentagem
		mainScreen.fillBillAmount("158.20");
		mainScreen.clickCalculateTip();
		
		//checa o resultado
		assertEquals("$23.73", mainScreen.getTipAmount());
		assertEquals("$181.93", mainScreen.getTotalAmount());
		
	}

}
