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
import com.cyclos.POM.TestCase4PageObjects;
import com.cyclos.POM.TestCase5PageObjects;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCase5Tests {
	private WebDriver driver; 
	private String baseUrl; 
	private TestCase1PageObjects TC1;
	private TestCase4PageObjects TC4;
	private TestCase5PageObjects TC5;
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
			{"admin", "123456"}
		};
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		TC1 = new TestCase1PageObjects(driver); 
		TC4 = new TestCase4PageObjects(driver);
		TC5 = new TestCase5PageObjects(driver); 
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
	public void TC5(String Username, String Password) throws InterruptedException {
		TC1.LoginIntoApplication(Username, Password);
		TC4.ValidateReportsSubLinks();
		TC5.NavigateAndValidateStatisticsPage();
		TC5.NavigateAndValidateKeyDevPage();
		TC5.SelectAllCheckboxes();
		TC5.validateStatisticalReportGeneration();
	}
}
