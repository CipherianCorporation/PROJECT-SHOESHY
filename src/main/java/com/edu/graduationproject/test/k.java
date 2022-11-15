package com.edu.graduationproject.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class k {
	@Test
	  public void Testlogin() {
	 
	  System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chromedriver.exe");
//		  WebDriverManager.chromedriver().setup();
//			ChromeOptions op = new ChromeOptions();
			  WebDriver dri = new ChromeDriver();
	  String url = "http://localhost:8080/security/login/form";
	  dri.get(url);
	
	 
	 
	  WebElement email=dri.findElement(By.name("username"));
	  WebElement pass=dri.findElement(By.name("password"));
	  WebElement login=dri.findElement(By.name("login"));
	
	  WebElement p=dri.findElement(By.xpath("/html/body/div[1]/main/article/section/div/div/div/div/form/div/div[4]"));
	  email.sendKeys("thuanle2425@gmail");
	  pass.sendKeys("24250");
	  login.click();
	 
WebElement tt = dri.findElement(By.className("badge rounded-pill badge-danger mb-2 p-3"));
org.testng.Assert.assertEquals(tt.getText(), "Sai thông tin hoặc tài khoản chưa được kích hoạt!");
	
}
}