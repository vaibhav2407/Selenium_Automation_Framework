package com.cyclos.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cyclos.POM.TestCase1PageObjects;
import com.cyclos.POM.TestCase6PageObjects;
import com.cyclos.POM.TestCase7PageObjects;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCase7Tests {
	private WebDriver driver; 
	private String baseUrl; 
	private TestCase1PageObjects TC1;
	private TestCase6PageObjects TC6;
	private TestCase7PageObjects TC7;
	private static Properties properties; 

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		TC1 = new TestCase1PageObjects(driver); 
		TC6 = new TestCase6PageObjects(driver); 
		TC7 = new TestCase7PageObjects(driver);
		baseUrl = properties.getProperty("baseURL");
		System.out.println("URL: "+baseUrl);
		// open the browser 
		driver.get(baseUrl);
	}
	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"manzoor", "manzoor","500","admin","123456","manzoor"}
		};
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(dataProvider="inputs")
	public void TC7(String Username1, String Password1, String Amount, String Username, String Password, String MemberLogin) throws InterruptedException {
		TC6.MemberLogin(Username1, Password1);
		TC7.ValidateAccountSublinks();
		TC7.NavigateViewLoansPage();
		TC7.NavigateAndValidateLoanDetailsPage();
		TC7.ValidateTotalAmountAndRepay(Amount);
		TC6.LogoutUser();
		TC1.LoginIntoApplication(Username, Password);
		TC6.NavigateMemberSection(MemberLogin);
		TC7.ValidateLoanDetailsWithPendingAmount();
	}
}
