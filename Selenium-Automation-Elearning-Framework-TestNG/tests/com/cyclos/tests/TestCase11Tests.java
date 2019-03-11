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

import com.cyclos.POM.TestCase10PageObjects;
import com.cyclos.POM.TestCase11PageObjects;
import com.cyclos.POM.TestCase1PageObjects;
import com.cyclos.POM.TestCase6PageObjects;
import com.cyclos.POM.TestCase9PageObjects;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCase11Tests {
	private WebDriver driver; 
	private String baseUrl; 
	private TestCase1PageObjects TC1;
	private TestCase11PageObjects TC11;
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
		TC11 = new TestCase11PageObjects(driver);
		baseUrl = properties.getProperty("baseURL");
		System.out.println("URL: "+baseUrl);
		// open the browser 
		driver.get(baseUrl);
	}
	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"admin", "123456","manzoor","loan repayment"}
		};
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(dataProvider="inputs")
	public void TC11(String Username, String Password, String MemberLogin, String Subject) throws InterruptedException {
		TC1.LoginIntoApplication(Username, Password);
		TC11.ValidateMessagesLinks();
		TC11.NavigateMessagesPage();
		TC11.SendNewMessage(MemberLogin, Subject);
		
		
	}
}
