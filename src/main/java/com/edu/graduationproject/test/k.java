package com.edu.graduationproject.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class k {
	@Test
	  public void Testlogin() {
	 
	  System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chromedriver.exe");
//		  WebDriverManager.chromedriver().setup();
//			ChromeOptions op = new ChromeOptions();
			  WebDriver dri = new ChromeDriver();
	  String url = "http://localhost:8080/account/forgotpassword";
	  dri.get(url);
	
	 
	 try {
		  WebElement email=dri.findElement(By.name("email"));
			 
		  WebElement login=dri.findElement(By.xpath("/html/body/div[1]/main/article/section/div/div/div/div/div/form/div[2]/button"));
		
		  email.sendKeys("thuanle2425@");
		 
		  login.click();
		  
		 Thread.sleep(1000);
	WebElement tt = dri.findElement(By.className("message badge rounded-pill badge-danger mb-2 p-3"));
	
	org.testng.Assert.assertEquals(tt.getText(), "Tài khoản của bạn không tồn tạ");
	
	if(tt.isDisplayed()) {
		System.out.println("PASS");
	}
	System.out.println("fail");
	} catch (Exception e) {
		// TODO: handle exception
	}

	
}
}