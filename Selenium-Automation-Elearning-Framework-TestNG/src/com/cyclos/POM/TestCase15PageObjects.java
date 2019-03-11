package com.cyclos.POM;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestCase15PageObjects {
	static WebDriver driver;

	public TestCase15PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	private WebElement ReportsTab;
	
	@FindBy(xpath = "//span[contains(text(),'State overview')]")
	private WebElement StateOverviewSubLink;
	
	@FindBy(xpath = "//span[contains(text(),'Member lists')]")
	private WebElement MemberListSubLink;
	
	@FindBy(xpath = "//span[contains(text(),'Member reports')]")
	private WebElement MemberReportsSubLink;
	
	@FindBy(xpath = "//span[contains(text(),'Statistical analysis')]")
	private WebElement StatisticalAnalysisSubLink;
	
	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	private WebElement MemberListHeader;
	
	@FindBy(xpath = "//input[@id='selectAllButton']")
	private WebElement SelectAllButton;
	
	@FindBy(id = "printReportButton")
	private WebElement PrintReportButton;
	
	@FindBy(xpath = "//td[@class='printTitle']")
	private WebElement PrintPageTitle;
	
	@FindBy(xpath = "//tr[1]//td[2]//input[1]")
	private WebElement KeyDevelopmentButton;
	
	@FindBy(xpath = "//td[contains(text(),'Statistics of key developments...')]")
	private WebElement StatisticsKeyDevelopmentHeader;
	
	@FindBy(id = "selectAllButton")
	private WebElement SelectAllButton1;
	
	@FindBy(name = "query(numberOfMembers)")
	private WebElement NumberOfMembersCheckbox;
	
	@FindBy(id = "submitButton")
	private WebElement SubmitButton;
	
	@FindBy(xpath = "//td[contains(text(),'Developments in number of members')]")
	private WebElement DevmntInNoMembers;
	
	
	public void ValidateReoprtsSublinks() {
		this.ReportsTab.click();
		Assert.assertTrue(this.StateOverviewSubLink.isDisplayed());
		Assert.assertTrue(this.MemberListSubLink.isDisplayed());
		Assert.assertTrue(this.MemberReportsSubLink.isDisplayed());
		Assert.assertTrue(this.StatisticalAnalysisSubLink.isDisplayed());
		this.MemberListSubLink.click();

		if (this.MemberListHeader.isDisplayed()) {
			System.out.println("User navigated to members list page");
		} else {
			System.out.println("User not navigated to members list page");
		}
	}
	
	public void GenerateMemberListStaticalReports() throws InterruptedException {
		this.SelectAllButton.click();
		this.PrintReportButton.click();
		Thread.sleep(3000);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// iterate through your windows
		while (it.hasNext()) {
			String parent = it.next();
			String newwin = it.next();
			driver.switchTo().window(newwin);
			// perform actions on new window
			driver.manage().window().maximize();
			Thread.sleep(3000);
			if (this.PrintPageTitle.isDisplayed()) {
				System.out.println("Member List reports generated successfully.");
			} else {
				System.out.println("Member List reports not generated.");
			}

			driver.close();
			driver.switchTo().window(parent);
			this.StatisticalAnalysisSubLink.click();
			this.KeyDevelopmentButton.click();
			if (this.StatisticsKeyDevelopmentHeader
					.isDisplayed()) {
				System.out.println("User navigated to Statistics of activities… page.");
			} else {
				System.out.println("User not navigated to Statistics of activities… page.");
			}

			this.SelectAllButton1.click();
			if (this.NumberOfMembersCheckbox.isSelected()) {
				System.out.println("All check boxes are selected.");
			} else {
				System.out.println("All checkboxes are not selected.");
			}

			this.SubmitButton.click();
			if (this.DevmntInNoMembers.isDisplayed()) {
				System.out.println("Statistics Report is generated.");
			}else {
				System.out.println("Statistics Report is not generated.");
			}
		}
	}
	
}
