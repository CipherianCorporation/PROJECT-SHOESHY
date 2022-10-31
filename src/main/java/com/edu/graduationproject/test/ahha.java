package com.edu.graduationproject.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;


public class ahha {
	WebDriver driver;
	String url = "https://www.facebook.com/";
	Map<String, Object[]> TestNgResult; 
	private XSSFWorkbook workbook;
	private XSSFSheet worksheet;
	private final String EX_DIR = "C:\\Users\\Admin\\Documents\\GRADUATION-PROJECT\\src\\main\\resources\\test_data\\";

@Test(priority = 1 )
public void f() {
	try {
		driver.get(url);
		driver.manage().window().maximize();
		TestNgResult.put("2", new Object[] {1d,"WebDemo","Site get open","pass"});

	} catch (Exception e) {
		TestNgResult.put("2", new Object[] {1d,"WebDemo","Site get open","fail"});
            org.testng.Assert.assertTrue(false);
	}
}

@Test(priority = 2 )
public void FillloginDetails() throws Exception {
	try {
	WebElement username = driver.findElement(By.name("email"));
	username.sendKeys("thuanle2425@gmail.com");
		System.out.println(username);
		WebElement pass = driver.findElement(By.name("pass"));
		pass.sendKeys("");
		driver.findElement(By.name("login")).click();
		TestNgResult.put("3", new Object[] {2d,"filllogin(username/pass","Fill gets fill","pass"});

	} catch (Exception e) {
		TestNgResult.put("3", new Object[] {2d,"filllogin(username/pass","Fill gets fill","fail"});
		 org.testng.Assert.assertTrue(false);

	}
}
@Test(priority = 3 )
public void dologinDetails() throws Exception {
	try {
	
		WebElement login = driver.findElement(By.name("login"));
		login.click();
		TestNgResult.put("4", new Object[] {3d,"login","login success","pass"});

	} catch (Exception e) {
		TestNgResult.put("4", new Object[] {3d,"login","login success","fail"});
		 org.testng.Assert.assertTrue(false);
	}
}
@BeforeClass
public void suiteseup() {
	WebDriverManager.chromedriver().setup();
	workbook = new XSSFWorkbook();
	worksheet = workbook.createSheet("testng");
	TestNgResult = new LinkedHashMap<String ,Object[]>();
	
	TestNgResult.put("1", new Object[] {"Test step No.","Action","Expected Output","actual Output"});
	try {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	} catch (Exception e) {
		throw new IllegalStateException("Can't Start");
	}
}
@AfterClass
public void teardown() {
	Set<String> keyset = TestNgResult.keySet();
	int rownum = 0;
	for(String key : keyset) {
		CellStyle rowstyle = workbook.createCellStyle();
		Row row = worksheet.createRow(rownum++);
		Object[] objarr = TestNgResult.get(key);
		int cellnum = 0;
		for(Object obj : objarr) {
			int flag = cellnum ++;
			Cell cell = row.createCell(flag);
			if(obj instanceof Date) {
				cell.setCellValue((Date) obj);
			}else if(obj instanceof Boolean) {
				cell.setCellValue((Boolean) obj);
			}else if(obj instanceof String) {
				cell.setCellValue((String) obj);
			}else if(obj instanceof Double) {
				cell.setCellValue((Double) obj);
			}
			
		}
		try {
			FileOutputStream out  = new FileOutputStream(new File(EX_DIR + "LoginData_result.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Successfully !!");
		} catch (Exception e) {
			System.out.println("suiteteardown() :" +e.getMessage());
			// TODO: handle exception
		}
	}
}
}
