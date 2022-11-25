package com.edu.graduationproject.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v97.headlessexperimental.model.ScreenshotParams;
import org.openqa.selenium.interactions.Actions;
import org.springframework.format.annotation.DateTimeFormat;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.jsonwebtoken.io.IOException;



public class test1 {
	String url = "http://localhost:8080/security/login/form";
public WebDriver driver;
private XSSFWorkbook workbook;
private XSSFSheet worksheet;
private Map<String, Object[]> TestNGResult;
private Map<String, String[]> dataLoginTest;

private final String EX_DIR = "C:\\Users\\Admin\\Documents\\GRADUATION-PROJECT\\src\\main\\resources\\test_data\\";

private final String IMG_DIR = "C:\\Users\\Admin\\Documents\\GRADUATION-PROJECT\\src\\main\\resources\\test_data\\img";

private void readDataFromExcel() {
	try {
		dataLoginTest = new  HashMap<String,String[]>();
		worksheet = workbook.getSheet("Sheet1");
		if(worksheet == null) {
			System.out.println("No found");
		}else {
			Iterator<Row> rowIterator = worksheet.iterator();
			DataFormatter dataformat = new DataFormatter();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				if(row.getRowNum()>=1) {
					Iterator<Cell> celliterator = row.cellIterator();
					String key = "";
					String name = "";
					String pass = "";
					String excepted ="";
					while(celliterator.hasNext()) {
						Cell cell = celliterator.next();
					if(cell.getColumnIndex() == 0) {
							key = dataformat.formatCellValue(cell);
					}else if(cell.getColumnIndex() == 1) {
						 name = dataformat.formatCellValue(cell);
					}else if(cell.getColumnIndex() == 2) {
						 pass = dataformat.formatCellValue(cell);
					}
					else if(cell.getColumnIndex() == 3) {
						 excepted = dataformat.formatCellValue(cell);
					}
				String[] myArr = {name,pass,excepted};
				dataLoginTest.put(key, myArr);
					}
				}
			}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void takeScreenshot (WebDriver driver, String outputSrc) throws IOException{
	
}

public void writeImage() {
	
}


@BeforeClass
public void suittest() {
	try {
		TestNGResult = new LinkedHashMap<String, Object[]>();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		workbook = new XSSFWorkbook(new FileInputStream(new File(EX_DIR + "LoginData.xlsx")));
		worksheet = workbook.getSheet("Sheet1");
		readDataFromExcel();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		workbook = new XSSFWorkbook();
		worksheet = workbook.createSheet("TestNG Result");
		
		CellStyle rowstyle = workbook.createCellStyle();
		rowstyle.setAlignment(HorizontalAlignment.CENTER);
		rowstyle.setVerticalAlignment(VerticalAlignment.CENTER);
		rowstyle.setWrapText(true);
		
		TestNGResult.put("1", new Object[] {"STT","Username","password","action","Excepted","actual","status","datecheck","link","image"});
	} catch (Exception e) {
		// TODO: handle exception
	}
}







//@Test(priority = 3)
//public void Logintest() {
//	try {
//		Set<String> keyset = dataLoginTest.keySet();
//		int index = 1;
//		for(String key : keyset) {
//			String[] value = dataLoginTest.get(key);
//			String username = value[0];
//			String pass = value[1];
//			String excepted = value[2];
//			 
//			LocalDateTime myDateobj = LocalDateTime.now();
//			DateTimeFormatter myFormatobj = DateTimeFormatter.ofPattern("HH:mm:ss | dd-MM-yyyy");
//			String formattedDate = myDateobj.format(myFormatobj);
//			
//			driver.get("http://localhost:8080/security/login/form");
//			
//			driver.findElement(By.name("username")).sendKeys(username);
//			System.out.println(username);
//			driver.findElement(By.name("password")).sendKeys(pass);
//			System.out.println(pass);
//			Thread.sleep(1000);
//			driver.findElement(By.name("login")).click();
//			
//			
//			String actutitle = driver.getTitle();
//			if(username.equalsIgnoreCase(pass)) {
//				TestNGResult.put(String.valueOf(index +1), new Object[] {
//						String.valueOf(index),
//						username,
//						pass,
//						"testlogin",
//						excepted,
//						actutitle,
//						"PASS",
//						formattedDate,
//						""
//						
//				});
//			}else {
//				TestNGResult.put(String.valueOf(index +1), new Object[] {
//						String.valueOf(index),
//						username,
//						pass,
//						"testlogin",
//						excepted,
//						actutitle,
//						"Fail",
//						formattedDate,
//						""
//						
//				});
//				
//			}
//			index++;
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//}



@Test(priority = 3)
public void Logintest() {
	try {
		Set<String> keyset = dataLoginTest.keySet();
		int index = 1;
		for(String key : keyset) {
			String[] value = dataLoginTest.get(key);
			String username = value[0];
			String pass = value[1];
			String excepted = value[2];
			 
			LocalDateTime myDateobj = LocalDateTime.now();
			DateTimeFormatter myFormatobj = DateTimeFormatter.ofPattern("HH:mm:ss | dd-MM-yyyy");
			String formattedDate = myDateobj.format(myFormatobj);
			
			driver.get("http://localhost:8080/security/login/form");
			
			driver.findElement(By.name("username")).sendKeys(username);
			System.out.println(username);
			driver.findElement(By.name("password")).sendKeys(pass);
			System.out.println(pass);
			Thread.sleep(1000);
			driver.findElement(By.name("login")).click();
			
			WebElement tt = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/article[1]/section[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/span[1]\r\n"));
			Thread.sleep(2000);
			org.testng.Assert.assertEquals(tt.getText(), "Sai thông tin hoặc tài khoản chưa được kích hoạt!");
			
			String actutitle = driver.getTitle();
			 
				TestNGResult.put(String.valueOf(index +1), new Object[] {
						String.valueOf(index),
						username,
						pass,
						"testlogin",
						excepted,
						actutitle,
						"PASS",
						formattedDate,
						""
						
				});
			
//				TestNGResult.put(String.valueOf(index +1), new Object[] {
//						String.valueOf(index),
//						username,
//						pass,
//						"testlogin",
//						excepted,
//						actutitle,
//						"Fail",
//						formattedDate,
//						""
//						
//				});
				
			
			index++;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}




@AfterClass
public void suiteteardown(){
	Set<String> keyset = TestNGResult.keySet();
	int rownum = 0;
	for(String key : keyset) {
		CellStyle rowstyle = workbook.createCellStyle();
		Row row = worksheet.createRow(rownum++);
		Object[] objarr = TestNGResult.get(key);
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
