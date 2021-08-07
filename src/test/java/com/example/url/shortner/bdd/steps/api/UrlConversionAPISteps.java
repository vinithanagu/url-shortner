package com.example.url.shortner.bdd.steps.api;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class UrlConversionAPISteps {

  @Steps
  private CommonAPISteps commonAPISteps;

  @Step(value = "Storing long url")
  public void saveLongUrl(final String longUrl) {
    commonAPISteps.saveLongUrl(longUrl);
  }

  @Step(value = "Assert the response code")
  public void assertResponseCode(final int statusCode) {
    commonAPISteps.assertResponseCode(statusCode);
  }

  @Step(value = "Hit the endpoint to convert long url to short url")
  public void shortenUrl() {
    commonAPISteps.shortenUrl();
  }

  @Step(value = "Assert the response is non null")
  public void assertResponse() {
    commonAPISteps.assertResponse();
  }

  @Step(value = "Hit the endpoint to retrieve long url")
  public void getLongUrl() {
    commonAPISteps.getLongUrl();
  }

  @Step(value = "Assert the response body")
  public void assertLongUrl() {
    commonAPISteps.assertLongUrl();
  }
}
