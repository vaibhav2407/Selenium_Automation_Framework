package com.cyclos.POM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TestCase14PageObjects {
	static WebDriver driver;

	public TestCase14PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='loginRegistrationDiv']//input[@value='Submit']")
	private WebElement SubmitButton;

	@FindBy(name = "member(user).username")
	private WebElement memberUserName;

	@FindBy(name = "member(name)")
	private WebElement MemberName;

	@FindBy(name = "member(email)")
	private WebElement Email;

	@FindBy(xpath = "//tr[4]//td[2]//input[3]")
	private WebElement BirthDay;

	@FindBy(xpath = "//tr[6]//td[2]//input[3]")
	private WebElement Address;

	@FindBy(xpath = "//tr[7]//td[2]//input[3]")
	private WebElement PostalCode;

	@FindBy(xpath = "//tr[8]//td[2]//input[3]")
	private WebElement City;

	@FindBy(xpath = "//select[@id='custom_field_select_6']")
	private WebElement Area;

	@FindBy(xpath = "//tr[10]//td[2]//input[3]")
	private WebElement Phone;

	@FindBy(xpath = "//tr[11]//td[2]//input[3]")
	private WebElement MobileNumber;

	@FindBy(name = "member(user).password")
	private WebElement Password;

	@FindBy(name = "confirmPassword")
	private WebElement ConfirmPassword;

	@FindBy(id = "saveButton")
	private WebElement SaveButton;

	@FindBy(id = "_radio_2_1")
	private WebElement Male;

	@FindBy(id = "_radio_2_0")
	private WebElement Female;

	@FindBy(xpath = "//body[@class='main']//tr//tr//tr[1]//td[1]")
	private WebElement ActualSuccessMessage;
	
	@FindBy(xpath = "//input[@id='btn']")
	private WebElement OKButton;
	
	public void UserRegistrationWithMultipleValidData() throws IOException, InterruptedException {
		File file = new File(System.getProperty("user.dir") + "\\resources\\Cyclos.xlsx");
		FileInputStream input = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet("TestData");
		int rowsCount = sheet.getFirstRowNum() - sheet.getLastRowNum();
		System.out.println("Total Rows --> " + rowsCount);
		this.SubmitButton.click();
		for (int i = 77; i <= 79; i++) {
			Row row = sheet.getRow(i);
			DataFormatter formatter = new DataFormatter();

			String LoginName = formatter.formatCellValue(row.getCell(1));
			String FullName = row.getCell(2).getStringCellValue();
			String email = row.getCell(3).getStringCellValue();
			String Birthday = row.getCell(4).getStringCellValue();
			String Gender = row.getCell(5).getStringCellValue();
			String Address = row.getCell(6).getStringCellValue();

			String PostalCodeS = formatter.formatCellValue(row.getCell(7));

			String City = row.getCell(8).getStringCellValue();
			String Phone = formatter.formatCellValue(row.getCell(9));
			String MobileNo = formatter.formatCellValue(row.getCell(10));
			String Password = row.getCell(13).getStringCellValue();
			String ConfirmPassword = row.getCell(14).getStringCellValue();

			this.memberUserName.clear();
			this.memberUserName.sendKeys(LoginName);
			this.MemberName.clear();
			this.MemberName.sendKeys(FullName);
			this.Email.clear();
			this.Email.sendKeys(email);
			this.BirthDay.clear();
			this.BirthDay.sendKeys(Birthday);

			if (Gender.equals("Male")) {
				this.Male.click();
			} else {
				this.Female.click();
			}
			this.Address.clear();
			this.Address.sendKeys(Address);
			this.PostalCode.clear();
			this.PostalCode.sendKeys(PostalCodeS);
			this.City.clear();
			this.City.sendKeys(City);
			Select s = new Select(this.Area);
			s.selectByVisibleText("Example area");
			this.Phone.clear();
			this.Phone.sendKeys(Phone);
			this.MobileNumber.clear();
			this.MobileNumber.sendKeys(MobileNo);
			this.Password.clear();
			this.Password.sendKeys(Password);
			this.ConfirmPassword.clear();
			this.ConfirmPassword.sendKeys(ConfirmPassword);
			Thread.sleep(15000);
			this.SaveButton.click();
			Thread.sleep(3000);
			String ExpectedSuccMessage = "Thanks for registering!";
			String ActualSuccessMessage = null;
			try {
				ActualSuccessMessage = driver.switchTo().alert().getText();
				System.out.println("Actual Messages: "+ActualSuccessMessage);
				driver.switchTo().alert().accept();
			}catch(Exception e) {
				System.out.println("No alert is prasent.");
			}
			
			try {
				ActualSuccessMessage = this.ActualSuccessMessage.getText();
				System.out.println("Actual Messages: "+ActualSuccessMessage);
				this.OKButton.click();
			}catch(Exception e) {
				System.out.println("Success Message is not displayed.");
			}
			
			if (ExpectedSuccMessage.contains(ActualSuccessMessage)) {
				System.out.println("New User Created Successfully.");
			} else {
				System.out.println("New User Creation failed.");
			}
			System.out.println("==========================================================");
			
			
			
			}
	}
}
