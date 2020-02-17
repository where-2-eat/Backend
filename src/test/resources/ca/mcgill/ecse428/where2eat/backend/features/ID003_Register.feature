Feature: Register
	As a new where2eat user, I would like to to create an account using a unique username and a password

	Scenario Outline: New user (Normal flow)
		When A user named "<full_name>" creates an account with password "<_password>" and username "<_username>"
		Then A new account is created for "<full_name>" with a password "<_password>" and a username "<_username>"
		
		Examples:
		|full_name    |_username     |_password    |
		|Steve Jobs   |steve99       |iphoneX89    |
		|John Doe     |johnDoe02     |abcd123      |


	Scenario Outline: The username provided already exists (Error flow)
		Given The following users are already registered
		|full_name    |_username     |_password    |
		|Steve Jobs   |steve99       |iphoneX89    |
		|John Doe     |johnDoe02     |abcd123      |

		When A user named "<full_name>" creates an account with password "<_password>" and username "<_username>"
		Then An error message informing user of pre-existing user is displayed
		Examples:
		|full_name    |_username     |_password    |
		|Steve Smith  |steve99       |iphone1234   |
		|John Doe2    |johnDoe02     |1234abcd     |

	Scenario Outline: Invalid username or password (Error flow) 
		When A user named "<full_name>" creates an account with password "<_password>" and username "<_username>"
		Then An error message informing user of incorrect information is returned
		Examples:
		|full_name    |_username     |_password    |
		|Steve Jobs   |steve99       |             |
		|John Doe     |johnDoe 2     |abcd123      |