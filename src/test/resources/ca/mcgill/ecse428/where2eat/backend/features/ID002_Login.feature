Feature: Login
	As an existing user, I want to login to access my account

	Background: 
		Given The following users are already registered 
		|full_name    |_username     |_password    |
		|Steve Jobs   |steve99       |iphoneX89    |
		|John Doe     |johnDoe02     |abcd123      |

	Scenario Outline: Successful Login (Normal flow)
		When The user "<full_name>" logs in with username "<_username>" and password "<_password>"
		Then The login is successful 
		Examples:
		|full_name    |_username     |_password    |
		|Steve Jobs   |steve99       |iphoneX89    |
		|John Doe     |johnDoe02     |abcd123      |
	

	Scenario Outline: Unsuccessful Login (Error flow)
		When The user "<full_name>" logs in with username "<_username>" and password "<_password>"
		Then An error message saying "Wrong username or password, please try again." is displayed
		Examples:
		|full_name    |_username     |_password    |
		|Steve Jobs   |stev9         |iphoneX89    |
		|John Doe     |johnDoe02     |abcd13       |
	


