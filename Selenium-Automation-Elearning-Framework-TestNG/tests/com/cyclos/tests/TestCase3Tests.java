package com.cyclos.tests;

import java.awt.AWTException;
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
import com.cyclos.POM.TestCase2PageObjects;
import com.cyclos.POM.TestCase3PageObjects;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCase3Tests {
	private WebDriver driver; 
	private String baseUrl; 
	private TestCase1PageObjects TC1;
	private TestCase3PageObjects TC3;
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
			{"admin", "123456","manzoor","loan repayment","loan repayment"}
		};
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		TC1 = new TestCase1PageObjects(driver); 
		TC3 = new TestCase3PageObjects(driver); 
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
	public void TC3(String Username, String Password, String MemberLogin,String Subject, String Description) throws InterruptedException, AWTException{
		TC1.LoginIntoApplication(Username, Password);
		TC3.ValidateMessagesLinks();
		TC3.ValidateMessagesListPage();
		TC3.SendNewMessage(MemberLogin, Subject, Description);
	}
}
