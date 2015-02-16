Feature: Premium account

  Rules:
    - mention the word "buy" and you lose 5 credits
    - over-long messages cost 2 credits

  Background:
    Given the range is 100
    And the following people:
      | name     | Sean | Lucy |
      | location | 0    | 100  |

  Scenario: Sean shouts several over-long messages and several containing the word "buy"
    Given Sean has bought 30 credits
    When Sean shouts 2 over-long message
    When Sean shouts 3 messages containing the word "buy"
    Then Lucy hears all Sean's messages
    And Sean should have 11 credits

  @todo
  Scenario: BUG #2789
    Given Sean has bought 30 credits
    When Sean shouts "buy, buy buy!"
    Then Sean should have 25 credits