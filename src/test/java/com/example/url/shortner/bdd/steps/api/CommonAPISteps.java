package com.example.url.shortner.bdd.steps.api;

import static com.example.url.shortner.constants.TestConstants.LONG_URL;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.log4j.Log4j2;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.example.url.shortner.Application;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles(value = "local")
@ContextConfiguration
@Log4j2
public class CommonAPISteps {

  @LocalServerPort
  private int port;

  private String shortnerServiceUrl;

  public void saveLongUrl(final String longUrl) {
    Serenity.getCurrentSession().put(LONG_URL, longUrl);
  }

  public void assertResponseCode(final int statusCode) {
    assertThat(SerenityRest.then().extract().statusCode(), is(statusCode));
  }

  public void shortenUrl() {
    setShortnerServiceUrl();
    SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
    SerenityRest.given()
        .contentType("application/json")
        .accept("application/json")
        .body(Serenity.getCurrentSession().get(LONG_URL))
        .when()
        .post(shortnerServiceUrl + "/api/long-url");
  }

  public void assertResponse() {
    Assert.assertNotNull(SerenityRest.then().extract().body().asPrettyString());
  }

  public void assertLongUrl() {
    assertThat(SerenityRest.then().extract().body().asPrettyString(), is(Serenity.getCurrentSession().get(LONG_URL)));
  }

  @Step
  public void getLongUrl() {
    var shortUrl = SerenityRest.then().extract().body().asPrettyString();
    SerenityRest.clear();
    setShortnerServiceUrl();
    SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
    SerenityRest.given()
        .contentType("application/json")
        .accept("application/json")
        .when()
        .get(shortnerServiceUrl + "/api/short-url/?shortUrl=" + shortUrl);
  }

  private void setShortnerServiceUrl() {
    try {
      shortnerServiceUrl = format("http://%s:%s", InetAddress.getLocalHost().getHostAddress(), port);
    } catch (final UnknownHostException e) {
      log.error(e, e);
    }
  }
}
