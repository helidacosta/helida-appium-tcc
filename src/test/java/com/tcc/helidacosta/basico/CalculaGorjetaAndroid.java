package com.tcc.helidacosta.basico;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

/**
 * Teste para calcular o valor total da gorjeta para um dispositivo Android
 *
 * A maioria das configurações estão diretamente no código, ao invés de usar o arquivo de configuração config.properties
 *
 * Esta classe serve para demostrar como é a criação de um script não único, ou seja, dependente da plataforma
 * usando a forma de localização direta no script (findElement)
 */
public class CalculaGorjetaAndroid {
	
	@Test
	public void testCalculateDefaultTip() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, new File("app/FasTip.apk").getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);

        AndroidDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), 
								  capabilities);

        driver.findElement(By.id("org.traeg.fastip:id/billAmtEditText")).sendKeys("100");
		driver.findElement(By.id("org.traeg.fastip:id/calcTipButton")).click();

		assertEquals("$15.00", driver.findElement(By.id("org.traeg.fastip:id/tipAmtTextView"))
			     .getText());
		assertEquals("$115.00", driver.findElement(By.id("org.traeg.fastip:id/totalAmtTextView"))
			     .getText());

		driver.quit();
	}

}
