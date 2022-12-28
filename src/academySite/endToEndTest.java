package academySite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import helpMethods.ForRunOnProducts;
import pageObject.HomePage;
import pageObject.ProductPageElement;
import resources.ValidData;
import resources.Base;
import dataExcel.DataDrivenInsert;

public class endToEndTest extends Base {
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(endToEndTest.class.getName());
	public DataDrivenInsert dataInsert;
	public HomePage homePage;
	public static String path = System.getProperty("user.dir") + "/file.txt";
	int categroesFromLastRun = 0;
	ArrayList<String> allCategoriesName = new ArrayList<String>();;
	ArrayList<String> allProductsName = new ArrayList<String>();;

// first thing is testing which line is failing before trying to fix it and dont judge only by the error message
	// and do debugg if you need
//	@BeforeTest
	public void initialize() throws IOException {
		driver = initialazDriver();
	}

	@BeforeMethod
	public void init() throws IOException {
		initialize();

		String path = System.getProperty("user.dir") + "\\Data.xlsx";
		dataInsert = new DataDrivenInsert(path);

		homePage = new HomePage(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

	}

	@Test(priority = 1)
	public void basePageNavigationDog() throws Exception {
		

	}





	@DataProvider
	public static Object[][] getData() throws IOException {
		return ValidData.getData();
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@AfterTest
	public void closingBrowser() throws InterruptedException {
//		System.out.println(this.categroesFromLastRun);

	}

}
