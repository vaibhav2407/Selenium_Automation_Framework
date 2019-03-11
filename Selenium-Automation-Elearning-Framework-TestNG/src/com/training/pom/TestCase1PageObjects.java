package com.training.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestCase1PageObjects {
	static WebDriver driver;

	public TestCase1PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "cyclosUsername")
	public WebElement Username;

	public WebElement getUsernameField() {
		return Username;
	}

	@FindBy(id = "cyclosPassword")
	public WebElement Password;

	public WebElement getPasswordField() {
		return Password;
	}

	@FindBy(xpath = "//td[@colspan='2']//input[@value='Submit']")
	public WebElement SubmitBtn;

	public WebElement getSubmitButton() {
		return SubmitBtn;
	}

	@FindBy(xpath = "//span[@class='menuText'][contains(text(),'Accounts')]")
	public WebElement AccountsTab;

	public WebElement getAccountsTab() {
		return AccountsTab;
	}

	@FindBy(xpath = "//span[contains(text(),'Manage Currencies')]")
	public WebElement ManageCurrencies;

	public WebElement getManageCurrencyLink() {
		return ManageCurrencies;
	}

	@FindBy(xpath = "//span[contains(text(),'Manage Accounts')]")
	public WebElement ManageAccounts;

	public WebElement getManageAccountsLink() {
		return ManageAccounts;
	}

	@FindBy(xpath = "//span[contains(text(),'System Accounts')]")
	public WebElement SystemAccounts;

	public WebElement getSystemAccountsLink() {
		return SystemAccounts;
	}

	@FindBy(xpath = "//span[contains(text(),'Manage Loans')]")
	public WebElement ManageLoans;

	public WebElement getManageLoansLink() {
		return ManageLoans;
	}

	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	public WebElement ManageLoansTable;

	public WebElement getManageLoansTable() {
		return ManageLoansTable;
	}

	@FindBy(id = "memberUsername")
	public WebElement memberUsername;

	public WebElement getMemberUsername() {
		return memberUsername;
	}

	@FindBy(id = "memberName")
	public WebElement MemeberName;

	public WebElement getMembername() {
		return MemeberName;
	}

	@FindBy(xpath = "//input[@value='Search']")
	public WebElement SearchButton;

	public WebElement getSearchButton() {
		return SearchButton;
	}

	@FindBy(xpath = "//td[contains(text(),'Search results')]")
	public WebElement SearchResults;

	public WebElement getSearchResults() {
		return SearchResults;
	}

	@FindBy(xpath = "//tbody//tr[4]//td[4]//img[1]")
	public WebElement LoadnView;

	public WebElement getLoanDetailsViewIcon() {
		return LoadnView;
	}

	@FindBy(xpath = "//td[contains(text(),'Loan details')]")
	public WebElement LoadnDetails;

	public WebElement getLoanDetails() {
		return LoadnDetails;
	}

	// Setters
	public void LoginIntoApplication(String Username1, String Password1) {
		getUsernameField().sendKeys(Username1);
		getPasswordField().sendKeys(Password1);
		getSubmitButton().click();
	}

	public void ClickAndValidateAccountTab() {
		getAccountsTab().click();

	}

	public void ValidateManageCurrensyLink() {
		Assert.assertTrue(getManageCurrencyLink().isDisplayed());
	}

	public void ValidateManageAccountsLink() {
		Assert.assertTrue(getManageAccountsLink().isDisplayed());
	}

	public void ValidateSystemAccountsLink() {
		Assert.assertTrue(getSystemAccountsLink().isDisplayed());
	}

	public void ValidateManageLoansLink() {
		Assert.assertTrue(getManageLoansLink().isDisplayed());
	}
	
	public void ValidateManageLoansPage() {
		getManageLoansLink().click();
		Assert.assertTrue(getManageLoansTable().isDisplayed());
	}
	
	public void ValidateMemberLogin(String memberUsername) throws InterruptedException {
		getMemberUsername().sendKeys(memberUsername);
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		String ExpectedMemName = "manzoor mehadi";
		String ActualMemName = getMembername().getAttribute("value");
		System.out.println("Member Name -->" + ActualMemName);
		Assert.assertEquals(ActualMemName, ExpectedMemName);
		Thread.sleep(1000);
	}
	
	public void ValidateSearchResults() {
		getSearchButton().click();
		if (getSearchResults().isDisplayed()) {
			System.out.println("loan details displayed successfully.");
		} else {
			System.out.println("loan details are not displayed.");
		}
	}
	
	public void ValidateLoanDetailsPage() {
		getLoanDetailsViewIcon().click();
		if (getLoanDetails().isDisplayed()) {
			System.out.println("Loan details are displayed.");
		} else {
			System.out.println("Loan details are not displayed.");
		}
	}
	public void CloseBrowser() {
		driver.close();
	}
}
