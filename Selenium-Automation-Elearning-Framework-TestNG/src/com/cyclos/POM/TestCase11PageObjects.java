package com.cyclos.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TestCase11PageObjects {
	static WebDriver driver;

	public TestCase11PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='menuText'][contains(text(),'Messages')]")
	private WebElement MessagesTab;

	@FindBy(xpath = "//span[@class='subMenuText'][contains(text(),'Messages')]")
	private WebElement MessagesSubLink;

	@FindBy(xpath = "//span[contains(text(),'Message Categories')]")
	private WebElement MessagesCategorySubLinks;

	@FindBy(xpath = "//span[contains(text(),'SMS mailings')]")
	private WebElement SMSMailingSublink;

	@FindBy(xpath = "//td[contains(text(),'Message list')]")
	private WebElement MessagesList;

	@FindBy(id = "messageBoxSelect")
	private WebElement MessageBox;

	@FindBy(xpath = "//input[@id='newButton']")
	private WebElement NewButton;

	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	private WebElement SendMessagePageHeader;

	@FindBy(id = "subjectText")
	private WebElement SubjectTextBox;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement SubmitButton;

	@FindBy(id = "memberUsername")
	private WebElement MemberUsername;

	public void ValidateMessagesLinks() {
		this.MessagesTab.click();
		Assert.assertTrue(this.MessagesSubLink.isDisplayed());
		Assert.assertTrue(this.MessagesCategorySubLinks.isDisplayed());
		Assert.assertTrue(this.SMSMailingSublink.isDisplayed());

	}

	public void NavigateMessagesPage() {
		this.MessagesSubLink.click();

		if (this.MessagesList.isDisplayed()) {
			System.out.println("User navigated message list page");
		} else {
			System.out.println("User not navigated message list page");
		}
		Select s = new Select(this.MessageBox);
		s.selectByVisibleText("Sent items");
		String SelectedValue = this.MessageBox.getAttribute("value");
		System.out.println("Dropdown selected value: " + SelectedValue);
		if (SelectedValue.equals("SENT")) {
			System.out.println("Sent items option is selected in dropdown.");
		} else {
			System.out.println("Sent items option is not selected in dropdown");
		}
	}

	public void SendNewMessage(String MemberLogin, String Subject) throws InterruptedException {
		Thread.sleep(2000);
		this.NewButton.click();
		if (this.SendMessagePageHeader.isDisplayed()) {
			System.out.println("Send message page containing all the fields is displayed.");
		} else {
			System.out.println("Send message page containing all the fields is not displayed.");
		}

		this.MemberUsername.sendKeys(MemberLogin);
		Thread.sleep(2000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		this.SubjectTextBox.sendKeys(Subject);

		a.sendKeys(Keys.TAB).build().perform();
		a.sendKeys("loan repayment").build().perform();
		this.SubmitButton.click();
		Thread.sleep(2000);

		String ActualSuccMessage = driver.switchTo().alert().getText();
		System.out.println("Send Message success Mesasage: " + ActualSuccMessage);
		String ExpectMessage = "The message was successfully sent";
		if (ActualSuccMessage.equals(ExpectMessage)) {
			System.out.println("The message was successfully sent message is displayed.");
		} else {
			System.out.println("The message was successfully sent message is not displayed.");
		}

		driver.switchTo().alert().accept();
	}

}
