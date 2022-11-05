Feature: Basic commands

  @Mobile
  Scenario: List of basic commands
    When I set variable "password" as "admin"
    And I type "admin" on mobile element "page.edtUsername"
    And I type "${password}" on mobile element "page.edtPassword[edtPassword]"
    And I click on mobile element "page.btnLogin"
    And I get text from "page.txtErrorMessage" and set to variable "message"
    And I should see "page.txtErrorMessage" displayed
    And I should see "page.txtErrorMessage" displaying "${message}"
    And I should see "page.txtErrorMessage" contains "success"
    And I scroll down
    And I scroll up
    And I swipe left
    And I swipe right
    And I open deep link "link"
    And I switch to native view
    And I switch to web view
    And I set list variables
      | key   | value   |
      | key 1 | value 1 |
      | kye 2 | value 2 |

