package com.cyclos.POM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestCase2PageObjects {
	static WebDriver driver;
	public TestCase2PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Member Payment')]")
	public WebElement MemberPayment;
	public WebElement getMemberPaymentLink() {
		return MemberPayment;
	  }
	
	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	public WebElement MemberPaymentTable;
	public WebElement getMemberPaymentTable() {
		return MemberPaymentTable;
	  }
	
	@FindBy(id = "memberUsername")
	public WebElement MemberUsername;
	public WebElement getMemberUserName() {
		return MemberUsername;
	  }
	
	@FindBy(id = "memberName")
	public WebElement MemberName;
	public WebElement getMemberName() {
		return MemberName;
	  }
	
	@FindBy(id = "amount")
	public WebElement amount;
	public WebElement getAmountField() {
		return amount;
	  }
	
	@FindBy(id = "description")
	public WebElement description;
	public WebElement getDescriptionField() {
		return description;
	  }
	
	@FindBy(id = "submitButton")
	public WebElement SubmitButton;
	public WebElement getSubmitButton() {
		return SubmitButton;
	  }
	
	@FindBy(xpath = "//td[@class='label']")
	public WebElement TransactionConfirmation;
	public WebElement getTransactionConfirmation() {
		return TransactionConfirmation;
	  }
	
	@FindBy(xpath = "//input[@value='Submit']")
	public WebElement Submit2Button;
	public WebElement getSubmit2Button() {
		return Submit2Button;
	  }
	
	@FindBy(xpath = "//td[contains(text(),'Insufficient balance on Community account')]")
	public WebElement InsufBalance;
	public WebElement getInsufBalanceMessage() {
		return InsufBalance;
	  }
	
	//Setters
	public void ValidateMemberPaymentPage() {
		TestCase1PageObjects TC1 = new TestCase1PageObjects(driver);
		TC1.getAccountsTab().click();
		getMemberPaymentLink().click();
		Assert.assertTrue(getMemberPaymentTable().isDisplayed());
	}
	
	public void ValidateMemberCredintials(String memberUsername) throws InterruptedException {
		getMemberUserName().sendKeys(memberUsername);
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();

		String ExpectedMemName = "manzoor mehadi";
		String ActualMemName = getMemberName().getAttribute("value");
		System.out.println("Member Name -->" + ActualMemName);
		Assert.assertEquals(ActualMemName, ExpectedMemName);
		Thread.sleep(1000);
	}
	
	public void EnterPaymentDetails(String amount, String description) {
		getAmountField().sendKeys("10000");
		getDescriptionField().sendKeys("System");
		getSubmitButton().click();

		String TransactionConfirmation = getTransactionConfirmation().getText();
		System.out.println("Transaction Confirmation Message -- >" + TransactionConfirmation);
		String ExpTransactionConf = "You are about to perform the following payment:";
		if (ExpTransactionConf.equals(TransactionConfirmation)) {
			System.out.println("Transaction Confirmation Message is diaplyed.");
		} else {
			System.out.println("Transaction Confirmation Message is not diaplyed.");
		}
	}
	
	public void ValidateInsufficientBalanceMessage() throws InterruptedException {
		getSubmit2Button().click();
		Thread.sleep(2000);
		String ExpMessage = "Insufficient balance on Community account";
		String ActBankApp = getInsufBalanceMessage().getText();
		System.out.println("Application status: " + ActBankApp);
		if(ActBankApp.contains(ExpMessage)) {
			System.out.println("Insufficient balance on Community account message is displayed.");
		}else {
			System.out.println("Insufficient balance on Community account message is not displayed.");
		}
	}
}
