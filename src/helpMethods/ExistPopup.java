package helpMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import academySite.AcdaemySiteMainTest;
import pageObject.ProductPageElement;


public class ExistPopup {
	 static WebDriver driver;
		private static Logger log = LogManager.getLogger(ExistPopup.class.getName());
	public static void popupClickOnClose(WebDriver driver,ProductPageElement homePage) throws InterruptedException {
//		homePage.getPopUpSize();
		
		if (homePage.getPopUpSize() > 0) {
			log.info("Remove ADD");
			homePage.PopupSubscribeExit().click();
		}
	}
}
