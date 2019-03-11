package com.cyclos.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestCase5PageObjects {

	static WebDriver driver;

	public TestCase5PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[contains(text(),'Statistical analysis')]")
	private WebElement StatisticalAnalysis;

	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	private WebElement StatisticsShowPageHeader;

	@FindBy(xpath = "//tbody//tr[1]//td[2]//input[1]")
	private WebElement KeyDevelopmentsSubmitButton;
	
	@FindBy(xpath = "//td[contains(text(),'Statistics of key developments...')]")
	private WebElement KeyDevelopmentsPageHeader;
	
	@FindBy(xpath = "//input[@id='selectAllButton']")
	private WebElement SelectAllButton;
	
	@FindBy(xpath = "//input[@id='numberOfMembersCheckBox']")
	private WebElement NumberOfMembersCheckbox;
	
	@FindBy(id = "submitButton")
	private WebElement SubmitButton;
	
	@FindBy(xpath = "//td[contains(text(),'Developments in number of members')]")
	private WebElement DevelopmentText;
	
	
	public void NavigateAndValidateStatisticsPage() throws InterruptedException {
		this.StatisticalAnalysis.click();
		Thread.sleep(3000);
		  if(this.StatisticsShowPageHeader.isDisplayed()) {
			  System.out.println("Statistics show page is displayed.");
		  }else {
			  System.out.println("Statistics show page is not displayed.");
		  }	  
	}
	
	public void NavigateAndValidateKeyDevPage() {
		this.KeyDevelopmentsSubmitButton.click();
		if(this.KeyDevelopmentsPageHeader.isDisplayed()) {
			  System.out.println("Statistics of activities… page is displayed.");
		  }else {
			  System.out.println("Statistics of activities… page is not displayed.");
		  }
	}
	
	public void SelectAllCheckboxes() throws InterruptedException {
		this.SelectAllButton.click();
		  Thread.sleep(3000);
		  if(this.NumberOfMembersCheckbox.isSelected()) {
			  System.out.println("All checkboxes are selected.");
		  }else {
			  System.out.println("All checkboxes are not selected.");
		  }
	}
	
	public void validateStatisticalReportGeneration() throws InterruptedException {
		this.SubmitButton.click();
		  Thread.sleep(3000);
		  if(this.DevelopmentText.isDisplayed()) {
			  System.out.println("Statistics report generated successfully.");
		  }else {
			  System.out.println("Statistics report not generated.");
		  }
	}
	
}
