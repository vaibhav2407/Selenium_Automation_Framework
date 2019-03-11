package com.cyclos.POM;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TestCase3PageObjects {
	static WebDriver driver;

	public TestCase3PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='menuText'][contains(text(),'Messages')]")
	private WebElement MessagesTab;

	@FindBy(xpath = "//span[@class='subMenuText'][contains(text(),'Messages')]")
	private WebElement MessagesSubMenu;

	@FindBy(xpath = "//span[contains(text(),'Message Categories')]")
	private WebElement MessagesCategories;

	@FindBy(xpath = "//span[contains(text(),'SMS mailings')]")
	private WebElement SMSMailing;

	@FindBy(xpath = "//td[contains(text(),'Message list')]")
	private WebElement MessagesList;

	@FindBy(id = "messageBoxSelect")
	private WebElement MessageBoxDropdown;

	@FindBy(id = "newButton")
	private WebElement SendNewMessageSubmitBtn;

	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	private WebElement SendNewMsgHeader;

	@FindBy(id = "memberUsername")
	private WebElement MemberUsername;

	@FindBy(id = "memberName")
	private WebElement ActualMemberName;

	@FindBy(id = "subjectText")
	private WebElement SubjectTextField;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement SubmitButton;

	@FindBy(xpath = "//tbody//tbody//tr[2]//td[4]//a[1]")
	private WebElement MessageSentTo;

	public void ValidateMessagesLinks() {
		this.MessagesTab.click();
		Assert.assertTrue(this.MessagesSubMenu.isDisplayed());
		Assert.assertTrue(this.MessagesCategories.isDisplayed());
		Assert.assertTrue(this.SMSMailing.isDisplayed());

	}

	public void ValidateMessagesListPage() throws InterruptedException, AWTException {
		this.MessagesSubMenu.click();
		if (this.MessagesList.isDisplayed()) {
			System.out.println("Messages List section is displayed.");
		} else {
			System.out.println("Messages List Section is not displayed.");
		}

		Select s = new Select(this.MessageBoxDropdown);
		s.selectByVisibleText("Sent items");

	}

	public void SendNewMessage(String MemberUsername, String Subject, String Description)
			throws InterruptedException, AWTException {
		this.SendNewMessageSubmitBtn.click();
		if (this.SendNewMsgHeader.isDisplayed()) {
			System.out.println("Send Messages section displayed.");
		} else {
			System.out.println("Send Messages section is not displayed");
		}
		this.MemberUsername.sendKeys(MemberUsername);
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();

		String ExpectedMemName = "manzoor mehadi";
		String ActualMemName = this.ActualMemberName.getAttribute("value");
		System.out.println("Member Name -->" + ActualMemName);
		Assert.assertEquals(ActualMemName, ExpectedMemName);
		Thread.sleep(1000);
		this.SubjectTextField.sendKeys(Subject);
		a.sendKeys(Keys.TAB).build().perform();

		StringSelection message = new StringSelection(Description);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(message, null);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		this.SubmitButton.click();
		String SuccessMessage = driver.switchTo().alert().getText();
		System.out.println("Success Message: " + SuccessMessage);
		Thread.sleep(2000);
		String ExpectedMessage = "The message was successfully sent";
		if (SuccessMessage.equals(ExpectedMessage)) {
			System.out.println("Message sent successfully.");
		} else {
			System.out.println("Message not sent.");
		}
		driver.switchTo().alert().accept();
		
		String ExpMessage = "manzoor";
		Thread.sleep(2000);
		String MessageSentTo = this.MessageSentTo.getText();
		System.out.println("Message Sent To -->" + MessageSentTo);

		if (MessageSentTo.equals(ExpMessage)) {
			System.out.println("Sent Message is displayed in search results page.");
		} else {
			System.out.println("Sent Message is not displayed in search results page.");
		}
	}

}
