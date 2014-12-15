Feature: Shout

  In order to send location-sensitive messages to people nearby
  As a shouter
  I want to broadcast messages to people near me

  Rules:
  - broadcast to all users
  - don't worry about proximity yet
  - only shout to people within a certain distance

  Background:
    Given the range is 100
    And a person named Lucy at location 100
    And a person named Sean at location 0
    And a person named Larry at location 150

  Scenario: Listener hears a message
    When Sean shouts "Free bagels!"
    Then Lucy hears Sean's message

  Scenario: Listener hears a different message
    When Sean shouts "Free coffee!"
    Then Lucy hears Sean's message

  Scenario: Listener is within range
    When Sean shouts "Free bagels!"
    Then Lucy hears Sean's message

  Scenario: Listener is out of range
    When Sean shouts "Free bagels!"
    Then Larry does not hear Sean's message
