Feature:Update products using PUT API
  Scenario Outline: Validate put product api status code work correctly
    Given I hit the url of put products api endpoint
    When  I pass the url in the request with <ProductNumber>
    Then  I receive the response code as 200
    Examples:
      | ProductNumber |
      | 6       |