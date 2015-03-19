Feature: Premium account

  Rules:
  - mention the word "buy" and you lose 5 credits per shout
  - over-long messages cost 2 credits

  Questions:
  - what happens if a message both has the word "buy" in it *and* is over-long?
  - what happens when Sean runs out of credits?

  Background:
    Given the range is 100
    And the following people:
      | name     | Sean | Lucy |
      | location | 0    | 100  |

  Scenario: Sean shouts several over-long messages
    Given Sean has bought 30 credits
    When Sean shouts 2 over-long message
    Then Lucy hears all Sean's messages
    And Sean should have 26 credits

  Scenario: Sean shouts several messages containing the word "buy"
    Given Sean has bought 30 credits
    When Sean shouts 3 messages containing the word "buy"
    Then Lucy hears all Sean's messages
    And Sean should have 15 credits

  Scenario: Sean shouts "buy" several times in the same shout
    Given Sean has bought 100 credits
    When Sean shouts "buy, buy buy!"
    Then Sean should have 95 credits