Feature: Adding new profanity string and updating input text with replacement text

  @positive
  Scenario Outline: Filtering profanity for valid texts after adding new valid profanity string
    When Siva requests a "<text>" after filtering using new "<profanity String>" by replacing with "<replacement string>" in "<format>"
    Then he should be able to view the "<filtered text>" returned in requested "<format>"
    Examples:
      | text               | format | profanity String |replacement string | filtered text            |
      | this is awesome    | json   | this,some        |                   |**** is awe****           |
      | this is awesome    | xml    | this             | that              |that is awesome           |
      | this is awesome    | plain  | this             | !                 |! is awesome              |

  @negative
  Scenario Outline: Filtering profanity for valid texts after adding new invalid profanity string
    When Siva requests a "<text>" after filtering using new "<profanity String>" by replacing with "<replacement string>" in "<format>"
    Then he should be shown an "<error>" message in requested "<format>"
    Examples:
      | text               | format | profanity String      |replacement string         | error                                               |
      | this is awesome    | json   | this,some             | $                         |Invalid User Replacement Text                        |
      | this is awesome    | xml    | this                  | twentycharacterlimitcheck |User Replacement Text Exceeds Limit of 20 Characters.|
      | this is awesome    | plain    | a,a,a,a,a,a,a,a,a,a,a | addwordlimitcheck         |User Black List Exceeds Limit of 10 Words.|

