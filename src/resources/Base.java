package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	private static Properties prop = new Properties();
//	public static String projectPath = prop.getProperty("pathForProject");
//	public static WebDriver driver;

	public WebDriver driver;

	public WebDriver initialazDriver() throws IOException {
		// chrome

		FileInputStream propertiesFile = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\resources\\data.properties");

		prop.load(propertiesFile);
//		prop.setProperty("browser", browserValue);
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");

		if (browserName.contains("chrome")) {
			// exectue in chrome driver
			System.setProperty("webdriver.chrome.driver", "C://jars//driver/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();

			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
		} else if (browserName.equals("firefox")) {
			// fireFox Code
			System.setProperty("webdriver.gecko.driver", "C://jars//driver/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			// Miscorsoft edge
			System.setProperty("webdriver.edge.driver", "C://jars//driver/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File sourceLocation = screenShot.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(sourceLocation, new File(destinationFile));
		return destinationFile;
	}

	public static void waitExplict(By path, int time, String option, WebDriver driver) {
		WebDriverWait w = new WebDriverWait(driver, time);
		if (option == "visibility") {

			w.until(ExpectedConditions.visibilityOfElementLocated(path));
		} else if (option == "invisibility") {

			w.until(ExpectedConditions.invisibilityOfElementLocated(path));
		} else if (option == "clickable") {
			w.until(ExpectedConditions.elementToBeClickable(path));
		}
	}

	// function for wait for element to dissappear from the page so it will click to
	// the next button
	public static void waitForElementVisibility(By elementLocator, int time, boolean shouldBeVisible,
			WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		if (!shouldBeVisible) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
		} else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		}
	}

	// "asset/app-results.txt"
	public static void createNewFileWithText(String path, String overAllCategories) throws IOException {
		File file = new File(path);
		FileWriter writer = null;
		// Create the file
		try {
			if (file.createNewFile()) {
				System.out.println("New Text File is created!");
			} else {
				System.out.println("File already exists.");
			}
			// Write Content
			writer = new FileWriter(file);
			writer.write(overAllCategories);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> readFunction(String path) throws Exception {
		FileReader fileReader;
		BufferedReader bufferedReader;
		ArrayList<String> arrayofLines = new ArrayList<String>();
		try {
			fileReader = new FileReader(path);
		} catch (Exception e) {
			throw new Exception("file not find");
		}
		bufferedReader = new BufferedReader(fileReader);

		String line = bufferedReader.readLine();

		while (line != null) {
			arrayofLines.add(line);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return arrayofLines;
	}
}
