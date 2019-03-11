package com.cyclos.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCase8PageObjects {
	static WebDriver driver;
	public TestCase8PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='menuText'][contains(text(),'Account')]")
	private WebElement AccountTab;

	@FindBy(xpath = "//span[contains(text(),'Account Information')]")
	private WebElement AccountInformationSubLink;

	@FindBy(xpath = "//span[contains(text(),'Loans')]")
	private WebElement LoansSubLink;
	
	@FindBy(xpath = "//span[contains(text(),'Scheduled payments')]")
	private WebElement ScheduledPaymentsSubLink;
	
	@FindBy(xpath = "//span[contains(text(),'Invoices')]")
	private WebElement InvoiceSubLink;
	
	@FindBy(xpath = "//span[contains(text(),'Member Payment')]")
	private WebElement MemberPaymentSubLink;
	
	@FindBy(id = "memberUsername")
	private WebElement MemberUsername;
	
	@FindBy(id = "amount")
	private WebElement AmountTextField;
	
	@FindBy(id = "description")
	private WebElement DescriptionField;
	
	@FindBy(id = "submitButton")
	private WebElement SubmitButton;
	
	@FindBy(xpath = "//td[@class='label']")
	private WebElement PaymentLabel;
	
	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement SubmitButton1;
	
	@FindBy(xpath = "//td[@align='center']")
	private WebElement PaymentSuccessMessage;
	
	@FindBy(xpath = "//td[contains(text(),'Search transactions on Member account')]")
	private WebElement SearchTransaction;
	
	public void ValidateAccountTabSublinks() {
		this.AccountTab.click();

		if (this.AccountInformationSubLink.isDisplayed()) {
			System.out.println("Account Information link is displayed.");
		} else {
			System.out.println("Account Information link is not displayed.");
		}

		if (this.LoansSubLink.isDisplayed()) {
			System.out.println("Loans link is displayed.");
		} else {
			System.out.println("Loans link is displayed.");
		}

		if (this.ScheduledPaymentsSubLink.isDisplayed()) {
			System.out.println("Scheduled payments link is displayed.");
		} else {
			System.out.println("Scheduled payments link is not displayed.");
		}

		if (this.InvoiceSubLink.isDisplayed()) {
			System.out.println("Invoices link is displayed.");
		} else {
			System.out.println("Invoices link is not displayed.");
		}
	}
	
	public void MemberPayment(String MemberUsername, String Amount, String Description) throws InterruptedException {
		this.MemberPaymentSubLink.click();
		this.MemberUsername.sendKeys(MemberUsername);
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		this.AmountTextField.sendKeys(Amount);
		this.DescriptionField.sendKeys(Description);
		this.SubmitButton.click();
		if (this.PaymentLabel.isDisplayed()) {
			System.out.println("You are about to perform the following payment: with entered details is displayed.");
		} else {
			System.out
					.println("You are about to perform the following payment: with entered details is not displayed.");
		}

		this.SubmitButton1.click();
		if (driver.findElement(By.xpath("//td[@align='center']")).isDisplayed()) {
			System.out.println("The payment has been performed  message is displayed.");
		} else {
			System.out.println("The payment has been performed  message is not displayed.");
		}
	}
	
	public void ValidateAccountInformation() {
		this.AccountInformationSubLink.click();
		if (this.SearchTransaction
				.isDisplayed()) {
			System.out.println("transaction details made by member is displayed.");
		} else {
			System.out.println("transaction details made by member is not displayed.");
		}
	}
	
}
