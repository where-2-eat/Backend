Feature: Select Location
	As a user currently logged in, I want to select a location

	Background: 
		Given The user is logged in
		And Has their country location set on their profile
	
	Scenario Outline: Successful selection of location for solo (Normal flow)
		When The user selects city "<_city>"
		And The user provides address "<_address>"
		Then The location name "<_city>" and address "<_address>" should be displayed
		
		Examples:
		| full_name	| _city		| _address	|
		| Kobe Bryant	| Montreal	| 1234 Durocher	|
		| Chris Paul 	| Vancouver	| 3462 Kennedy	|


	Scenario Outline: Successful selection of location for group (Alternate flow)
		When The user "<full_name>" creates an event "<_event>"
		And The user selects city "<_city>"
		And The user provides address number "<_address>"
		Then The location name "<_city>" and address "<_address>" should be displayed

		Examples:
		| full_name	| _event	| _city		| _address	|
		| Steve Jobs	| lunch		| Montreal	| 1234 Durocher	|
		| John Doe	| dinner	| Toronto	| 3462 Kennedy	|

	
	Scenario Outline: Address provided does not exist (Error flow)
		When The user selects city "<_city>"
		And The user provides address "<_address>"
		Then An error message "Invalid address" will be displayed

		Examples:
		| _city		| _address |
		| Toronto	| Ronaldo  |
		| Montreal	| Neymar 4 |

