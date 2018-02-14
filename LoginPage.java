package com.guru99.PageObjects;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.guru99.GenericLib.Constants;
import com.guru99.GenericLib.WebDriverCommonLib;

public class LoginPage extends WebDriverCommonLib{
	WebElement ele;
	
	public void verifyHomePage() throws IOException {
		try {
		driver.navigate().back();
		wait(1000);
		driver.navigate().back();
		wait(1000);
		if(driver.findElement(By.xpath(Constants.loginUserID)).isDisplayed() &&
			driver.findElement(By.xpath(Constants.loginPass)).isDisplayed()&&
				driver.findElement(By.xpath(Constants.loginBtn)).isDisplayed()) {
					driver.findElement(By.xpath(Constants.loginUserID)).sendKeys(getValueFromGlobal("USERNAME"));
					driver.findElement(By.xpath(Constants.loginPass)).sendKeys(getValueFromGlobal("PASSWORD"));
				wait(1000);
				driver.findElement(By.xpath(Constants.loginBtn)).click();
				wait(1000);
		logReport("Loggedin successfully" + "Status PASSED");
		} else {
			logReport("Element is not present" + "Status FAILED");
		}
		String marqueeHeading = "Welcome To Manager's Page of Guru99 Bank";
			Assert.assertEquals(marqueeHeading, Constants.marqueehHeading);
		logReport("Loggedin successfully" + " Status PASSED");
		
		//Take Screenshot
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File("D:\\MyWorkspace\\MyWorkspace\\Guru99EndToEnd\\Screenshot\\screenshot.png"));
	
		}
		catch(NullPointerException e){
			e.printStackTrace();
			logReport("Page is not displayed" + " Status FAILED");
			System.out.println("GIT");
		}
	}
	

	@DataProvider(name = "newCust")
	public void enterDetailsforNewCust() throws FileNotFoundException {
		
		String custName = getDataFromExcel("Sheet1", 5, 4);
		String dob = getDataFromExcel("Sheet1", 5, 5);
		String address = getDataFromExcel("Sheet1", 5, 6);
		String city = getDataFromExcel("Sheet1", 5, 7);
		String state = getDataFromExcel("Sheet1", 5, 8);
		String pin = getDataFromExcel("Sheet1", 5, 9);
		String mobile = getDataFromExcel("Sheet1", 5, 10);
		String email = getDataFromExcel("Sheet1", 5, 11);
		String pass = getDataFromExcel("Sheet1", 5, 12);
		
		driver.findElement(By.xpath(Constants.newCustLink)).click();
		if(driver.findElement(By.xpath(Constants.newCustLink)).isDisplayed() &&
				driver.findElement(By.xpath(Constants.dateofBirth)).isDisplayed() &&
				driver.findElement(By.xpath(Constants.addressarea)).isDisplayed() &&
				driver.findElement(By.xpath(Constants.entercity)).isDisplayed() &&
				driver.findElement(By.xpath(Constants.enterState)).isDisplayed() &&
				driver.findElement(By.xpath(Constants.enterPin)).isDisplayed() &&
				driver.findElement(By.xpath(Constants.mobielNum)).isDisplayed() &&
				driver.findElement(By.xpath(Constants.enteremail)).isDisplayed() &&
				driver.findElement(By.xpath(Constants.enterPass)).isDisplayed()){
			
			logReport("Elements are displayed");
			
			driver.findElement(By.xpath(Constants.inputName)).sendKeys(custName);
			//enterText(Locators.XPATH, Constants.newCustLink, custName, "aparna");
			driver.findElement(By.xpath(Constants.radiobtn)).click();
			driver.findElement(By.xpath(Constants.dateofBirth)).sendKeys(dob);
			driver.findElement(By.xpath(Constants.addressarea)).sendKeys(address);
			driver.findElement(By.xpath(Constants.entercity)).sendKeys(city);
			driver.findElement(By.xpath(Constants.enterState)).sendKeys(state);
			driver.findElement(By.xpath(Constants.enterPin)).sendKeys(pin);
			System.out.println(pin);
			driver.findElement(By.xpath(Constants.mobielNum)).sendKeys(mobile);
			driver.findElement(By.xpath(Constants.enteremail)).sendKeys(email);
			driver.findElement(By.xpath(Constants.enterPass)).sendKeys(pass);
			driver.findElement(By.xpath(Constants.submit)).click();
			logReport("Successfully customer created");
			wait(1000);
			
			
		} else {
			logReport("Elements are not found");
		}
	}
	
	public void editCustomer() throws IOException {
		if(driver.findElement(By.xpath(Constants.custID)).isDisplayed()) {
			String custID = driver.findElement(By.xpath(Constants.custID)).getText();
			logReport("Customer ID is " + custID);
			driver.findElement(By.xpath(Constants.continueLink));
			wait(1000);
			//Totake Screenshot
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			wait(1000);
			// Code to save screenshot at desired location
			FileUtils.copyFile(scrFile, new File("D:\\MyWorkspace\\MyWorkspace\\Guru99EndToEnd\\Screenshot\\screenshot.png"));
			wait(1000);
			System.out.println("Screenshot taken");
			
		
		driver.findElement(By.xpath(Constants.editLink)).click();
		wait(1000);
		driver.findElement(By.xpath(Constants.entercustID)).sendKeys(custID);
		driver.findElement(By.xpath(Constants.editpagesubmit)).click();
		} else {
			logReport("Element Not found");
		}
	}
}
