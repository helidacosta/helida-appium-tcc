package com.tcc.helidacosta.po;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.temporal.ChronoUnit;

public class MainScreenPageObject {

	@AndroidFindBy(id = "org.traeg.fastip:id/billAmtEditText")
	@iOSFindBy(xpath = "//XCUIElementTypeTextField")
	@FindBy(id = "billAmount")
    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
	MobileElement billAmount;
	
	@AndroidFindBy(id = "org.traeg.fastip:id/calcTipButton")
	@iOSFindBy(accessibility = "Calculate Tip")
	@FindBy(id = "calcTip")
	MobileElement calculateTip;
	
	@AndroidFindBy(id = "org.traeg.fastip:id/menu_settings")
    @iOSFindBy(id = "Settings")
	@FindBy(id = "settingsButton")
    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
	MobileElement menuSettings;
	
	@AndroidFindBy(id = "org.traeg.fastip:id/tipAmtTextView")
	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[2]")
	@FindBy(id = "tipAmount")
	@WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
	MobileElement tipAmount;
	
@AndroidFindBy(id = "org.traeg.fastip:id/totalAmtTextView")
@iOSFindBy(xpath = "//XCUIElementTypeStaticText[4]")
@FindBy(id = "totalAmount")
@WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
MobileElement totalAmount;
	
public MainScreenPageObject(AppiumDriver<?> driver) {
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
	
	public void fillBillAmount(String amount) {
		billAmount.sendKeys(amount);
	}

    public void clickCalculateTip() {
        calculateTip.click();
    }

public String getTipAmount() {
	return tipAmount.getText();
}

public void clickMenuSettings() {
	menuSettings.click();
}

public String getTotalAmount() {
	return totalAmount.getText();
}
	
}
