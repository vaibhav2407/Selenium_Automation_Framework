package com.cyclos.POM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCase12PageObjects {
	static WebDriver driver;

	public TestCase12PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='newButton']")
	private WebElement NewButton;
	
	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	private WebElement SendMessagePageHeader;
	
	@FindBy(id = "memberUsername")
	private WebElement MemberUsername;
	
	@FindBy(id = "subjectText")
	private WebElement SubjectTextField;
	
	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement SubmitButton;
	
	public void SendMessage() throws InterruptedException, IOException {
		File file = new File(System.getProperty("user.dir")+"\\resources\\Cyclos.xlsx");
		FileInputStream input = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet("TestData");
		int rowsCount = sheet.getFirstRowNum() - sheet.getLastRowNum();
		System.out.println("Total Rows --> " + rowsCount);

		for (int i = 72; i <= 74; i++) {
			String MemberLogin = sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println("MemberLogin --> " + MemberLogin);

			String Subject = sheet.getRow(i).getCell(2).getStringCellValue();
			System.out.println("Subject --> " + Subject);

			String Body = sheet.getRow(i).getCell(3).getStringCellValue();
			System.out.println("Body --> " + Body);

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
			this.SubjectTextField.sendKeys(Subject);

			a.sendKeys(Keys.TAB).build().perform();
			a.sendKeys(Body).build().perform();

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
	
}
