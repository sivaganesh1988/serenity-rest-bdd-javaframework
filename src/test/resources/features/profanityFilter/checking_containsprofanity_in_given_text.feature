Feature: Checking profanity content in a given text

  @positive
  Scenario Outline: Checking profanity content in a given text
    When Siva requests a "<text>" to be checked for profanity
    Then he should be shown with the "<result>" in plain text format
    Examples:
      | text      | result |
      | sample    | false  |
      | smarta$$  | true   |
      | class     | false  |
