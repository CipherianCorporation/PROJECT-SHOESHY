package com.edu.graduationproject.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCart {

	WebDriver driver;

	@BeforeClass
	public void connect() {
		System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		String url = "http://localhost:8080";
		driver.get(url);

	}
@Test
public void main() {
	 WebElement logi=driver.findElement(By.xpath("//body/div[1]/main[1]/article[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/button[1]\r\n"
	 		));
		logi.click();

	 WebElement login=driver.findElement(By.xpath("//header/nav[1]/div[1]/div[2]/a[1]/i[1]"));
	login.click();
}

public void buy(String[] data) {
	
	
//	/html/body/div[1]/main/article/main/div/section/div/div/div/div/div/div/div[1]/div/div[2]/div[3]/input
}
@AfterClass
public void close() {
	
}
}
