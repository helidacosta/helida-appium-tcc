package com.tcc.helidacosta.support;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.tcc.helidacosta.exception.PlatformException;
import com.tcc.helidacosta.exception.PropertyException;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Utils {


    private Utils() {}

	/**
	 * Le o valor de uma propriedade do arquivo <i>config.properties<i/>
	 * @param propriedade nome da propriedade
	 * @return valor da propriedade
	 */
	public static String lerPropriedade(String propriedade) {
		Properties prop;
		String valor = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("config.properties")));

            valor = prop.getProperty(propriedade);
			
			if (valor == null || valor == "") {
				throw new PropertyException();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valor;
	}

    /**
     * Retorna o driver (iOS ou Android) para a execução dos testes de acordo com a plataforma informada
     * @param plataforma plataforma ios ou android
     * @return IOSDriver para execução em iOS ou AndroidDriver para execução em Android
     * @throws MalformedURLException
     */
	public static AppiumDriver<?> retornaDriver(String plataforma) throws MalformedURLException, PlatformException {
		AppiumDriver<?> driver = null;
		DesiredCapabilities capacidade = new DesiredCapabilities();

		final Boolean isHybrid = Boolean.parseBoolean(lerPropriedade("execucao.hibrida"));
		
		if (isHybrid) {
			capacidade.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
		}
		
		String urlCompleta = "http://" + lerPropriedade("execucao.ip") + ":" + lerPropriedade("execucao.porta") + "/wd/hub";
		
		switch (plataforma.toLowerCase()) {
		
		case "ios":
			
			if (isHybrid) {
				capacidade.setCapability(MobileCapabilityType.APP, new File(lerPropriedade("ios.app.hibrido")).getAbsolutePath());
			}else {	capacidade.setCapability(MobileCapabilityType.APP, new File(lerPropriedade("ios.app.nativo")).getAbsolutePath());}
			
			capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, lerPropriedade("ios.nome.dispositivo"));
			capacidade.setCapability(MobileCapabilityType.PLATFORM_VERSION, lerPropriedade("android.versao.plataforma"));
			capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
			
			if ( Boolean.parseBoolean(lerPropriedade("ios.xcode8"))) {
				capacidade.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			}
			
			driver = new IOSDriver<>(new URL(urlCompleta), capacidade);
			break;

		case "android":
			
			if (isHybrid) {
				capacidade.setCapability(MobileCapabilityType.APP, new File(lerPropriedade("android.app.hibrido")).getAbsolutePath());}
			else {capacidade.setCapability(MobileCapabilityType.APP, new File(lerPropriedade("android.app.nativo")).getAbsolutePath());}
			
			capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, lerPropriedade("android.nome.dispositivo"));
			capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			
			driver = new AndroidDriver<>(new URL(urlCompleta), capacidade);
			break;
			
		    default:
		        throw new PlatformException();

		}
		
		return driver;
	}
}
