package com.qa.po;


import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.util.TestBase;
import com.qa.util.BrowserUtil;

public class poHomePage extends TestBase {
	Logger log = Logger.getLogger(getClass().getSimpleName());
	@FindBy(xpath = "//div[@class='search-form-inner']//div[@class='_ia1']//button")
	WebElement depCrossIcon;

	@FindBy(xpath = "//*[contains(@id,'origin-airport-textInputWrapper')]/input[contains(@placeholder,'From?')]")
	WebElement departureCity;
	@FindBy(xpath="//div[@data-placeholder='To?']")
	WebElement arrivalCity;

	@FindBy(xpath="//input[contains(@id,'destination-airport') and @placeholder='To?']")
	WebElement arrivalCityInput;

	@FindBy(xpath="//div[@data-placeholder='Depart']")
	WebElement departureDate;

	@FindBy(xpath="//div[@data-placeholder='Return']")
	WebElement returnDate;

	@FindBy(xpath="(//button[@aria-label='Search flights'])[1]")
	WebElement searchFlightIcon;

	@FindBy(xpath="(//div[@class=\"viewport\"])[13]/div/button") 
	WebElement closePopUp;
	@FindBy(xpath="//span[contains(text(),'Save price alert')]") 
	WebElement popUp_Save_price_alert;  

	@FindBy(xpath="//div[@aria-label='Departure date input']")
	WebElement departureDatePlaceholder;

	@FindBy(xpath="//div[@class='input _iKG _id7 _ial _ii0 _iQj _iaj _ihh _idE' and @aria-label='Departure date input']") 
	WebElement chooseDate;

	@FindBy(xpath="//div[@data-placeholder='Return']") 
	WebElement returnDatePlaceholder;

	@FindBy(xpath="(//div[@aria-label=\"Date selection modes\"])[1]")
	WebElement calenderView;

	@FindBy(xpath="//div[@class='resultWrapper']")
	WebElement resultContainer;

	@FindBy(xpath="//div[@class='resultWrapper']")
	List<WebElement> liResultContainer;

	@FindBy(xpath="(//label[contains(text(),'Depart')])[1]")
	WebElement Depart;

	@FindBy(xpath="(//span[@class=\"origin\"]")
	List<WebElement> liResultOrigin;

	@FindBy(xpath="//div[@id='yFw_-info']//ol[@class='flights']")
	WebElement deal;

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]")
	WebElement BasicEco;

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/span[1]/span[1]")
	WebElement departureele;

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/span[2]/span[1]")
	WebElement arrivalele;

	String dynamicXpathDate="//div[contains(text(),'REPLACE_MMYYYY')]/..//div[text()='REPLACE_DATE']";


	public poHomePage() {
		PageFactory.initElements(driver, this);
	}

	// verify title of page
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public void selectfrom() throws Exception {
		try {
			oBroUtil.waitForElementVisible(driver, depCrossIcon, 3);
		}catch(Exception a) {}
		if(oBroUtil.isDisplayed(depCrossIcon))
			oBroUtil.ufClick(depCrossIcon);
	}

	// Input for the departure city
	public void EnterfromAirtport(String departure) throws Exception {
		oBroUtil.waitForElementVisible(driver, departureCity, 2);
		oBroUtil.ufSendKeys(departureCity, departure);
		departureCity.sendKeys(Keys.ENTER);
		Thread.sleep(3000);


	}

	// Input for the Arrival city
	public void EntertoAirtport(String arrival) throws Exception {
		oBroUtil.waitForElementVisible(driver, arrivalCity, 2);
		oBroUtil.ufClick(arrivalCity);
		oBroUtil.waitForElementVisible(driver, arrivalCityInput, 3);
		oBroUtil.ufSendKeys(arrivalCityInput, arrival);
		arrivalCityInput.sendKeys(Keys.ENTER);
	}

	// Input Departure Date
	public void SelectFromDate(String departureDate) throws Exception {
		oBroUtil.waitForElementVisible(driver, departureDatePlaceholder, 3);
		oBroUtil.ufClick(departureDatePlaceholder);
		oBroUtil.waitForElementVisible(driver, calenderView, 3);
		String[] sArrDateMonthYear = departureDate.split(",");
		dynamicXpathDate=dynamicXpathDate.replaceAll("REPLACE_MMYYYY", sArrDateMonthYear[1].trim()).replaceAll("REPLACE_DATE", sArrDateMonthYear[0].trim());	
		WebElement ele = driver.findElement(By.xpath(dynamicXpathDate));
		oBroUtil.ufClick(ele);
		Thread.sleep(2000);
	}

	// Input Return Date
	public void SelectReturnDate(String rDate) throws Exception {
		oBroUtil.waitForElementVisible(driver, calenderView, 3);
		String[] sArrDateMonthYear = rDate.split(",");
		dynamicXpathDate=dynamicXpathDate.replaceAll("REPLACE_MMYYYY", sArrDateMonthYear[1].trim()).replaceAll("REPLACE_DATE", sArrDateMonthYear[0].trim());	
		WebElement ele = driver.findElement(By.xpath(dynamicXpathDate));
		oBroUtil.ufMouseOver(ele, driver);
		oBroUtil.ufClick(ele);

		oBroUtil.waitForElementDisable(driver, calenderView, 2);

	}


	public void ClickSearchIcon() throws Exception{
		oBroUtil.waitForElementVisible(driver, searchFlightIcon, 2);
		oBroUtil.ufClick(searchFlightIcon);

	}

	public void closePopUpAlert()   throws Exception{
		try {
			oBroUtil.waitForElementVisible(driver, popUp_Save_price_alert, 20);	
			oBroUtil.ufClick(closePopUp);
		}catch(Exception ea) {
		}
	}


	public void clickOnDeals() throws Exception{

		oBroUtil.waitForElementVisible(driver, resultContainer, 5);
		oBroUtil.ufClick( liResultContainer.get(0));		
		oBroUtil.ufClick(BasicEco);

	}

	public void scrollDown() throws Exception {
		oBroUtil.ScrollToView(driver, arrivalele );
	}

	public String verifyDeparture() {
		String departure=departureele.getText();
		return departure;
	}

	public String verifyArrival() {
		String arrival=arrivalele.getText();
		return arrival;
	}
	public void closeBrowser()throws Exception{

		driver.close();
	}




}
