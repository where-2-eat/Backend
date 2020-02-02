Feature: Search Restaurant
	As a user, I want to search for a restaurant to view its basic information
	
	Background:
		Given An exiting user is logged into the where2eat system
		And The user wants to search for a restaurant
	
	Scenario Outline: Search restaurant by name (Normal flow) 
		When The user searches for a restaurant named "<resto_name>"
		Then A new page shall be displayed with a list of restaurants
		And The basic information for each restaurant shall be displayed
		Examples: 
		|resto_name  |
		|McDonald    |
		|Basha       |
		|Tim Hortons |

	Scenario Outline: Search restaurant by type (Alternate flow)
		When The user searches for a restaurant type "<resto_type>"
		Then A new page shall be displayed with a list of restaurants 
		And The basic information for each restaurant shall be displayed
		Examples: 
		|resto_type      |
		|fast food       |
		|mexican         |
		|chinese         |
		|french	         |
		|all you can eat |

	Scenario Outline: Search by type leads to no results (Error flow)
		When The user searches for a restaurant type "<resto_type>"
		Then An error message saying "No results found for this restaurant type" shall be displayed
		Examples: 
		|resto_type   |
		|             |
		|Mcgill       |
		|____         |

	Scenario Outline: Seach by name leads to no results (Error flow)
		When The user searches for a restaurant named "<resto_name>"
		Then An error message saying "No results found for this restaurant name" shall be displayed
		Examples: 
		|resto_name   |
		|             |
		|Mcgill       |
		|____         | 

