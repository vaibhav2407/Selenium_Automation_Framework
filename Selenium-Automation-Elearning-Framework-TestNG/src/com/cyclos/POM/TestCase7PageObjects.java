package com.cyclos.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCase7PageObjects {
	static WebDriver driver;
	String SubtractionAmount = null;
	public TestCase7PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='menuText'][contains(text(),'Account')]")
	private WebElement AccountTab;

	@FindBy(xpath = "//span[contains(text(),'Account Information')]")
	private WebElement AccountInformationSubLink;

	@FindBy(xpath = "//span[contains(text(),'Loans')]")
	private WebElement LoansSubLink;

	@FindBy(xpath = "//td[contains(text(),'My loans')]")
	private WebElement MyLoans;

	@FindBy(xpath = "//tbody//tbody//tr[2]//td[4]//img[1]")
	private WebElement LoansViewIcon;

	@FindBy(xpath = "//td[contains(text(),'Loan details')]")
	private WebElement LoanDetails;

	@FindBy(id = "amountText")
	private WebElement AmountTextField;

	@FindBy(xpath = "//input[@value='Repay']")
	private WebElement RepayButton;

	@FindBy(xpath = "//tbody//tr[8]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
	private WebElement ViewLoansButton;

	public void ValidateAccountSublinks() throws InterruptedException {
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

	}

	public void NavigateViewLoansPage() throws InterruptedException {
		this.LoansSubLink.click();
		if (this.MyLoans.isDisplayed()) {
			System.out.println("Loans granted by admin is displayed.");
		} else {
			System.out.println("Loans granted by admin is not displayed.");
		}
		Thread.sleep(2000);
	}

	public void NavigateAndValidateLoanDetailsPage() {
		this.LoansViewIcon.click();

		if (this.LoanDetails.isDisplayed()) {
			System.out.println("Loan details with Loan repayment option is displayed.");
		} else {
			System.out.println("Loan details with Loan repayment option is not displayed.");
		}
	}

	public void ValidateTotalAmountAndRepay(String Amount) throws InterruptedException {
		// Total amount in the account
		String TotalAmount = this.AmountTextField.getAttribute("value");

		System.out.println("Total amount in the account --> " + TotalAmount);
		this.AmountTextField.clear();
		this.AmountTextField.sendKeys(Amount);
		this.RepayButton.click();

		String AmountConfirm = driver.switchTo().alert().getText();
		System.out.println("Amount Confirmation Message: " + AmountConfirm);

		String ExpectedMessage = "Are you sure to repay 500 units? Please, click ok to proceed";
		if (AmountConfirm.contains(ExpectedMessage)) {
			System.out.println("Amount Confirmation alert is displayed.");
		} else {
			System.out.println("Amount Confirmation alert is not displayed.");
		}

		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		String AmountConfirmationMessage = driver.switchTo().alert().getText();
		System.out.println("Amount Confirmation message: " + AmountConfirmationMessage);

		String ExpeMessage = "The repayment was succesfully processed ";
		if (AmountConfirmationMessage.equals(ExpeMessage)) {
			System.out.println("Repayment successfully done.");
		} else {
			System.out.println("Repayment is not done.");
		}
		driver.switchTo().alert().accept();
		String SubtractionAmount = this.AmountTextField.getAttribute("value");
		System.out.println("Substraction Amount --> " + SubtractionAmount);
		if (TotalAmount != SubtractionAmount) {
			System.out.println("paid amount substracted from total amount.");
		} else {
			System.out.println("paid amount not substracted from total amount.");
		}
		Thread.sleep(2000);
	}

	public void ValidateLoanDetailsWithPendingAmount() {
		this.ViewLoansButton.click();
		
		String RemainingAmount = driver
				.findElement(By.xpath("//td[contains(text(),'" + SubtractionAmount + " units')]")).getText();
		System.out.println("Remaing Amount --> " + RemainingAmount);
		if (RemainingAmount.contains((SubtractionAmount))) {
			System.out.println("Loan details with pending amount after repayment is displayed.");
		} else {
			System.out.println("Loan details with pending amount after repayment is not displayed.");
		}
	}
}
