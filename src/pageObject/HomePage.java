package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	private By dogsLink = By
			.xpath("//span[@class='elementor-image-box-title']/a[contains(@href, '%D7%9B%D7%9C%D7%91%D7%99%D7%9D/')]");
	private By catsLink = By.xpath(
			"//span[@class='elementor-image-box-title']/a[contains(@href, '%d7%97%d7%aa%d7%95%d7%9c%d7%99%d7%9d/')]");
	private By mouseLink = By.xpath(
			"//span[@class='elementor-image-box-title']/a[contains(@href, '%D7%9E%D7%9B%D7%A8%D7%A1%D7%9E%D7%99%D7%9D/')]");
	private By birdLink = By.xpath(
			"//span[@class='elementor-image-box-title']/a[contains(@href, '%d7%91%d7%a2%d7%9c%d7%99-%d7%9b%d7%a0%d7%a3/')]");

	// the same links for both (bug)
	private By fishsLink = By
			.xpath("//span[@class='elementor-image-box-title']/a[contains(@href, '%d7%93%d7%92%d7%99%d7%9d/')]");
	private By boxexLink = By
			.xpath("//span[@class='elementor-image-box-title']/a[contains(@href, '%d7%93%d7%92%d7%99%d7%9d/')]");

	public ProductPageElement getAnimel(String animel) {
		if (animel == "dog") {
			driver.findElement(catsLink).click();
			return new ProductPageElement(driver);
		} else if (animel == "cats") {
			driver.findElement(catsLink).click();
			return new ProductPageElement(driver);
		} else if (animel == "mouse") {
			driver.findElement(mouseLink).click();
			return new ProductPageElement(driver);
		} else if (animel == "bird") {
			driver.findElement(birdLink).click();
			return new ProductPageElement(driver);
		} else if (animel == "fish") {
			driver.findElement(fishsLink).click();
			return new ProductPageElement(driver);
		} else {
			return null;
		}
	}

	public ProductPageElement getDog() {

		driver.findElement(dogsLink).click();
		return new ProductPageElement(driver);
	}

	public ProductPageElement getCats() {
		driver.findElement(catsLink).click();
		return new ProductPageElement(driver);
	}

	public ProductPageElement getMouse() {
		driver.findElement(mouseLink).click();
		return new ProductPageElement(driver);
	}

	public ProductPageElement getBird() {
		driver.findElement(birdLink).click();
		return new ProductPageElement(driver);
	}

	public ProductPageElement getFish() {
		driver.findElement(fishsLink).click();
		return new ProductPageElement(driver);
	}

	public void getBoxex() {
		driver.findElement(boxexLink).click();

	}

	public void getScrollBy() {

	}
}
