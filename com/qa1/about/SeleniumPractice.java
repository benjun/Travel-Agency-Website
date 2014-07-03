package com.qa1.about;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/*
 * Practice for Selenium web driver. 
 * @Author: Minchan Jun  
 */
public class SeleniumPractice {
	
	public static void main(String [] args) throws IOException{
		Scanner s = new Scanner(System.in);
		System.out.print("Type WebDriver you want to use (FirefoxDriver, InternetExplorerDriver, ChromeDriver): ");
		String input = s.next();
		
		if(input.equals("FirefoxDriver")){
			//Start FirefoxDriver
			WebDriver driver = new FirefoxDriver();
			//Go to URL
			driver.get("http://neurology.qa1.about.com/od/Symptoms/a/Symptoms-Of-A-Stroke.htm");
			//Maximize window
			driver.manage().window().maximize();
			//Select email button
			WebElement element = driver.findElement(By.cssSelector("span.sprite.envelope"));
			element.click();
			/*
			 * Type information
			 */
			driver.findElement(By.id("email-sent-to")).sendKeys("minchan.jun@stonybrook.edu");
			driver.findElement(By.id("email-your-name")).sendKeys("Minchan Jun");
			driver.findElement(By.id("email-sent-from")).sendKeys("ben-jun@hotmail.com");
			driver.findElement(By.id("email-comment")).sendKeys("You typed successfully");
			File src = null;
			src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("c:\\Users\\미소년\\Desktop\\About\\selenium\\Screenshot.png"));
			//submit button
			driver.findElement(By.cssSelector("button.btn.btn-light.btn-wide.right-col")).click();	
		}
		else if(input.equals("InternetExplorerDriver")){
			System.setProperty("webdriver.ie.driver", "C:\\Users\\미소년\\Desktop\\About\\IEDriverServer\\IEDriverServer.exe" );  
			WebDriver driver = new InternetExplorerDriver();
			//Go to URL
			driver.get("http://neurology.qa1.about.com/od/Symptoms/a/Symptoms-Of-A-Stroke.htm");
			//Maximize window
			driver.manage().window().maximize();
			//Select email button
			WebElement element = driver.findElement(By.cssSelector("span.sprite.envelope"));
			element.click();
			/*
			 * Type information
			 */
			driver.findElement(By.id("email-sent-to")).sendKeys("minchan.jun@stonybrook.edu");
			driver.findElement(By.id("email-your-name")).sendKeys("Minchan Jun");
			driver.findElement(By.id("email-sent-from")).sendKeys("ben-jun@hotmail.com");
			driver.findElement(By.id("email-comment")).sendKeys("You typed successfully");
			File src = null;
			src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("c:\\Users\\미소년\\Desktop\\About\\selenium\\Screenshot.png"));
			//submit button
			driver.findElement(By.cssSelector("button.btn.btn-light.btn-wide.right-col")).click();				
		}
		else if(input.equals("ChromeDriver")){
			//Go to URL
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\미소년\\Desktop\\About\\chromedriver\\chromedriver.exe");

			WebDriver driver = new ChromeDriver();
			
			driver.get("http://neurology.qa1.about.com/od/Symptoms/a/Symptoms-Of-A-Stroke.htm");
			//Maximize window
			driver.manage().window().maximize();
			//Select email button
			WebElement element = driver.findElement(By.cssSelector("span.sprite.envelope"));
			element.click();
			/*
			 * Type information
			 */
			driver.findElement(By.id("email-sent-to")).sendKeys("minchan.jun@stonybrook.edu");
			driver.findElement(By.id("email-your-name")).sendKeys("Minchan Jun");
			driver.findElement(By.id("email-sent-from")).sendKeys("ben-jun@hotmail.com");
			driver.findElement(By.id("email-comment")).sendKeys("You typed successfully");
			File src = null;
			src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("c:\\Users\\미소년\\Desktop\\About\\selenium\\Screenshot.png"));
			//submit button
			driver.findElement(By.cssSelector("button.btn.btn-light.btn-wide.right-col")).click();				
		}

	}
}
