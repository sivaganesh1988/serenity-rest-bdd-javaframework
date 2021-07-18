Feature: Adding new profanity string and updating input character/text with replacement string

  @positive
  Scenario Outline: Filtering profanity for valid texts after adding new valid profanity string
    When Siva requests a "<text>" after filtering using new "<profanity String>" by replacing with "<replacement char>" character in "<format>"
    Then he should be able to view the "<filtered text>" returned in requested "<format>"
    Examples:
      | text               | format | profanity String |replacement char | filtered text         |
      | this is awesome    | json   | this,some        |                 |**** is awe****        |
      | this is a test    | xml    | a             |-|this is - test|

  @negative
  Scenario Outline: Filtering profanity for valid texts after adding new invalid profanity string
    When Siva requests a "<text>" after filtering using new "<profanity String>" by replacing with "<replacement char>" character in "<format>"
    Then he should be shown an "<error>" message in requested "<format>"
    Examples:
      | text               | format | profanity String      |replacement char           | error                                    |
      | this is awesome    | json   | this,some             | $                         |Invalid User Replacement Characters       |
      | this is awesome    | xml    | this                  | twentycharacterlimitcheck |Invalid User Replacement Characters       |
      | this is awesome    | plain  | a,a,a,a,a,a,a,a,a,a,a | *                         |User Black List Exceeds Limit of 10 Words.|
