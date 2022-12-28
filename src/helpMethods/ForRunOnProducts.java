package helpMethods;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataExcel.DataDrivenInsert;
import pageObject.ProductPageElement;
import resources.Base;

public class ForRunOnProducts {
	public WebDriver driver;

	public static ArrayList<String> productOption(WebDriver driver, List<WebElement> products, String productOption) {
		ArrayList<String> arrayOfProducts = new ArrayList<String>();

		if (productOption == "name") {
			runOnProudctsByArray(driver, products, arrayOfProducts, "a/h2");

			return arrayOfProducts;
		} else if (productOption == "price") {
			runOnProudctsByArray(driver, products, arrayOfProducts, "a/h2/following-sibling::span");

			return arrayOfProducts;
		} else if (productOption == "all") {
			getPriceAndName(driver, products, arrayOfProducts);
			return arrayOfProducts;
		} else {
			return null;
		}

	}

	public static void runOnProudctsByArray(WebDriver driver, List<WebElement> products,
			ArrayList<String> arrayOfProducts, String locator) {

		for (int i = 0; i < products.size(); i++) {
			String productTitle = driver.findElements(By.xpath("//li[contains(@class,'product type-product')]")).get(i)
					.findElement(By.xpath(locator)).getText();
			arrayOfProducts.add(productTitle + "\n");
		}
	}

	public static void getPriceAndName(WebDriver driver, List<WebElement> products, ArrayList<String> arrayOfProducts) {

		for (int i = 0; i < products.size(); i++) {

			String productNameTitle = driver.findElements(By.xpath("//li[contains(@class,'product type-product')]"))
					.get(i).findElement(By.xpath("a/h2")).getText();
			String productPrice = driver.findElements(By.xpath("//li[contains(@class,'product type-product')]")).get(i)
					.findElement(By.xpath("a/h2/following-sibling::span")).getText();
			arrayOfProducts.add(productNameTitle + "\n");
			arrayOfProducts.add(productPrice);
		}
	}

	public ArrayList<String> runOnAllPagesToGetAllProducts(ProductPageElement homePageAnimel, WebDriver driver) {
		Boolean state = true;
		ArrayList<String> productsName = new ArrayList<String>();
		while (state) {
			try {
				List<WebElement> products = homePageAnimel.listOfProducts();
				ArrayList<String> productName = productOption(driver, products, "all");
				productsName.addAll(productName);

				waitForPopupAndExit(homePageAnimel, driver);
				homePageAnimel.clickOnNextButton().click();

			} catch (Exception e) {

				state = false;
			}
		}
		return productsName;
	}

	public static void showPage(ProductPageElement homePageAnimel) {

		try {
			String numberOfPage = homePageAnimel.currentPageOfAnime().getText();
			System.out.println(numberOfPage);
		} catch (Exception error) {

		}
	}

	public static void waitForPopupAndExit(ProductPageElement homePageAnimel, WebDriver driver) {
		try {
			Base.waitExplict(homePageAnimel.popup, 20, "visibility", driver);
			ExistPopup.popupClickOnClose(driver, homePageAnimel);
		} catch (Exception e) {
			System.out.println("no adds");
		}

		Base.waitForElementVisibility(homePageAnimel.popup, 20, false, driver);
	}
}