package com.tcc.helidacosta.po;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsScreenPageObject {

	@AndroidFindBy(id = "org.traeg.fastip:id/tipPercentageEditText")
	@iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"FasTip\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField")
	@FindBy(id = "tipPercentage")
	MobileElement fillPercentage;
	
	@AndroidFindBy(id = "org.traeg.fastip:id/saveSettingsButton")
	@iOSFindBy(accessibility = "Done")
	@FindBy(id = "saveSettings")
	MobileElement settingsButton;
	
	public SettingsScreenPageObject(AppiumDriver<?> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void clickSettingsButton() {
		settingsButton.click();
	}

	public void fillTipPercentage(String percentage) {
		fillPercentage.sendKeys(percentage);
	}
	
	public void clearPercentage() {
		fillPercentage.clear();
	}
}