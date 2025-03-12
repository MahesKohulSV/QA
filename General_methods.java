package module;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class General_methods {
	public static WebDriver driver;
	public static void setdriver(WebDriver driver) {
		General_methods.driver=driver;
	}
	/**
	 * This method is used for click on the element
	 * @param xpath
	 * @author siva
	 */
	public static void clickONButton(String xpath)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			element.click();
			System.out.println("clicked element");
		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error occured while clicking the element: "+e.getMessage());
		}
	}
	/**
	 * This method is used for page verification
	 * @param xpath, value
	 * @author siva
	 */
	public static void pageVerification(String xpath, String value)
	{
		try
		{
			WebElement verifytheElement = General_methods.driver.findElement(By.xpath(xpath));
			String getText=verifytheElement.getText();
		
			if(getText.contains(value))
			{
				System.out.println(value+" page verified succesfully");
			}
			else
			{
				System.out.println(value+" Verification failed");
			}
		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error occured while during the verification: "+e.getMessage());
		}
	}
	/**
	 * This method is used for send text to the text field
	 * @param xpath, name
	 * @author siva
	 */
	public static void generalSendKeys(String xpath, String name)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			element.sendKeys(name);

			System.out.println("Entered text is "+name);
		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error occured while clicking the element: "+e.getMessage());
		}
	}
	/**
	 * This method is used for get the customer id from popup message
	 * @param alertText
	 * @author siva
	 */

	public static String extractCustomerId(String alertText) {

		String temp = null;
		if (alertText != null) {
			String start[] = alertText.split(":");
			temp = start[1];
		}
		return temp;
	}
	/**
	 * This method is used for select the text from dropdown by selectbyvisibletext
	 * @param xpath, text
	 * @author siva
	 */
	public static void selectByVisibleText(String xpath, String text)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			Select s=new Select(element);
			s.selectByVisibleText(text);
		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error occured while clicking the element: "+e.getMessage());
		}
	}
	/**
	 * This method is used for select the text from dropdown by selectbyvalue
	 * @param xpath, text
	 * @author siva
	 */
	public static void selectByValue(String xpath, String text)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			Select s=new Select(element);
			s.deselectByValue(text);
		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error occured while clicking the element: "+e.getMessage());
		}
	}
	/**
	 * This method is used for handle alert and get the text
	 * @param acceptAlert
	 * @author siva
	 */
	public static String handleAlertAndGetText(boolean acceptAlert)
	{
		String alertText=null;
		try
		{
			WebDriverWait wait=new WebDriverWait(General_methods.driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String alerttext = alert.getText();
			if(acceptAlert)
			{
				alert.accept();
				System.out.println("Alert accepted succesfully");
			}
			else
			{
				alert.dismiss();
				System.out.println("alert dismissed succesfully");
			}

		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error occured while clicking the element: "+e.getMessage());
		}
		return alertText;

	}
	/**
	 * This method is used for extract the account no from popup message
	 * @param alertText
	 * @author siva
	 */
	public static String extractAccountNo(String alertText) {

		String temp = null;
		if (alertText != null) {
			String start[] = alertText.split(":");
			temp = start[1];

		}
		return temp;
	}
	/**
	 * This method is used for table verification
	 * @param table xpath, expectedcolumns, expectedData
	 * @author siva
	 */
	public static boolean TdVerification(String tableXPath, String[] expectedColumns, String... expectedData) {
		try {

			WebElement tableElement = driver.findElement(By.xpath(tableXPath));
			List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
			//System.out.println(rows.size());

			if (expectedData.length == 0) {
				System.out.println("No data provided for verification.");
				return false;
			}

			if (expectedColumns.length != expectedData.length) {
				System.out.println("Number of expected columns and data points don't match.");
				return false;
			}

			for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
				WebElement row = rows.get(rowIndex);

				List<WebElement> cells = row.findElements(By.tagName("td"));
				//System.out.println(cells.size());
//				if (cells.size() < expectedColumns.length) {
//					continue;
//				}

				//boolean rowMatch = true;
				List<String> actualValues = new ArrayList<>();

				for (int i = 0; i < expectedData.length; i++) {
					String expectedValue = expectedData[i] != null ? expectedData[i].trim() :"";
					String actualValue = cells.get(i).getText().trim();
					actualValues.add(actualValue);

					if (!actualValue.equalsIgnoreCase(expectedValue)) {
						//rowMatch = false;
						//break;
						System.out.println(actualValue + " is not match with expected value " +expectedValue);
					}else {
						System.out.println(actualValue + " is match with expected value " +expectedValue);	
					}

				}
			}
			return true;

		} catch (Exception e) {
			System.err.println("Error occurred while verifying table data: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	
	/**
	 * This method is used for get the text
	 * @param table xpath
	 * @author siva
	 */
	public static void generalGetText(String xpath)
	{
		try
		{
			WebElement verifytheElement = General_methods.driver.findElement(By.xpath(xpath));
			String getText=verifytheElement.getText();
			System.out.println(getText);
			if(getText.equals("Deposit Successful"))
			{
				System.out.println("Deposite Verified");
			}
			else
			{
				System.out.println("deposite Not Verified");
			}

		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error occured while during the verification: "+e.getMessage());
		}

	}
	/**
	 * This method is used for verification
	 * @param table xpath, creditAmount
	 * @author siva
	 */
	public static void generalVerification(String xpath, String creditAmount)
	{
		try
		{
			WebElement verifytheElement = General_methods.driver.findElement(By.xpath(xpath));
			String getText=verifytheElement.getText();

			if(getText.contains(creditAmount))
			{
				System.out.println(creditAmount+" verified succesfully");
			}
			else
			{
				System.out.println(creditAmount+" Verification failed");
			}
		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error occured while during the verification: "+e.getMessage());
		}
	}
	/**
	 * This method is used get the system time
	 * @param null
	 * @author siva
	 */

	public static String generalGetTimeandDate()
	{
		LocalDateTime currenttime=LocalDateTime.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a");

		return currenttime.format(formatter);
	}
	public static boolean withDrawVerification(String tableXPath, String[] expectedColumns, String... expectedData) {
		try {

			WebElement tableElement = driver.findElement(By.xpath(tableXPath));
			List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
			//System.out.println(rows.size());

			if (expectedData.length == 0) {
				System.out.println("No data provided for verification.");
				return false;
			}

			if (expectedColumns.length != expectedData.length) {
				System.out.println("Number of expected columns and data points don't match.");
				return false;
			}

			for (int rowIndex = 2; rowIndex < rows.size(); rowIndex++) {
				WebElement row = rows.get(rowIndex);

				List<WebElement> cells = row.findElements(By.tagName("td"));
				//System.out.println(cells.size());
//				if (cells.size() < expectedColumns.length) {
//					continue;
//				}

				//boolean rowMatch = true;
				List<String> actualValues = new ArrayList<>();

				for (int i = 0; i < expectedData.length; i++) {
					String expectedValue = expectedData[i] != null ? expectedData[i].trim() :"";
					String actualValue = cells.get(i).getText().trim();
					actualValues.add(actualValue);

					if (!actualValue.equalsIgnoreCase(expectedValue)) {
						//rowMatch = false;
						//break;
						System.out.println(actualValue + " is not match with expected value " +expectedValue);
					}else {
						System.out.println(actualValue + " is match with expected value " +expectedValue);	
					}
				}
			}
			return true;

		} catch (Exception e) {
			System.err.println("Error occurred while verifying table data: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
//Add new
}
