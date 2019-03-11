package com.cyclos.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCase10PageObjects {
	static WebDriver driver;

	public TestCase10PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "memberUsername")
	private WebElement memberUsername;

	@FindBy(xpath = "//td[contains(text(),'Profile of manzoor mehadi')]")
	private WebElement ProfileOfUserMember;

	@FindBy(xpath = "//tbody//tr[8]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[4]//input[1]")
	private WebElement GrantLoanSubmitButton;

	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	private WebElement GrantLoanMember;

	@FindBy(id = "amount")
	private WebElement AmountTextField;

	@FindBy(id = "description")
	private WebElement DescriptionField;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement SubmitButton;

	@FindBy(xpath = "//td[@class='label']")
	private WebElement LoanDetailsLabel;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement SubmitButton1;

	@FindBy(xpath = "//tbody//tr[8]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
	private WebElement ViewLoansButton;

	@FindBy(xpath = "//td[contains(text(),'Loans of manzoor mehadi')]")
	private WebElement UserLoans;

	@FindBy(xpath = "//span[contains(text(),'Loans')]")
	private WebElement LoansSubLink;

	@FindBy(xpath = "//td[contains(text(),'My loans')]")
	private WebElement MyLoans;

	public void NavigateMemberprofile(String MemberLogin) throws InterruptedException {
		this.memberUsername.sendKeys(MemberLogin);
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);
		if (this.ProfileOfUserMember.isDisplayed()) {
			System.out.println("Profile of manzoor mehadi is displayed.");
		} else {
			System.out.println("Profile of manzoor mehadi is not displayed.");
		}
	}

	public void NavigateGrantLoanPage() {
		this.GrantLoanSubmitButton.click();
		if (this.GrantLoanMember.isDisplayed()) {
			System.out.println("Grant loan to manzoor mehadi page is displayed");
		} else {
			System.out.println("Grant loan to manzoor mehadi page is not displayed");
		}
	}

	public void GrantLoan(String Amount, String Description) throws InterruptedException {
		this.AmountTextField.sendKeys(Amount);
		this.DescriptionField.sendKeys(Description);
		this.SubmitButton.click();
		Thread.sleep(3000);
		String LoanDetails = this.LoanDetailsLabel.getText();
		System.out.println("Loan Details Label--> " + LoanDetails);

		String ExpLabel = "You are about to grant the following loan:";
		if (LoanDetails.contains(LoanDetails)) {
			System.out.println("You are about to grant the following loan: with loan details is displayed.");
		} else {
			System.out.println("You are about to grant the following loan: with loan details is not displayed.");
		}

		this.SubmitButton1.click();
		Thread.sleep(2000);
		String SuccessMessage = driver.switchTo().alert().getText();
		System.out.println("Grant Loan Success Message: " + SuccessMessage);
		String ExpSuccMessage = "The loan was successfully granted";
		if (SuccessMessage.equals(ExpSuccMessage)) {
			System.out.println("The loan was successfully granted message is displayed.");
		} else {
			System.out.println("The loan was successfully granted message is not displayed.");
		}

		driver.switchTo().alert().accept();
	}

	public void NavigateMemberLoansPage() throws InterruptedException {
		this.ViewLoansButton.click();

		if (this.UserLoans.isDisplayed()) {
			System.out.println("Details of Loans granted to all member is displayed.");
		} else {
			System.out.println("Details of Loans granted to all member is not displayed.");
		}

		Thread.sleep(2000);
	}

	public void NavigateMyLoansPage() throws InterruptedException {
		this.LoansSubLink.click();
		if (this.MyLoans.isDisplayed()) {
			System.out.println("Loans granted by admin is displayed.");
		} else {
			System.out.println("Loans granted by admin is not displayed.");
		}

		Thread.sleep(2000);
	}
}
