package com.tcc.helidacosta.basico;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

/**
 * Teste para calcular o valor total da gorjeta para um dispositivo iOS
 *
 * A maioria das configurações estão diretamente no código, ao invés de usar o arquivo de configuração config.properties
 *
 * Esta classe serve para demostrar como é a criação de um script não único, ou seja, dependente da plataforma
 * usando a forma de localização direta no script (findElement)
 */
public class CalculaGorjetaIOS {
	
	@Test
	public void testCalculateDefaultTip() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, new File("app/FasTip.app").getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone SE");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

        IOSDriver<MobileElement> driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

	driver.findElement(By.xpath("//XCUIElementTypeTextField")).sendKeys("100");
	driver.findElementByAccessibilityId("Calculate Tip").click();

	assertEquals("$15.00", driver.findElement(By.xpath("//XCUIElementTypeStaticText[2]")).getText());
	assertEquals("$115.00", driver.findElement(By.xpath("//XCUIElementTypeStaticText[4]")).getText());
        driver.quit();
	}

}
