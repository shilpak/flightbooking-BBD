package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;



public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static  BrowserUtil oBroUtil = new BrowserUtil();

	public TestBase() {
	
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
			System.getProperties().putAll(prop);
			ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/log4j.properties");
			prop.load(ip);
			PropertyConfigurator.configure(prop);
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}	
	public static void intialisation() throws Exception {
		oBroUtil.launchBrowser( prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));

	}



}


