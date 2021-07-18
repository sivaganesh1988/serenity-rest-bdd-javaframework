Feature: Filtering profanity

  @positive
  Scenario Outline: Filtering profanity for valid texts and providing results in multiple formats
    When Siva requests a "<text>" after filtering for profanity in specific "<format>"
    Then he should be able to view the "<filtered text>" returned in requested "<format>"
    Examples:
      | text      | format | filtered text |
      | sample    | json   | sample        |
      | smarta$$  | xml    | ********      |
      | class     | plain  | class         |


  @negative
  Scenario Outline: Filtering profanity for invalid texts and providing results in multiple formats
    When Siva requests a "<text>" after filtering for profanity in specific "<format>"
    Then he should be shown an "<error>" message in requested "<format>"
    Examples:
      | text      | format | error    |
      |           | json   | No Input |
      |           | xml    | No Input |
      |           | plain  | No Input |