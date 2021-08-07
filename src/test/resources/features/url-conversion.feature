@CREATE_REPORTS
Feature: A long url is converted to short url

  Scenario: User coverts long url to short url
    Given LongUrl https://www.real.com
    When The user convert longUrl to shortUrl
    Then A response code 200 is obtained
    And A non null response is received
    When The user attempts get back the long url with the previous result
    Then A response code 200 is obtained
    And The url is decoded back