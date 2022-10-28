Feature: Basic commands

  @Mobile
  Scenario: List of basic commands
    And I type "admin" on mobile element "page.edtUsername"
    And I type "admin" on mobile element "page.edtPassword"
    And I click on mobile element "page.btnLogin"
#    And I get text from "element"
#    And I should see "element" displayed
#    And I should see "element" displaying "text"
#    And I scroll down in list view
#    And I scroll up in list view
#    And I scroll down from "start double value" to "end double value"
#    And I scroll up from "start double value" to "end double value"
#    And I swipe left
#    And I swipe right
#    And I swipe left on "element"
#    And I swipe right on "element"
#    And I open deep link ""
#    And I switch to native view
#    And I switch to web view
