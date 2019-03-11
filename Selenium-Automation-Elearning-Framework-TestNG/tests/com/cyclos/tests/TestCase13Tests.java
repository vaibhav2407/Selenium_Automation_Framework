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
import com.cyclos.POM.TestCase13PageObjects;
import com.cyclos.POM.TestCase1PageObjects;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCase13Tests {
	private WebDriver driver; 
	private String baseUrl; 
	private TestCase13PageObjects TC13;
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
		TC13 = new TestCase13PageObjects(driver);
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
	
	@Test
	public void TC11() throws InterruptedException, IOException {
		TC13.UserRegistrationWithMultipleInvalidData();	
	}
}
