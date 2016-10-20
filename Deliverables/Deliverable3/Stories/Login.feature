
Feature: As a user, I should be ablt to login with valid credentials
    
    Scenario: Test login with valid credentials
    Given Open firefox and start application
    When I enter valid username and valid password
    Then User should be able to login successfully

    Scenario: Test login with invalid credentials
    Given Open firefox and start application
    When I enter invalid username or password
    Then User should not be able to login

    
