package com.cyclos.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TestCase6PageObjects {

	static WebDriver driver;

	public TestCase6PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='memberUsername']")
	private WebElement MemberLogin;

	@FindBy(xpath = "//tbody//tr[4]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
	private WebElement ManageAdvSubmitButton;

	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	private WebElement AdvHeaderTab;

	@FindBy(xpath = "//input[@id='newButton']")
	private WebElement InsertNewAdvSubmitButton;

	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	private WebElement NewAdvHeaderTab;

	@FindBy(name = "ad(title)")
	private WebElement TitleTextField;

	@FindBy(name = "ad(category)")
	private WebElement CategoryDropdown;

	@FindBy(name = "ad(price)")
	private WebElement price;

	@FindBy(id = "notExpirableCheck")
	private WebElement NotExpirableCheckBox;

	@FindBy(xpath = "//input[@id='saveButton']")
	private WebElement SaveButton;

	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	private WebElement LogoutTab;

	@FindBy(id = "cyclosUsername")
	private WebElement Username;

	@FindBy(id = "cyclosPassword")
	private WebElement Password;

	@FindBy(xpath = "//td[@colspan='2']//input[@value='Submit']")
	private WebElement SubmitButton;

	@FindBy(xpath = "//span[contains(text(),'Personal')]")
	private WebElement PersonalTab;

	@FindBy(xpath = "//span[contains(text(),'Profile')]")
	private WebElement ProfileSublink;

	@FindBy(xpath = "//span[contains(text(),'Messages')]")
	private WebElement MessagesSubLink;

	@FindBy(xpath = "//span[contains(text(),'Advertisements')]")
	private WebElement AdvSubLink;

	@FindBy(xpath = "//td[contains(text(),'My advertisements')]")
	private WebElement MyAdvSection;

	public void NavigateMemberSection(String MemberLogin) throws InterruptedException {
		this.MemberLogin.sendKeys(MemberLogin);
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);
	}

	public void NavigateManageAdvPage() {
		this.ManageAdvSubmitButton.click();
		if (this.AdvHeaderTab.isDisplayed()) {
			System.out.println(
					"Advertisements of selected member page with existing advertisement details is displayed.");
		} else {
			System.out.println(
					"Advertisements of selected member page with existing advertisement details is not displayed.");
		}
	}

	public void CreateNewAdv(String Title, String Category, String price, String Description)
			throws InterruptedException {
		Thread.sleep(2000);
		this.InsertNewAdvSubmitButton.click();
		if (this.NewAdvHeaderTab.isDisplayed()) {
			System.out.println("New Advertisement Page is displayed.");
		} else {
			System.out.println("New Advertisement Page is not displayed.");
		}

		this.TitleTextField.sendKeys(Title);
		Select s = new Select(this.CategoryDropdown);
		s.selectByVisibleText(Category);

		this.price.sendKeys(price);
		this.NotExpirableCheckBox.click();
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB).build().perform();
		a.sendKeys(Description).build().perform();

		this.SaveButton.click();
		String SuccessMessage = driver.switchTo().alert().getText();
		System.out.println("Success Message: " + SuccessMessage);
		String ExpectedMessage = "Advertisement inserted";
		if (SuccessMessage.equals(ExpectedMessage)) {
			System.out.println("Advertisement inserted successfully.");
		} else {
			System.out.println("Advertisement not inserted.");
		}

		driver.switchTo().alert().accept();
		Thread.sleep(2000);
	}

	public void LogoutUser() {
		this.LogoutTab.click();
		String LogoutMessage = driver.switchTo().alert().getText();
		System.out.println("Success Message: " + LogoutMessage);
		String ExpectedLogoutMessage = "Please confirm to logout";
		if (LogoutMessage.equals(ExpectedLogoutMessage)) {
			System.out.println("User loggedout successfully.");
		} else {
			System.out.println("User not loggedout.");
		}

		driver.switchTo().alert().accept();
	}

	public void MemberLogin(String Username1, String Password1) {
		this.Username.sendKeys(Username1);
		this.Password.sendKeys(Password1);
		this.SubmitButton.click();
	}

	public void ValidatePersonalTabSubLinks() throws InterruptedException {
		this.PersonalTab.click();
		if (this.ProfileSublink.isDisplayed()) {
			System.out.println("Profile sub link is displayed.");
		} else {
			System.out.println("Profile sub link is not displayed.");
		}

		if (this.MessagesSubLink.isDisplayed()) {
			System.out.println("Messages sub link is displayed.");
		} else {
			System.out.println("Messages sub link is not displayed.");
		}

		if (this.AdvSubLink.isDisplayed()) {
			System.out.println("Advertisements sub link is displayed.");
		} else {
			System.out.println("Advertisements sub link is not displayed.");
		}

		this.AdvSubLink.click();
		if (this.MyAdvSection.isDisplayed()) {
			System.out.println("Advertisement added by admin is displayed.");
		} else {
			System.out.println("Advertisement added by admin is not displayed.");
		}
		Thread.sleep(2000);
	}
}
