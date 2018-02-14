package com.guru99.PageObjects;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.guru99.GenericLib.Constants;
import com.guru99.GenericLib.WebDriverCommonLib;

public class Guru99NewUsersPage extends WebDriverCommonLib{
	WebElement ele;
	
	public void newUsersRegd() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		String emailID= getDataFromExcel("Sheet1", 1, 2);
		System.out.println("driver=" + driver);
		
		driver.findElement(By.xpath(Constants.enterNewEmailID)).sendKeys(emailID);
		System.out.println("Email ID field is present");
		wait(1000);
	//	enterText(Locators.XPATH, Constants.enterNewEmailID, emailID, "adas138@gmail.com");
		driver.findElement(By.xpath(Constants.submitBtn)).click();
		//click(Locators.XPATH, Constants.submitBtn, "Clicked");
		wait(2000);
		System.out.println(driver.findElement(By.xpath(Constants.userID)).getText());
		System.out.println(driver.findElement(By.xpath(Constants.passWord)).getText());
		
	
	} 
   
   
	
	/*
	 * @Author Aparna
	 * @validation Rules : Error Msg Validation
	 * @param : 
	 * @return type : void
	 */
	public void errorMSGValidation() {
		try {
			if (driver.findElement(By.xpath(Constants.clickHereLink)).isDisplayed()){ 
				System.out.println("Link is present");
				wait(2000);
				driver.findElement(By.xpath(Constants.clickHereLink)).click();
			//	click(Locators.XPATH, Constants.clickHereLink, "Click Here to Register");
				wait(1000);
				
			} else {
				System.out.println("Link is not available");
			}
			wait(1000);
			driver.findElement(By.xpath(Constants.submitBtn)).click();
			String validationmsg = "Email ID must not be blank";
			String actualMsg = driver.findElement(By.xpath(Constants.validationtext)).getText().toString();
			
			if(validationmsg.contentEquals(actualMsg)) {
				logReport(actualMsg +" is Printed");
			}
			
		}catch (ElementNotFoundException e){
			logReport("No Validation Msg found" + e.toString());
			
		}
		wait(1000);
	}
}
