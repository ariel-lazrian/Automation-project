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

public class AcdaemySiteMainTest extends Base {
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(AcdaemySiteMainTest.class.getName());
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
		log.info("dog link");
		ProductPageElement homeAnimel = homePage.getDog();
		runByPageOfGroup(homeAnimel, dataInsert);

	}

	@Test(priority = 2)
	public void basePageNavigationCat() throws Exception {
		log.info("cat link");
		ProductPageElement homeAnimel = homePage.getCats();
		runByPageOfGroup(homeAnimel, dataInsert);
	}

	@Test(priority = 3)
	public void basePageNavigationBird() throws Exception {
		log.info("bird link");
		ProductPageElement homeAnimel = homePage.getBird();
		runByPageOfGroup(homeAnimel, dataInsert);
	}

//	@Test(priority = 4)
//	public void basePageNavigationMouse() throws InterruptedException, IOException {
//		 System.out.println( "three-test " + categroesFromLastRun);
//		ProductPageElement homeAnimel = homePage.getMouse();
//		runByPageOfGroup(homeAnimel,dataInsert);
//	}

	@Test(priority = 5)
	public void basePageNavigationFish() throws Exception {
		log.info("fish link ");
		ProductPageElement homeAnimel = homePage.getFish();
		runByPageOfGroup(homeAnimel, dataInsert);
	}

	public void runByPageOfGroup(ProductPageElement homeAnimel, DataDrivenInsert dataInsert) throws Exception {

		ForRunOnProducts forRunOnProducts = new ForRunOnProducts();

		String titleOfPage = homeAnimel.title().getText();
		dataInsert.setCellData("Sheet1", this.categroesFromLastRun, 0, titleOfPage);
		this.categroesFromLastRun += 1;

		List<WebElement> categories = homeAnimel.categoriesOFAnimal();

		for (int i = 0; i < categories.size(); i++) {

			By pathForCategory = By.xpath("//nav[contains(@class,'e--pointer-underline')]/ul/li");
			driver.findElements(pathForCategory).get(i).click();
			System.out.println(driver.findElements(pathForCategory));
			
			String category = driver.findElements(pathForCategory).get(i).getText();
			dataInsert.setCellData("Sheet1", i + this.categroesFromLastRun, 0, category);

			ArrayList<String> products = forRunOnProducts.runOnAllPagesToGetAllProducts(homeAnimel, driver);

			for (int j = 0; j < products.size(); j++) {

				dataInsert.setCellData("Sheet1", this.categroesFromLastRun + i, j + 1, products.get(j));
			}
//			dataInsert.setCellData("Sheet1", i + 1, products.size() + 1, String.valueOf(products.size()));
		}

		this.categroesFromLastRun += categories.size();
	}

	public static void showCategoryAndPagesOFAnimel(String category, WebDriver driver) {
		By allpagesPath = pageObject.ProductPageElement.allPages;
		int pagesOfEveryCategory = driver.findElements(allpagesPath).size() - 1;
		System.out.println(category + " pages: " + (pagesOfEveryCategory == -1 ? 0 : pagesOfEveryCategory));
	}

	public static void putTitleForEveryRun(ProductPageElement homeAnimel, DataDrivenInsert dataInsert,
			int categroesFromLastRun) throws IOException {

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
