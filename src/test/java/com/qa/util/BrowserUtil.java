package com.qa.util;

import java.util.List;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.chrono.AssembledChronology;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BrowserUtil extends TestBase {

	Logger log = Logger.getLogger(getClass().getSimpleName());

	public void launchBrowser(String browser) throws Exception {
		log.info("Launching Browser : "+browser);

		// Check if parameter passed from TestNG is 'firefox'/chrome
		if (browser.toLowerCase().startsWith("ch")) {


			WebDriverManager.chromedriver().setup(); ChromeOptions chromeOptions = new
					ChromeOptions(); chromeOptions.addArguments("--disable-notifications");
					driver = new ChromeDriver(chromeOptions);

		}  else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();

	}

	public boolean waitForElementVisible(WebDriver driver,  WebElement ele, int iTimeInSeconds) throws Exception {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		

		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (ele.isDisplayed()) {
					log.info("Element Displayed : " + ele);
					return ele;
				} else {
					log.info("******Element NOT Displayed : " + ele);
					return null;
				}
			}
		});

		return ele.isDisplayed();
	}

	public boolean waitForElementDisable(WebDriver driver,  WebElement ele, int iTimeInSeconds) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
		.pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (!ele.isDisplayed()) {
					log.info("Element Not Displayed : " + ele);
					return ele;
				} else {
					log.info("******Element is Still Displaying : " + ele);
					return null;
				}
			}
		});

		return !ele.isDisplayed();
	}

	public boolean isDisplayed(WebElement ele) throws Exception {
		boolean bRes_flag = false;
		try {
			if (ele.isDisplayed()) {
				log.info("Displayed " + ele);
				bRes_flag = true;
			}
		} catch (Exception ea) {
			bRes_flag = false;
		}
		return bRes_flag;
	}

	public void screenShotBrowser(WebDriver driver, String className) throws Exception {
		String destDir = "screenshots";
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
		String destFile = className + ".png";

		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "/test-output/" + destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void screenShotBrowserForWebElement(WebDriver driver, WebElement eleScreenArea, String className)
			throws Exception {
		String destDir = "screenshots";
		String destFile = className + ".png";
		log.info("ScreenShot");
		// Screenshot fpScreenshot = new
		// AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver, eleScreenArea);
		// Screenshot fpScreenshot = new AShot().takeScreenshot(driver, eleScreenArea);

		try {
			ImageIO.write(fpScreenshot.getImage(), "PNG",
					new File(System.getProperty("user.dir") + "/target/surefire-reports/" + destDir + "/" + destFile));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void isDisplayed(WebElement ele, String str) throws Exception {

		Assert.assertTrue("Actual:" + str + " is displayed. Expected:" + str + " is not displayed", ele.isDisplayed());
		log.info("Actual:" + str + " Expected:" + str + " ");
		Assert.assertTrue("Actual:" + str + " is Enabled. Expected:" + str + " is not Enabled", ele.isEnabled());

	}

	public boolean isAttribtuePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	public boolean ScrollToView(WebDriver driver, WebElement ele) throws Exception {
		boolean bRes_Flag = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
		bRes_Flag = true;
		return bRes_Flag;
	}

	public void ClickUsingJS(WebElement ele) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public void ufClick(WebElement ele) throws Exception {
		ele.click();
	}

	public void ufSendKeys(WebElement ele, String keysToSend) throws Exception {
		ele.sendKeys(keysToSend);
	}

	public String ufGetText(WebElement ele) throws Exception {
		return ele.getText();
	}

	public void ufMouseOver(WebElement ele, WebDriver driver) throws Exception {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele);		

	}

}
