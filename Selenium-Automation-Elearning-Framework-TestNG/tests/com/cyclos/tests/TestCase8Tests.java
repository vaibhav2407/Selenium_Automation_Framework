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
import com.cyclos.POM.TestCase8PageObjects;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCase8Tests {
	private WebDriver driver; 
	private String baseUrl; 
	private TestCase6PageObjects TC6;
	private TestCase8PageObjects TC8;
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
		TC6 = new TestCase6PageObjects(driver); 
		TC8 = new TestCase8PageObjects(driver);
		baseUrl = properties.getProperty("baseURL");
		System.out.println("URL: "+baseUrl);
		// open the browser 
		driver.get(baseUrl);
	}
	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"manzoor", "manzoor","manzoor","5000","birth day gift"}
		};
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(dataProvider="inputs")
	public void TC8(String Username1, String Password1, String MemberUsername, String Amount, String Description) throws InterruptedException {
		TC6.MemberLogin(Username1, Password1);
		TC8.ValidateAccountTabSublinks();
		TC8.MemberPayment(MemberUsername, Amount, Description);
		TC8.ValidateAccountInformation();
		
	}
}
