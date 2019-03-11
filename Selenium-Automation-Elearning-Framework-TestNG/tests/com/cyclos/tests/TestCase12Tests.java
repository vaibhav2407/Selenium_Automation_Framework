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

import com.cyclos.POM.TestCase11PageObjects;
import com.cyclos.POM.TestCase12PageObjects;
import com.cyclos.POM.TestCase1PageObjects;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCase12Tests {
	private WebDriver driver; 
	private String baseUrl; 
	private TestCase1PageObjects TC1;
	private TestCase11PageObjects TC11;
	private TestCase12PageObjects TC12;
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
		TC12 = new TestCase12PageObjects(driver);
		baseUrl = properties.getProperty("baseURL");
		System.out.println("URL: "+baseUrl);
		// open the browser 
		driver.get(baseUrl);
	}
	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"admin", "123456"}
		};
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(dataProvider="inputs")
	public void TC11(String Username, String Password) throws InterruptedException, IOException {
		TC1.LoginIntoApplication(Username, Password);
		TC11.ValidateMessagesLinks();
		TC11.NavigateMessagesPage();
		TC12.SendMessage();
		
		
	}
}
