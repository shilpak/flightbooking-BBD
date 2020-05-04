Feature: Kayak Search Page

@smoke
Scenario Outline: Kayak Search Scenario

Given user is on Kayak Search page
When user verify the pageTitle "Search Flights, Hotels & Rental Cars | KAYAK"
When user Uncheck the default Selection
Then user enters  From Location "<Departure>" 
Then user enters To Location "<Arrival>" 
Then User Select the Departure Date "<StartDate>" 
Then user Select the Arrival Date  "<EndDate>"
Then user clicks Search Icon
Then user dismiss the pop up
Then user click on the deals
Then user scroll down for deal details 
Then user verifies departure "<Departure>"
Then user verifies arrival "<Arrival>"
Then close the Browser

Examples:
|Departure        |Arrival             |StartDate       |EndDate        |
|Atlanta (ATL)    |San Francisco (SFO) |27, April 2020  |10, May 2020   |
|Denver (DEN)     |New Orleans (MSY)   |10, April 2020  |20, May 2020   |


