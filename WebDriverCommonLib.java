package com.guru99.GenericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebDriverCommonLib {
	
	public static WebDriver driver;
	WebDriverWait wait = null;
	public static final String PATTERN = ":";
    public static final String FIRST_PATTERN = "mngr";
    public static final String SECOND_PATTERN = "[0-9]+";

	
	public enum Locators {
		ID,NAME,XPATH,CSSSELECTOR;
	}
	public void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
		
	}
	
	/*
	 * @Authors: Aparna
	 * @Description : Get Values from Property file
	 * @Param : String
	 * @returnType : String 
	 */
	
	public String getValueFromGlobal(String data) {
		String value = null;
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream("D:\\MyWorkspace\\MyWorkspace\\Guru99EndToEnd\\src\\com\\guru99\\GenericLib\\Guru99Data.Properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		}catch(IOException e) {
			e.printStackTrace();
		}
		value = prop.getProperty(data);
		logReport("Data Fetched From File as :" + value);
		return value;
	}
	/*
	 * @Authors: Aparna
	 * @Description : Get Browser
	 * @Param : String
	 * @returnType : WebDriver 
	 */
	
	
	public WebDriver getBrowser(String browser) {
		switch (browser) {
		case "Chrome" :
			System.setProperty("webdriver.chrome.driver", getValueFromGlobal("CHROME_DRIVER_PATH"));
			driver = new ChromeDriver();
			break ;
		case "FireFox" :
			System.setProperty("webdriver.gecko.driver", getValueFromGlobal("FIREFOX_DRIVER_PATH"));
			driver = new FirefoxDriver();
			break ;
		case "IE" :
			System.setProperty("webdriver.ie.driver", getValueFromGlobal("IE_DRIVER_PATH"));
			driver = new InternetExplorerDriver();
			break ;
		default :
			driver = new ChromeDriver();
		}
		logReport(browser +"Browser launched successfully");
		return driver;
		
	}
	/*
	 * @Authors: Aparna
	 * @Description : 
	 * @Param : String
	 * @returnType : BY 
	 */
	public By by(Locators locator,String Control){
		By by= null;
		switch(Control) {
		case "XPATH" :
			by = By.xpath(Control);
			break;
		case"ID" :
			by= By.id(Control);
			break;
		case "CSSSELECTOR":
			by = By.cssSelector(Control);
			break;
		}
		
		return by;
	}
	/*
	 * @Authors: Aparna
	 * @Description : To Click
	 * @Param : String
	 * @returnType : void 
	 */
	public void click (Locators locator, String Control, String objName) {
		
		driver.findElement(by(locator,Control)).click();
		wait(2000);
		logReport("Clicked on " + objName);
	}
	
	/*
	 * @Authors: Aparna
	 * @Description : To send Keys
	 * @Param : String
	 * @returnType : void 
	 */
	public void enterText(Locators locator, String Control, String text , String ObjName) {
		driver.findElement(by(locator,Control)).sendKeys(text);
		logReport("Entered text in " + ObjName + "as " + text);
		
	}
	/*
	 * @Authors: Aparna
	 * @Description : To check if element is displayed
	 * @Param : String
	 * @returnType : Boolean 
	 */
	public boolean isDisplayed(Locators locator , String Control){
	boolean flag = false;
	flag = driver.findElement(by(locator,Control)).isDisplayed();
	return flag;
	}
	
	/*
	 * @Authors: Aparna
	 * @Description : To launch URL
	 * @Param : String
	 * @returnType : void 
	 */
	
	public void launchURL(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		logReport("URL launched as" + url);
		driver.manage().window().maximize();
		logReport("Window maximized Successfully");
	
	}
	
	public void waitForObject(Locators locator, String value) {
		wait = new WebDriverWait(driver , 60);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by(locator,value)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by(locator,value)));
		} catch(Exception e) {
			logReport("Element is not present in UI, Object property might have changed");
		}
	}
	
	public void closeTest() {
		driver.quit();
		logReport("Driver is quit");
	}
	
	public void moveToElement(Locators locator, String Control,String ObjName) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(by(locator, Control))).perform();
		logReport("Move to element" + ObjName +" successfully");
		wait(2000);
	}
	
	public enum Alerts {
		ACCEPT, DISMISS,GETTEXT;
	}
	
	public void handleAlert(Alerts action) {
		try {
			switch(action) {
			case ACCEPT:
				driver.switchTo().alert().accept();
				break;
			case DISMISS:
				driver.switchTo().alert().dismiss();
				break;
			case GETTEXT:
				driver.switchTo().alert().getText();
				break;
			
		}
	} catch (NoAlertPresentException e) {
		logReport("No Alert Present");
	}
}
	/*
	 * @Authors: Aparna
	 * @Description : To Fetch Data from Excel Sheet
	 * @Param : String
	 * @returnType : String 
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int colNum) {
		String result = "";
		try {
			String location = getValueFromGlobal("GETTESTDATA_PATH");
			FileInputStream fis = new FileInputStream(location);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row rw = sh.getRow(rowNum);
			result = rw.getCell(colNum).getStringCellValue();
		}catch(Exception e) {
			logReport("Unable to fetch the data from excel sheet. reason is " + e.toString());
		}
		
		return result;
		
	}
	
	public void logReport(String msg) {
		// TODO Auto-generated method stub
		Reporter.log(msg, true);
	}
	
	@SuppressWarnings("deprecation")
	 public String setDataToExcelSheet(String sheetname,int rowno,int colno,String data) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		String location =  getValueFromGlobal("GETTESTDATA_PATH");	
		FileInputStream fis = new FileInputStream(location);	
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetname);
		Row row = sheet.getRow(rowno);
		Cell cell = row.getCell(colno);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		FileOutputStream fos = new FileOutputStream(location);
		cell.setCellValue(data);
		wb.write(fos);
		fos.close();
		fis.close();
		wb.close();
		return data;
	       }
	
	
	
}
