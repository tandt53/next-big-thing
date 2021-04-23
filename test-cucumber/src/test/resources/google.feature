Feature: Search google

  @Web_S1
  Scenario: search google demo
    Given I open browser
    When I go to "http://google.com.vn"
    And I type and search keyword "VinId"
    When I check state in anther step definition class is equals to "http://google.com.vn"
#
#  @State_S1 @Mobile
#  Scenario: search state
#    Given I set state value is "state"
#    When I check state in anther step definition class is equals to "state"

#  @State_S2 @Web
#  Scenario: search state
#    Given I set variable value is "variable"
#    When I check variable in anther step definition class is equals to "variable"

    @Mobile_S1
    Scenario: mobile demo
      Given I open application
      And I login with email "admin" and password "admin"
      Then I verify message "Login success"