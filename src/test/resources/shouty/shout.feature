Feature: Shout

  Scenario: Listener is within range
    Given Lucy is 15m from Sean
    When Sean shouts "Free bagels!"
    Then Lucy hears Sean's message

  Scenario: Listener hears a different message
    Given Lucy is 15m from Sean
    When Sean shouts "Free coffee!"
    Then Lucy hears Sean's message
