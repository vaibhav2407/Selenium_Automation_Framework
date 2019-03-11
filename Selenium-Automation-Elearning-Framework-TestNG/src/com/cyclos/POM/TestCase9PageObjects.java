package com.cyclos.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCase9PageObjects {
	static WebDriver driver;
	public TestCase9PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='menuText'][contains(text(),'Account')]")
	private WebElement AccountTab;
	
	@FindBy(xpath = "//span[contains(text(),'Manage Currencies')]")
	private WebElement ManageCurrencies;
	
	@FindBy(xpath = "//span[contains(text(),'Manage Accounts')]")
	private WebElement ManageAccounts;
	
	@FindBy(xpath = "//span[contains(text(),'System Payment')]")
	private WebElement SystemPayment;
	
	@FindBy(xpath = "//span[contains(text(),'Member Payment')]")
	private WebElement MemberPayment;
	
	@FindBy(id = "memberUsername")
	private WebElement MemberUsername;
	
	@FindBy(id = "amount")
	private WebElement Amount;
	
	@FindBy(id = "description")
	private WebElement Description;
	
	@FindBy(id = "submitButton")
	private WebElement SubmitButton;
	
	@FindBy(xpath = "//td[@class='label']")
	private WebElement paymentLabel;
	
	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement SubmitButton1;
	
	@FindBy(xpath = "//td[@align='center']")
	private WebElement PaymentMessage;
	
	@FindBy(xpath = "//span[contains(text(),'Logged user: manzoor - manzoor mehadi')]")
	private WebElement LoggedInUsername;
	
	@FindBy(xpath = "//span[contains(text(),'Account Information')]")
	private WebElement AccountInformation;
	
	@FindBy(xpath = "//span[contains(text(),'Scheduled payments')]")
	private WebElement ScheduledPayments;
	
	@FindBy(xpath = "//td[contains(text(),'Search transactions on Member account')]")
	private WebElement SearchTransaction;
	
	
	public void ValidateAccountTabSubLinks() {
		this.AccountTab.click();

		if (this.ManageCurrencies.isDisplayed()) {
			System.out.println("Manage Currencies link is displayed.");
		} else {
			System.out.println("Manage Currencies link is not displayed.");
		}

		if (this.ManageAccounts.isDisplayed()) {
			System.out.println("Manage Accounts link is displayed.");
		} else {
			System.out.println("Manage Accounts link is not displayed.");
		}

		if (this.SystemPayment.isDisplayed()) {
			System.out.println("System Payment link is displayed.");
		} else {
			System.out.println("System Payment link is not displayed.");
		}
	}
	
	public void MemberPayment(String MemberUsername, String Amount, String Description) throws InterruptedException {
		this.MemberPayment.click();
		if (this.MemberPayment.isDisplayed()) {
			System.out.println("Payment system to member page is displayed.");
		} else {
			System.out.println("Payment system to member page is not displayed.");
		}

		this.MemberUsername.sendKeys(MemberUsername);
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		this.Amount.sendKeys(Amount);
		this.Description.sendKeys(Description);
		this.SubmitButton.click();

		if (this.paymentLabel.isDisplayed()) {
			System.out.println(
					"You are about to perform the following payment: message containing details is displayed.");
		} else {
			System.out.println(
					"You are about to perform the following payment: message containing details is not displayed.");
		}

		this.SubmitButton1.click();

		if (this.PaymentMessage.isDisplayed()) {
			System.out.println("The payment has been performed  message is displayed.");
		} else {
			System.out.println("The payment has been performed  message is not displayed.");
		}
		Thread.sleep(2000);
	}
	
	public void ValidateMemberLogin() {
		if (this.LoggedInUsername
				.isDisplayed()) {
			System.out.println("Logged user: manzoor - manzoor mehadi member home page is displayed.");
		} else {
			System.out.println("Logged user: manzoor - manzoor mehadi member home page is not displayed.");
		}
	}
	
	public void ValidateMemberAccountSublinks() {
		this.AccountTab.click();
		if (this.AccountInformation.isDisplayed()) {
			System.out.println("Account Information link is displayed.");
		} else {
			System.out.println("Account Information link is not displayed.");
		}

		if (this.ScheduledPayments.isDisplayed()) {
			System.out.println("Scheduled payments link is displayed.");
		} else {
			System.out.println("Scheduled payments link is not displayed.");
		}
	}
	
	public void ValidateTransactionDetails() throws InterruptedException {
		this.AccountInformation.click();
		if(this.SearchTransaction.isDisplayed()) {
			System.out.println("transaction details made by member is displayed.");
		}else {
			System.out.println("transaction details made by member is not displayed.");
		}
		Thread.sleep(2000);
	}
}
