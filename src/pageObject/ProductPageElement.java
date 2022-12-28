package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPageElement {
	public WebDriver driver;
	public static By productsList = By.xpath("//li[contains(@class,'product type-product')]");
	// not on the current page
	public By currentPageEl = By.xpath("//ul[@class='page-numbers']/li/span[@class='page-numbers current']");
	public static By allPages = By.xpath("//ul[@class='page-numbers']/li");

	public By prevButton = By.xpath("//ul[@class='page-numbers']/li/a[@class='prev page-numbers']");
	public By nextButton = By.xpath("//ul[@class='page-numbers']/li/a[@class='next page-numbers']");
	public By categoryForAnimel = By.xpath("//nav[contains(@class,'e--pointer-underline')]/ul/li");
	public By titleOfPage = By
			.xpath("//div[@class='elementor-widget-container']/h1[contains(@class,'elementor-heading-title')]");
	public By popup = By.xpath("//i[@class = 'eicon-close']");

	public ProductPageElement(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement currentPageOfAnime() {
		return driver.findElement(currentPageEl);
	}

	public List<WebElement> listOfProducts() {
		return driver.findElements(productsList);
	}

	public WebElement clickOnNextButton() {
		return driver.findElement(nextButton);
	}

	public List<WebElement> categoriesOFAnimal() {
		return driver.findElements(categoryForAnimel);
	}

	public WebElement title() {
		return driver.findElement(titleOfPage);
	}

	public WebElement PopupSubscribeExit() {
		return driver.findElement(popup);
	}

	public int getPopUpSize() {
		return driver.findElements(popup).size();
	}
}