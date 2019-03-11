package com.cyclos.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cyclos.POM.TestCase1PageObjects;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;




public class TestCase1Tests {

	private WebDriver driver; 
	private String baseUrl; 
	private TestCase1PageObjects TC1; 
	private static Properties properties; 

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	
	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"admin", "123456","manzoor"}
		};
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		TC1 = new TestCase1PageObjects(driver); 
		baseUrl = properties.getProperty("baseURL");
		System.out.println("URL: "+baseUrl);
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(dataProvider="inputs")
	public void TC1(String Username, String Password, String MemberLogin) throws InterruptedException {

		TC1.LoginIntoApplication(Username, Password);
		TC1.ClickAndValidateAccountTab();
		TC1.ValidateManageCurrensyLink();
		TC1.ValidateManageAccountsLink();
		TC1.ValidateSystemAccountsLink();
		TC1.ValidateManageLoansLink();
		TC1.ValidateManageLoansPage();
		TC1.ValidateMemberLogin(MemberLogin);
		TC1.ValidateSearchResults();
		TC1.ValidateLoanDetailsPage();
	}

	
	 
}
