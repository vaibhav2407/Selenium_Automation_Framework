package com.cyclos.POM;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestCase4PageObjects {
	static WebDriver driver;

	public TestCase4PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	public WebElement ReportsTab;

	@FindBy(xpath = "//span[contains(text(),'State overview')]")
	public WebElement StateOverviewSubLink;

	@FindBy(xpath = "//span[contains(text(),'Member lists')]")
	public WebElement MemberListSubLink;

	@FindBy(xpath = "//span[contains(text(),'Member reports')]")
	public WebElement MemberReoprtsSubLink;

	@FindBy(xpath = "//span[contains(text(),'Statistical analysis')]")
	public WebElement StatisticalAnalysys;

	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	public WebElement MemberListPageHeader;

	@FindBy(id = "selectAllButton")
	public WebElement SelectAllButton;

	@FindBy(id = "memberName")
	public WebElement MemberNameCheckbox;

	@FindBy(id = "printReportButton")
	public WebElement PrintReportButton;

	@FindBy(xpath = "//td[@class='printTitle']")
	public WebElement PrintTitle;

	public void ValidateReportsSubLinks() {
		this.ReportsTab.click();
		Assert.assertTrue(this.StateOverviewSubLink.isDisplayed());
		Assert.assertTrue(this.MemberListSubLink.isDisplayed());
		Assert.assertTrue(this.MemberReoprtsSubLink.isDisplayed());
		Assert.assertTrue(this.StatisticalAnalysys.isDisplayed());
	}

	public void NavigateAndValidateMemberListPage() {
		this.MemberListSubLink.click();
		if (this.MemberListPageHeader.isDisplayed()) {
			System.out.println("Member List Page is displayed.");
		} else {
			System.out.println("Member List Page is not displayed.");
		}

	}

	public void ClickSelectAllCheckboxes() throws InterruptedException {
		this.SelectAllButton.click();
		Thread.sleep(3000);
		if (this.MemberNameCheckbox.isSelected()) {
			System.out.println("All Checkboxes are selected.");
		} else {
			System.out.println("Checkboxes are not selected.");
		}
	}

	public void NavigateAndValidatePrintReport() throws InterruptedException {
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
			if (this.PrintTitle.isDisplayed()) {
				System.out.println("Member List reports generated successfully.");
			} else {
				System.out.println("Member List reports not generated.");
			}
			driver.close();
			driver.switchTo().window(parent);
		}
	}
}
