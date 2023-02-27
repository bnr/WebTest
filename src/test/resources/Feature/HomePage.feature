Feature: User to verify the features under home page

@smoke
Scenario: User Verify the components under home page   
	Given User launches the web application
	Then User verifies the components under home page

@smoke
Scenario: User verify the record count filter display feature
  Given User launches the web application
  When User sets the record count filter to "20"
  Then User stored the data displayed 
  When User set Algorith filter to PoW and filters under More Filter 
	Then User compares the data between filter and unfilter data


