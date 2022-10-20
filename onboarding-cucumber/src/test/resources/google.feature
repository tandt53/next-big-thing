Feature: Search google

  @Web_S1 @Web
  Scenario: search google demo
#    Given I open browser
    When I go to "http://google.com.vn"
    And I type and search keyword "tandt53"
    Then I should see the result number

  @Mobile_S1 @Mobile
  Scenario: mobile demo
    Given I open application
    And I login with email "admin" and password "admin"
    Then I verify message "Login success"
