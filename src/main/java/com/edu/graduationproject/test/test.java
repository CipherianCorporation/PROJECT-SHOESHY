package com.edu.graduationproject.test;

import static org.testng.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.edu.graduationproject.utils.ExcelUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {
	 

//	
//	 
//	  
//	  WebElement email=dri.findElement(By.name("username"));
//	  WebElement pass=dri.findElement(By.name("password"));
//	  WebElement login=dri.findElement(By.name("login"));
//	  
//	  email.sendKeys("thuan");
//	  pass.sendKeys("thuanle");
//	  login.click();
 
		
		public WebDriver driver;
		Map<String, Object[]> res;
		int index;

		@BeforeClass
		public void initiateStep() {
			res = new LinkedHashMap<String, Object[]>();
			index = 0;
			res.put("" + index, new Object[] { "Test ID", "Action", "Input data", "Expected", "Actual", "Result" });
		}

//		@AfterClass
//		public void finalizeStep() {
//			res.entrySet().forEach(entry -> {
//				System.out.println(entry.getKey() + " : " + Arrays.toString(entry.getValue()));
//			});
//			try {
//				ExcelUtil.exportTestResultExcel(Paths.get("src","main","resources", "LoginData.xlsx").toFile(), res);
//			} catch (Exception e) {
//
//				e.printStackTrace();
//			}
//		}

		@SuppressWarnings("deprecation")
		@BeforeMethod
		public void launchBrowser() throws Exception {
			System.out.println("Launching Chrome browser...");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
				
				  driver = new ChromeDriver();
		  String url = "https://www.facebook.com/";
		  driver.get(url);
		}

		@Test(dataProvider = "loginData")
		public void testLogin(String username, String password) {
//			try {
				System.out.println("testLogin running...");
				// sending test Input data
				driver.findElement(By.name("email")).sendKeys(username);
				System.out.println(username);
				driver.findElement(By.name("pass")).sendKeys(password);
				System.out.println(password);
				driver.findElement(By.name("login")).click();
				// check login result by checking page title
//				String expectedTitle = "";
//				String actualTitle = driver.getTitle();
//				assertEquals(expectedTitle, actualTitle);
//				boolean result = expectedTitle.equals(actualTitle);
//				index++;
//				res.put("" + index, new Object[] { index, "login page", username + ", " + password, expectedTitle,
//						actualTitle, result ? "Passed" : "Fail" });
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}

		@DataProvider
		public Object[][] loginData() throws Exception {
			String excelFilePath = Paths.get("src","main","resources","test_data", "LoginData.xlsx").toFile().getAbsolutePath();
			Object[][] arr = ExcelUtil.getTableArray(excelFilePath, "Sheet1", 2);
			return arr;
		}

		@AfterMethod
		public void terminateBrowser() {
			driver.close();
		}
		
	
	
	
		
		
}