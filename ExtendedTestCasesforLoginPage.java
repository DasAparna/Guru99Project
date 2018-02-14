package com.guru99.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.guru99.GenericLib.Constants;
import com.guru99.GenericLib.WebDriverCommonLib;

public class ExtendedTestCasesforLoginPage extends WebDriverCommonLib{
	WebElement ele;
	
	public void invalidUserNameValidPass(){
	String userName = getDataFromExcel("Sheet1", 2, 2);
	String password = getDataFromExcel("Sheet1", 2, 3);
	try{
		wait(1000);
		if(driver.findElement(By.xpath(Constants.loginUserID)).isDisplayed()&&
				driver.findElement(By.xpath(Constants.loginPass)).isDisplayed()) {
		
	driver.findElement(By.xpath(Constants.loginUserID)).sendKeys(userName);
	driver.findElement(By.xpath(Constants.loginPass)).sendKeys(password);
	driver.findElement(By.xpath(Constants.loginBtn)).click();
	handleAlert(Alerts.GETTEXT);
	logReport("Alert is present");
	handleAlert(Alerts.ACCEPT);
	wait(2000);
		} 
			
		}catch(NoAlertPresentException e) {  
			logReport("No Alert Present" + e.toString());
		}
	}
	public void invalidPassValidUserName() {
		String userName = getDataFromExcel("Sheet1", 3, 2);
		String password = getDataFromExcel("Sheet1", 3, 3);
		try{
			wait(1000);
			if(driver.findElement(By.xpath(Constants.loginUserID)).isDisplayed()&&
					driver.findElement(By.xpath(Constants.loginPass)).isDisplayed()) {
			
		driver.findElement(By.xpath(Constants.loginUserID)).sendKeys(userName);
		driver.findElement(By.xpath(Constants.loginPass)).sendKeys(password);
		driver.findElement(By.xpath(Constants.loginBtn)).click();
		handleAlert(Alerts.GETTEXT);
		logReport("Alert is present");
		handleAlert(Alerts.ACCEPT);
		wait(2000);
			} 
				
			}catch(NoAlertPresentException e) {  
				logReport("No Alert Present" + e.toString());
			}
		}
	public void invalidPassInvalidUserName() {
		String userName = getDataFromExcel("Sheet1", 4, 2);
		String password = getDataFromExcel("Sheet1", 4, 3);
		try{
			wait(1000);
			if(driver.findElement(By.xpath(Constants.loginUserID)).isDisplayed()&&
					driver.findElement(By.xpath(Constants.loginPass)).isDisplayed()) {
			
		driver.findElement(By.xpath(Constants.loginUserID)).sendKeys(userName);
		driver.findElement(By.xpath(Constants.loginPass)).sendKeys(password);
		driver.findElement(By.xpath(Constants.loginBtn)).click();
		handleAlert(Alerts.GETTEXT);
		logReport("Alert is present");
		handleAlert(Alerts.ACCEPT);
		wait(2000);
			} 
				
			}catch(NoAlertPresentException e) {  
				logReport("No Alert Present" + e.toString());
			}
	}
}
