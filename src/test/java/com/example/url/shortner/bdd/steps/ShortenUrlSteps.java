package com.example.url.shortner.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import com.example.url.shortner.bdd.steps.api.UrlConversionAPISteps;

public class ShortenUrlSteps {

  @Steps
  private UrlConversionAPISteps steps;

  @Given("^LongUrl (.*)$")
  public void longUrl(final String longUrl) {
    steps.saveLongUrl(longUrl);
  }

  @When("^The user convert longUrl to shortUrl$")
  public void shortenUrl() {
    steps.shortenUrl();
  }

  @Then("A response code {int} is obtained")
  public void assertResponseCode(final int responseCode) {
    steps.assertResponseCode(responseCode);
  }

  @And("^A non null response is received$")
  public void assertResponse() {
    steps.assertResponse();
  }

  @Then("The user attempts get back the long url with the previous result")
  public void getLongUrl() {
    steps.getLongUrl();
  }

  @And("The url is decoded back")
  public void assertLongUrl() {
    steps.assertLongUrl();
  }
}
