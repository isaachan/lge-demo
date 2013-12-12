Feature: Sign Up

  Scenario: I can sign up with valid username/password
    Given I enter text "james" into field with id "username"
    And   I enter text "1111aaaa" into field with id "password"
    And   I press the "Sign Up" button
    Then  I see the text "Sign up james successfully."

