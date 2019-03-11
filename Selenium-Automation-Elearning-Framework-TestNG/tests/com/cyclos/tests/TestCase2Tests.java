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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cyclos.POM.TestCase1PageObjects;
import com.cyclos.POM.TestCase2PageObjects;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;



public class TestCase2Tests {
	private WebDriver driver; 
	private String baseUrl; 
	private TestCase1PageObjects TC1;
	private TestCase2PageObjects TC2; 
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
			{"admin", "123456","manzoor","1000","system"}
		};
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		TC1 = new TestCase1PageObjects(driver);
		TC2 = new TestCase2PageObjects(driver); 
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
	public void TC2(String Username, String Password, String MemberLogin, String Amount, String Description) throws InterruptedException {
		TC1.LoginIntoApplication(Username, Password);
		TC2.ValidateMemberPaymentPage();
		TC2.ValidateMemberCredintials(MemberLogin);
		TC2.EnterPaymentDetails(Amount, Description);
		TC2.ValidateInsufficientBalanceMessage();
	}
}
