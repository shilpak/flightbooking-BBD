package com.qa.stepDefination;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.qa.po.poHomePage;
import com.qa.util.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageStepDefination extends TestBase {

	Logger log = Logger.getLogger(getClass().getSimpleName());
	poHomePage homepage;

	@Given("^user is on Kayak Search page$")
	public void user_is_on_Kayak_Search_page() throws Throwable {
		TestBase.intialisation();
		homepage = new poHomePage();
	}

	@When("^user verify the pageTitle \"([^\"]*)\"$")
	public void user_verify_the_pageTitle(String expectedTitle) throws Throwable {
		String title = homepage.verifyHomePageTitle();
		Assert.assertEquals("Search Flights, Hotels & Rental Cars | KAYAK", title);
	}

	@When("^user Uncheck the default Selection$")
	public void user_Uncheck_the_default_Selection() throws Throwable {
		homepage.selectfrom();
	}

	@Then("^user enters  From Location \"([^\"]*)\"$")
	public void user_enters_From_Location(String departure) throws Throwable {
		log.info("Departure City: " + departure);
		homepage.EnterfromAirtport(departure);
	}

	@Then("^user enters To Location \"([^\"]*)\"$")
	public void user_enters_To_Location(String arrival) throws Throwable {
		homepage.EntertoAirtport(arrival);

	}

	@Then("^User Select the Departure Date \"([^\"]*)\"$")
	public void user_Select_the_Departure_Date(String rDate) throws Throwable {
		homepage.SelectFromDate(rDate);

	}

	@Then("^user Select the Arrival Date  \"([^\"]*)\"$")
	public void user_Select_the_Arrival_Date(String departureDate) throws Throwable {
		homepage.SelectReturnDate(departureDate);
	}

	@Then("^user clicks Search Icon$")
	public void user_clicks_Search_Icon() throws Throwable {
		homepage.ClickSearchIcon();
	}

	@Then("^user dismiss the pop up$")
	public void user_dismiss_the_pop_up() throws Throwable {
		homepage.closePopUpAlert();
	} 

	@Then("^user click on the deals$")
	public void user_click_on_the_deals() throws Throwable {
		homepage.clickOnDeals();
	}  
	
	@Then("^user scroll down for deal details$")
	public void user_scroll_down_for_deal_details () throws Exception {
		homepage.scrollDown();
		
	}

	@Then("^user verifies departure \"([^\"]*)\"$")
	public void user_verifies_departure(String departure) throws Throwable {
		String dep=homepage.verifyDeparture(); 
		Assert.assertEquals(dep, departure);
	}

	@Then("^user verifies arrival \"([^\"]*)\"$")
	public void user_verifies_arrival(String arrival) throws Throwable {
		String dep=homepage.verifyArrival(); 
		Assert.assertEquals(dep, arrival);
	}

	@Then("^close the Browser$")
	public void close_the_Browser() throws Throwable {
		homepage.closeBrowser();
	}

}
