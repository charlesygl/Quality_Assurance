Feature: As a user, I can search items so that I can find them
    
    Scenario: Test search function with available items
    Given Open firefox and open webpage
    When I enter iPod in the search bar
    Then iPod should be listed in the search result

    Scenario: Test search function with unavailable items
    Given Open firefox and open webpage
    When I enter chairs in the search bar
    Then Search result should show nothing
