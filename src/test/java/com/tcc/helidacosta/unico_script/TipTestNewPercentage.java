package com.tcc.helidacosta.unico_script;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import com.tcc.helidacosta.exception.PlatformException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tcc.helidacosta.po.MainScreenPageObject;
import com.tcc.helidacosta.po.SettingsScreenPageObject;

import io.appium.java_client.AppiumDriver;

import static com.tcc.helidacosta.support.Utils.*;

/**
 * Teste que altera o percentual da gorjeta
 *
 * Este script é único, ou seja, não tem informações da plataforma.
 * A plataforma é informada no arquivo config.properties, sendo ela a responsável por enviar o teste para iOS ou Android
 */
public class TipTestNewPercentage {

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
		
		//click menu settings
		mainScreen.clickMenuSettings();

		// instancia o page object da tela de configurações
		SettingsScreenPageObject settingsScreen = new SettingsScreenPageObject(driver);

		//altera a porcentagem
		settingsScreen.clearPercentage();
		settingsScreen.fillTipPercentage("20");
		settingsScreen.clickSettingsButton();

		// volta a instanciar o page object da tela principal, para que seja possível localizar os componentes
        mainScreen = new MainScreenPageObject(driver);
		//calcula a porcentagem
		mainScreen.fillBillAmount("179.83");
		mainScreen.clickCalculateTip();
		
		//checa o resultado
		assertEquals("$35.97", mainScreen.getTipAmount());
		assertEquals("$215.80", mainScreen.getTotalAmount());
		
	}
}